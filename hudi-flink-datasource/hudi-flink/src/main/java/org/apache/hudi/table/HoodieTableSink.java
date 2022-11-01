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

package org.apache.hudi.table;

import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.WriteOperationType;
import org.apache.hudi.configuration.FlinkOptions;
import org.apache.hudi.configuration.OptionsResolver;
import org.apache.hudi.sink.utils.Pipelines;
import org.apache.hudi.util.ChangelogModes;
import org.apache.hudi.util.StreamerUtil;

import org.apache.flink.annotation.VisibleForTesting;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.table.catalog.ResolvedSchema;
import org.apache.flink.table.connector.ChangelogMode;
import org.apache.flink.table.connector.sink.DataStreamSinkProvider;
import org.apache.flink.table.connector.sink.DynamicTableSink;
import org.apache.flink.table.connector.sink.abilities.SupportsOverwrite;
import org.apache.flink.table.connector.sink.abilities.SupportsPartitioning;
import org.apache.flink.table.types.logical.RowType;

import java.util.Map;

/**
 * Hoodie table sink.
 */
public class HoodieTableSink implements DynamicTableSink, SupportsPartitioning, SupportsOverwrite {

  private final Configuration conf;
  private final ResolvedSchema schema;
  private boolean overwrite = false;

  public HoodieTableSink(Configuration conf, ResolvedSchema schema) {
    this.conf = conf;
    this.schema = schema;
  }

  public HoodieTableSink(Configuration conf, ResolvedSchema schema, boolean overwrite) {
    this.conf = conf;
    this.schema = schema;
    this.overwrite = overwrite;
  }
  //  flink sql 可以利用 options 暗示来添加设置 flinkhudi 参数，如下，参数 必须加单引号
  //  insert into targetTable /*+ OPTIONS('write.tasks'='2', 'write.bucket_assign. tasks'='3' , ' compaction. tasks'='4')*/
  //  select from sourceTable;
  @Override
  public SinkRuntimeProvider getSinkRuntimeProvider(Context context) {
    return (DataStreamSinkProvider) dataStream -> {

      // setup configuration
      long ckpTimeout = dataStream.getExecutionEnvironment()
          .getCheckpointConfig().getCheckpointTimeout();
      conf.setLong(FlinkOptions.WRITE_COMMIT_ACK_TIMEOUT, ckpTimeout);

      RowType rowType = (RowType) schema.toSourceRowDataType().notNull().getLogicalType();

      // bulk_insert mode
      final String writeOperation = this.conf.get(FlinkOptions.OPERATION);
      if (WriteOperationType.fromValue(writeOperation) == WriteOperationType.BULK_INSERT) {
        return Pipelines.bulkInsert(conf, rowType, dataStream);
      }

      // Append mode
      if (OptionsResolver.isAppendMode(conf)) {
        return Pipelines.append(conf, rowType, dataStream, context.isBounded());
      }

      // default parallelism
      int parallelism = dataStream.getExecutionConfig().getParallelism();
      DataStream<Object> pipeline;
      // bootstrap
      final DataStream<HoodieRecord> hoodieRecordDataStream =
          Pipelines.bootstrap(conf, rowType, parallelism, dataStream, context.isBounded(), overwrite);
      // write pipeline

      //StreamWriteOperatorCoordinator 在整个流程中也有重要作用 ,比如同步 hive的逻辑
      pipeline = Pipelines.hoodieStreamWrite(conf, parallelism, hoodieRecordDataStream);

      // compaction
      // 在线异步的压缩容易干扰写流程,离线压缩更稳定

      // 离线压缩设置
      // compaction.async.enabled 设为 false, 关闭在线 compaction
      // compaction.schedule.enabled 设为true, 生成plan 的 调度仍然保持开启 （因为离线压缩中默认不会调度生成 plan）

      // org.apache.hudi.sink.compact.HoodieFlinkCompactor 是离线压缩的工具类, 运行参数参看FlinkCompactionConfig 配置类
      // 可以直接起一个压缩的常驻服务 , 也可以使用外部集中式调度起如 azkaban 来周期性调度（后者更加省资源）

      // 具体使用
      // ./bin/flink run  -C org.apache.hudi.sink.compact.HoodieFlinkCompactor lib/hudi-flink1.13-bundle-0.12.0.jar
      // --path hdfs://hadoop1:8020/tmp/hudi_catalog/default/tableName
      // 常驻压缩 加--service 和 --min-compaction-interval-seconds 参数
      if (StreamerUtil.needsAsyncCompaction(conf)) {
        //  是mor表  并且  compaction.async.enabled 开启
        return Pipelines.compact(conf, pipeline);
      } else {
        return Pipelines.clean(conf, pipeline);
      }
    };
  }

  @VisibleForTesting
  public Configuration getConf() {
    return this.conf;
  }

  @Override
  public ChangelogMode getChangelogMode(ChangelogMode changelogMode) {
    if (conf.getBoolean(FlinkOptions.CHANGELOG_ENABLED)) {
      return ChangelogModes.FULL;
    } else {
      return ChangelogModes.UPSERT;
    }
  }

  @Override
  public DynamicTableSink copy() {
    return new HoodieTableSink(this.conf, this.schema, this.overwrite);
  }

  @Override
  public String asSummaryString() {
    return "HoodieTableSink";
  }

  @Override
  public void applyStaticPartition(Map<String, String> partitions) {
    // #applyOverwrite should have been invoked.
    if (this.overwrite && partitions.size() > 0) {
      this.conf.setString(FlinkOptions.OPERATION, WriteOperationType.INSERT_OVERWRITE.value());
    }
  }

  @Override
  public void applyOverwrite(boolean overwrite) {
    this.overwrite = overwrite;
    // set up the operation as INSERT_OVERWRITE_TABLE first,
    // if there are explicit partitions, #applyStaticPartition would overwrite the option.
    this.conf.setString(FlinkOptions.OPERATION, WriteOperationType.INSERT_OVERWRITE_TABLE.value());
  }
}
