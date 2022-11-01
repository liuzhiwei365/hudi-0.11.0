/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hudi.sink.utils;

import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.configuration.FlinkOptions;
import org.apache.hudi.configuration.OptionsResolver;
import org.apache.hudi.sink.CleanFunction;
import org.apache.hudi.sink.StreamWriteOperator;
import org.apache.hudi.sink.append.AppendWriteOperator;
import org.apache.hudi.sink.bootstrap.BootstrapOperator;
import org.apache.hudi.sink.bootstrap.batch.BatchBootstrapOperator;
import org.apache.hudi.sink.bucket.BucketBulkInsertWriterHelper;
import org.apache.hudi.sink.bucket.BucketStreamWriteOperator;
import org.apache.hudi.sink.bulk.BulkInsertWriteOperator;
import org.apache.hudi.sink.bulk.RowDataKeyGen;
import org.apache.hudi.sink.bulk.sort.SortOperatorGen;
import org.apache.hudi.sink.common.WriteOperatorFactory;
import org.apache.hudi.sink.compact.CompactFunction;
import org.apache.hudi.sink.compact.CompactionCommitEvent;
import org.apache.hudi.sink.compact.CompactionCommitSink;
import org.apache.hudi.sink.compact.CompactionPlanEvent;
import org.apache.hudi.sink.compact.CompactionPlanOperator;
import org.apache.hudi.sink.partitioner.BucketAssignFunction;
import org.apache.hudi.sink.partitioner.BucketIndexPartitioner;
import org.apache.hudi.sink.transform.RowDataToHoodieFunctions;
import org.apache.hudi.table.format.FilePathUtils;

import org.apache.flink.api.common.functions.Partitioner;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.runtime.state.KeyGroupRangeAssignment;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.api.graph.StreamGraphGenerator;
import org.apache.flink.streaming.api.operators.KeyedProcessOperator;
import org.apache.flink.streaming.api.operators.ProcessOperator;
import org.apache.flink.table.data.RowData;
import org.apache.flink.table.planner.plan.nodes.exec.utils.ExecNodeUtil;
import org.apache.flink.table.runtime.typeutils.InternalTypeInfo;
import org.apache.flink.table.types.logical.RowType;

import java.util.HashMap;
import java.util.Map;

/**
 * Utilities to generate all kinds of sub-pipelines.
 */
public class Pipelines {

  /**
   * Bulk insert the input dataset at once.
   *
   * <p>By default, the input dataset would shuffle by the partition path first then
   * sort by the partition path before passing around to the write function.
   * The whole pipeline looks like the following:
   *
   * <pre>
   *      | input1 | ===\     /=== |sorter| === | task1 | (p1, p2)
   *                   shuffle
   *      | input2 | ===/     \=== |sorter| === | task2 | (p3, p4)
   *
   *      Note: Both input1 and input2's dataset come from partitions: p1, p2, p3, p4
   * </pre>
   *
   * <p>The write task switches to new file handle each time it receives a record
   * from the different partition path, the shuffle and sort would reduce small files.
   *
   * <p>The bulk insert should be run in batch execution mode.
   *
   * @param conf       The configuration
   * @param rowType    The input row type
   * @param dataStream The input data stream
   * @return the bulk insert data stream sink
   */
  public static DataStreamSink<Object> bulkInsert(Configuration conf, RowType rowType, DataStream<RowData> dataStream) {
    WriteOperatorFactory<RowData> operatorFactory = BulkInsertWriteOperator.getFactory(conf, rowType);
    if (OptionsResolver.isBucketIndexType(conf)) {
      String indexKeys = conf.getString(FlinkOptions.INDEX_KEY_FIELD);
      int numBuckets = conf.getInteger(FlinkOptions.BUCKET_INDEX_NUM_BUCKETS);

      //分区器 和 正常流程之前一样
      BucketIndexPartitioner<String> partitioner = new BucketIndexPartitioner<>(numBuckets, indexKeys);
      RowDataKeyGen keyGen = RowDataKeyGen.instance(conf, rowType);

      RowType rowTypeWithFileId = BucketBulkInsertWriterHelper.rowTypeWithFileId(rowType);

      InternalTypeInfo<RowData> typeInfo = InternalTypeInfo.of(rowTypeWithFileId);

      Map<String, String> bucketIdToFileId = new HashMap<>();
      dataStream = dataStream.partitionCustom(partitioner, keyGen::getRecordKey)
          .map(record -> BucketBulkInsertWriterHelper.rowWithFileId(bucketIdToFileId, keyGen, record, indexKeys, numBuckets), typeInfo)
          .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS)); // same parallelism as write task to avoid shuffle

      // 处理排序 , 默认要排序
      if (conf.getBoolean(FlinkOptions.WRITE_BULK_INSERT_SORT_INPUT)) {
        SortOperatorGen sortOperatorGen = BucketBulkInsertWriterHelper.getFileIdSorterGen(rowTypeWithFileId);
        dataStream = dataStream.transform("file_sorter", typeInfo, sortOperatorGen.createSortOperator())
            .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS)); // same parallelism as write task to avoid shuffle

        // flink api 给算子设置资源
        ExecNodeUtil.setManagedMemoryWeight(dataStream.getTransformation(),
            conf.getInteger(FlinkOptions.WRITE_SORT_MEMORY) * 1024L * 1024L);
      }

      return dataStream
          .transform("bucket_bulk_insert", TypeInformation.of(Object.class), operatorFactory)
          .uid("uid_bucket_bulk_insert" + conf.getString(FlinkOptions.TABLE_NAME))
          .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS))
          .addSink(DummySink.INSTANCE)
          .name("dummy");
    }

    final String[] partitionFields = FilePathUtils.extractPartitionKeys(conf);
    if (partitionFields.length > 0) {
      RowDataKeyGen rowDataKeyGen = RowDataKeyGen.instance(conf, rowType);
      if (conf.getBoolean(FlinkOptions.WRITE_BULK_INSERT_SHUFFLE_INPUT)) {

        // shuffle by partition keys
        // use #partitionCustom instead of #keyBy to avoid duplicate sort operations,
        // see BatchExecutionUtils#applyBatchExecutionSettings for details.
        Partitioner<String> partitioner = (key, channels) ->
            KeyGroupRangeAssignment.assignKeyToParallelOperator(key, StreamGraphGenerator.DEFAULT_LOWER_BOUND_MAX_PARALLELISM, channels);
        dataStream = dataStream.partitionCustom(partitioner, rowDataKeyGen::getPartitionPath);
      }
      if (conf.getBoolean(FlinkOptions.WRITE_BULK_INSERT_SORT_INPUT)) {
        SortOperatorGen sortOperatorGen = new SortOperatorGen(rowType, partitionFields);
        // sort by partition keys
        dataStream = dataStream
            .transform("partition_key_sorter",
                TypeInformation.of(RowData.class),
                sortOperatorGen.createSortOperator())
            .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS));
        ExecNodeUtil.setManagedMemoryWeight(dataStream.getTransformation(),
            conf.getInteger(FlinkOptions.WRITE_SORT_MEMORY) * 1024L * 1024L);
      }
    }
    return dataStream
        .transform("hoodie_bulk_insert_write",
            TypeInformation.of(Object.class),
            operatorFactory)
        // follow the parallelism of upstream operators to avoid shuffle
        .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS))
        .addSink(DummySink.INSTANCE)
        .name("dummy");
  }

  /**
   * Insert the dataset with append mode(no upsert or deduplication).
   *
   * <p>The input dataset would be rebalanced among the write tasks:
   *
   * <pre>
   *      | input1 | ===\     /=== | task1 | (p1, p2, p3, p4)
   *                   shuffle
   *      | input2 | ===/     \=== | task2 | (p1, p2, p3, p4)
   *
   *      Note: Both input1 and input2's dataset come from partitions: p1, p2, p3, p4
   * </pre>
   *
   * <p>The write task switches to new file handle each time it receives a record
   * from the different partition path, so there may be many small files.
   *
   * @param conf       The configuration
   * @param rowType    The input row type
   * @param dataStream The input data stream
   * @param bounded    Whether the input stream is bounded
   * @return the appending data stream sink
   */
  public static DataStreamSink<Object> append(
      Configuration conf,
      RowType rowType,
      DataStream<RowData> dataStream,
      boolean bounded) {
    if (!bounded) {
      // In principle, the config should be immutable, but the boundedness
      // is only visible when creating the sink pipeline.
      conf.setBoolean(FlinkOptions.WRITE_BULK_INSERT_SORT_INPUT, false);
    }
    WriteOperatorFactory<RowData> operatorFactory = AppendWriteOperator.getFactory(conf, rowType);

    return dataStream
        .transform("hoodie_append_write", TypeInformation.of(Object.class), operatorFactory)
        .uid("uid_hoodie_stream_write" + conf.getString(FlinkOptions.TABLE_NAME))
        .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS))
        .addSink(DummySink.INSTANCE)
        .name("dummy");
  }

  /**
   * Constructs bootstrap pipeline as streaming.
   * The bootstrap operator loads the existing data index (primary key to file id mapping),
   * then sends the indexing data set to subsequent operator(usually the bucket assign operator).
   */
  public static DataStream<HoodieRecord> bootstrap(
      Configuration conf,
      RowType rowType,
      int defaultParallelism,
      DataStream<RowData> dataStream) {
    return bootstrap(conf, rowType, defaultParallelism, dataStream, false, false);
  }

  /**
   * Constructs bootstrap pipeline.
   * The bootstrap operator loads the existing data index (primary key to file id mapping),
   * then send the indexing data set to subsequent operator(usually the bucket assign operator).
   *
   * @param conf               The configuration
   * @param rowType            The row type
   * @param defaultParallelism The default parallelism
   * @param dataStream         The data stream
   * @param bounded            Whether the source is bounded
   * @param overwrite          Whether it is insert overwrite
   */
  public static DataStream<HoodieRecord> bootstrap(
      Configuration conf,
      RowType rowType,
      int defaultParallelism,
      DataStream<RowData> dataStream,
      boolean bounded,
      boolean overwrite) {
    final boolean globalIndex = conf.getBoolean(FlinkOptions.INDEX_GLOBAL_ENABLED);
    if (overwrite || OptionsResolver.isBucketIndexType(conf)) {
      // 桶索引 不需要进行索引绑定
      // rowData 是flinksql 的一条数据的抽象
      // 1 把 flinksql 的类型 转化 为 hoodie 类型
      // 2 流控 （建议把流控的功能剥离出去） write.rate.limit
      // 3 利用  KeyGenerator  生成 数据的 hoodiekey
      // 4 利用  PayloadCreation  生成 数据的 payload

      // 注意 :  该方法调用完成后 返回的数据格式是   HoodieRecord 的子类 HoodieAvroRecord （paylaod包含在其中）
      return rowDataToHoodieRecord(conf, rowType, dataStream);
    } else if (bounded && !globalIndex && OptionsResolver.isPartitionedTable(conf)) {
      //批模式的索引绑定
      return boundedBootstrap(conf, rowType, defaultParallelism, dataStream);
    } else {
      //流模式的索引绑定
      return streamBootstrap(conf, rowType, defaultParallelism, dataStream, bounded);
    }
  }

  private static DataStream<HoodieRecord> streamBootstrap(
      Configuration conf,
      RowType rowType,
      int defaultParallelism,
      DataStream<RowData> dataStream,
      boolean bounded) {
    DataStream<HoodieRecord> dataStream1 = rowDataToHoodieRecord(conf, rowType, dataStream);

    // 从已存在的hoodie表中 ， 绑定索引状态
    // 也就是加载 flink 状态
    if (conf.getBoolean(FlinkOptions.INDEX_BOOTSTRAP_ENABLED) || bounded) {
      dataStream1 = dataStream1
          .transform(
              "index_bootstrap",
              TypeInformation.of(HoodieRecord.class),
              new BootstrapOperator<>(conf))
          .setParallelism(conf.getOptional(FlinkOptions.INDEX_BOOTSTRAP_TASKS).orElse(defaultParallelism))
          .uid("uid_index_bootstrap_" + conf.getString(FlinkOptions.TABLE_NAME));
    }

    return dataStream1;
  }

  /**
   * Constructs bootstrap pipeline for batch execution mode.
   * The indexing data set is loaded before the actual data write
   * in order to support batch UPSERT.
   */
  private static DataStream<HoodieRecord> boundedBootstrap(
      Configuration conf,
      RowType rowType,
      int defaultParallelism,
      DataStream<RowData> dataStream) {
    final RowDataKeyGen rowDataKeyGen = RowDataKeyGen.instance(conf, rowType);
    // shuffle by partition keys
    dataStream = dataStream
        .keyBy(rowDataKeyGen::getPartitionPath);

    return rowDataToHoodieRecord(conf, rowType, dataStream)
        .transform(
            "batch_index_bootstrap",
            TypeInformation.of(HoodieRecord.class),
            new BatchBootstrapOperator<>(conf))
        .setParallelism(conf.getOptional(FlinkOptions.INDEX_BOOTSTRAP_TASKS).orElse(defaultParallelism))
        .uid("uid_batch_index_bootstrap_" + conf.getString(FlinkOptions.TABLE_NAME));
  }

  /**
   * Transforms the row data to hoodie records.
   */
  public static DataStream<HoodieRecord> rowDataToHoodieRecord(Configuration conf, RowType rowType, DataStream<RowData> dataStream) {
    return dataStream.map(RowDataToHoodieFunctions.create(rowType, conf), TypeInformation.of(HoodieRecord.class))
            .setParallelism(dataStream.getParallelism()).name("row_data_to_hoodie_record");
  }

  /**
   * The streaming write pipeline.
   *
   * <p>The input dataset shuffles by the primary key first then
   * shuffles by the file group ID before passing around to the write function.
   * The whole pipeline looks like the following:
   *
   * <pre>
   *      | input1 | ===\     /=== | bucket assigner | ===\     /=== | task1 |
   *                   shuffle(by PK)                    shuffle(by bucket ID)
   *      | input2 | ===/     \=== | bucket assigner | ===/     \=== | task2 |
   *
   *      Note: a file group must be handled by one write task to avoid write conflict.
   * </pre>
   *
   * <p>The bucket assigner assigns the inputs to suitable file groups, the write task caches
   * and flushes the data set to disk.
   *
   * @param conf               The configuration
   * @param defaultParallelism The default parallelism
   * @param dataStream         The input data stream
   * @return the stream write data stream pipeline
   */
  public static DataStream<Object> hoodieStreamWrite(Configuration conf, int defaultParallelism, DataStream<HoodieRecord> dataStream) {
    if (OptionsResolver.isBucketIndexType(conf)) {

      WriteOperatorFactory<HoodieRecord> operatorFactory = BucketStreamWriteOperator.getFactory(conf);
      //hoodie.bucket.index.num.buckets
      int bucketNum = conf.getInteger(FlinkOptions.BUCKET_INDEX_NUM_BUCKETS);
      String indexKeyFields = conf.getString(FlinkOptions.INDEX_KEY_FIELD);

      //该分区器的策略与  BucketStreamWriteFunction的 getBucketToLoad()方法 , 保持一致的逻辑，这点非常重要
      BucketIndexPartitioner<String> partitioner = new BucketIndexPartitioner<>(bucketNum, indexKeyFields);
      // 自定义分区器分区
      return dataStream.partitionCustom(partitioner, HoodieRecord::getRecordKey)
              //实际写入的逻辑  由 BucketStreamWriteFunction 来承载
          .transform("bucket_write", TypeInformation.of(Object.class), operatorFactory)
             //使用uid后,即使代码升级后,用savepoint来恢复任务 仍然能够正常恢复其状态正常运行; 不使用uid,状态没法恢复
          .uid("uid_bucket_write" + conf.getString(FlinkOptions.TABLE_NAME))
          .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS));

    } else {
      //非桶索引流程

      WriteOperatorFactory<HoodieRecord> operatorFactory = StreamWriteOperator.getFactory(conf);
      return dataStream
          // Key-by record key, to avoid multiple subtasks write to a bucket at the same time
          .keyBy(HoodieRecord::getRecordKey)
          .transform(
              "bucket_assigner",
              TypeInformation.of(HoodieRecord.class),
              new KeyedProcessOperator<>(new BucketAssignFunction<>(conf)))// 会将索引维护在状态里面
          .uid("uid_bucket_assigner_" + conf.getString(FlinkOptions.TABLE_NAME))
          .setParallelism(conf.getOptional(FlinkOptions.BUCKET_ASSIGN_TASKS).orElse(defaultParallelism))
          // shuffle by fileId(bucket id)
          .keyBy(record -> record.getCurrentLocation().getFileId())
          .transform("stream_write", TypeInformation.of(Object.class), operatorFactory)
          .uid("uid_stream_write" + conf.getString(FlinkOptions.TABLE_NAME))
          .setParallelism(conf.getInteger(FlinkOptions.WRITE_TASKS));
    }
  }

  /**
   * The compaction tasks pipeline.
   *
   * <p>The compaction plan operator monitors the new compaction plan on the timeline
   * then distributes the sub-plans to the compaction tasks. The compaction task then
   * handle over the metadata to commit task for compaction transaction commit.
   * The whole pipeline looks like the following:
   *
   * <pre>
   *                                           /=== | task1 | ===\
   *      | plan generation | ===> re-balance                      | commit |
   *                                           \=== | task2 | ===/
   *
   *      Note: both the compaction plan generation task and commission task are singleton.
   * </pre>
   *
   * @param conf       The configuration
   * @param dataStream The input data stream
   * @return the compaction pipeline
   */
  public static DataStreamSink<CompactionCommitEvent> compact(Configuration conf, DataStream<Object> dataStream) {
    return dataStream.transform("compact_plan_generate",
            TypeInformation.of(CompactionPlanEvent.class),
            new CompactionPlanOperator(conf))//在 checkpint 完成后 回调 scheduleCompaction ，生成压缩计划
        .setParallelism(1) // plan generate must be singleton
        .rebalance()
        .transform("compact_task",
            TypeInformation.of(CompactionCommitEvent.class),
            new ProcessOperator<>(new CompactFunction(conf))) //实际压缩
        .setParallelism(conf.getInteger(FlinkOptions.COMPACTION_TASKS))
        .addSink(new CompactionCommitSink(conf))// 提交压缩 , 默认异步 clean 操作
        .name("compact_commit")
        .setParallelism(1); // compaction commit should be singleton
  }

  public static DataStreamSink<Object> clean(Configuration conf, DataStream<Object> dataStream) {
    return dataStream.addSink(new CleanFunction<>(conf))
        .setParallelism(1)
        .name("clean_commits");
  }

  /**
   * Dummy sink that does nothing.
   */
  public static class DummySink implements SinkFunction<Object> {
    private static final long serialVersionUID = 1L;
    public static DummySink INSTANCE = new DummySink();
  }
}
