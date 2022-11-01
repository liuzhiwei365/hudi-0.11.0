/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hudi.table.action.commit;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.client.utils.SparkValidatorUtils;
import org.apache.hudi.common.data.HoodieData;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieCommitMetadata;
import org.apache.hudi.common.model.HoodieFileGroupId;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordLocation;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.model.HoodieWriteStat;
import org.apache.hudi.common.model.WriteOperationType;
import org.apache.hudi.common.table.timeline.HoodieActiveTimeline;
import org.apache.hudi.common.table.timeline.HoodieInstant;
import org.apache.hudi.common.util.CommitUtils;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.common.util.ReflectionUtils;
import org.apache.hudi.common.util.collection.Pair;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.data.HoodieJavaPairRDD;
import org.apache.hudi.data.HoodieJavaRDD;
import org.apache.hudi.exception.HoodieCommitException;
import org.apache.hudi.exception.HoodieIOException;
import org.apache.hudi.exception.HoodieUpsertException;
import org.apache.hudi.execution.SparkLazyInsertIterable;
import org.apache.hudi.io.CreateHandleFactory;
import org.apache.hudi.io.HoodieConcatHandle;
import org.apache.hudi.io.HoodieMergeHandle;
import org.apache.hudi.io.HoodieSortedMergeHandle;
import org.apache.hudi.keygen.BaseKeyGenerator;
import org.apache.hudi.keygen.factory.HoodieSparkKeyGeneratorFactory;
import org.apache.hudi.table.HoodieSparkTable;
import org.apache.hudi.table.HoodieTable;
import org.apache.hudi.table.WorkloadProfile;
import org.apache.hudi.table.WorkloadStat;
import org.apache.hudi.table.action.HoodieWriteMetadata;
import org.apache.hudi.table.action.cluster.strategy.UpdateStrategy;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.storage.StorageLevel;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import scala.Tuple2;

import static org.apache.hudi.common.util.ClusteringUtils.getAllFileGroupsInPendingClusteringPlans;
import static org.apache.hudi.config.HoodieWriteConfig.WRITE_STATUS_STORAGE_LEVEL_VALUE;

public abstract class BaseSparkCommitActionExecutor<T extends HoodieRecordPayload> extends
    BaseCommitActionExecutor<T, HoodieData<HoodieRecord<T>>, HoodieData<HoodieKey>, HoodieData<WriteStatus>, HoodieWriteMetadata<HoodieData<WriteStatus>>> {

  private static final Logger LOG = LogManager.getLogger(BaseSparkCommitActionExecutor.class);
  protected final Option<BaseKeyGenerator> keyGeneratorOpt;

  public BaseSparkCommitActionExecutor(HoodieEngineContext context,
                                       HoodieWriteConfig config,
                                       HoodieTable table,
                                       String instantTime,
                                       WriteOperationType operationType) {
    this(context, config, table, instantTime, operationType, Option.empty());
  }

  public BaseSparkCommitActionExecutor(HoodieEngineContext context,
                                       HoodieWriteConfig config,
                                       HoodieTable table,
                                       String instantTime,
                                       WriteOperationType operationType,
                                       Option<Map<String, String>> extraMetadata) {
    super(context, config, table, instantTime, operationType, extraMetadata);
    try {
      keyGeneratorOpt = config.populateMetaFields()
          ? Option.empty()
          : Option.of((BaseKeyGenerator) HoodieSparkKeyGeneratorFactory.createKeyGenerator(this.config.getProps()));
    } catch (IOException e) {
      throw new HoodieIOException("Only BaseKeyGenerators are supported when meta columns are disabled ", e);
    }
  }

  //考虑到clustering 的影响
  private HoodieData<HoodieRecord<T>> clusteringHandleUpdate(HoodieData<HoodieRecord<T>> inputRecords) {
    context.setJobStatus(this.getClass().getSimpleName(), "Handling updates which are under clustering");

    Set<HoodieFileGroupId> fileGroupsInPendingClustering =
        table.getFileSystemView().getFileGroupsInPendingClustering().map(Pair::getKey).collect(Collectors.toSet());

    UpdateStrategy<T, HoodieData<HoodieRecord<T>>> updateStrategy = (UpdateStrategy<T, HoodieData<HoodieRecord<T>>>) ReflectionUtils
            //hoodie.clustering.updates.strategy有拒绝 和 允许 两种策略,默认拒绝
        .loadClass(config.getClusteringUpdatesStrategyClass(), this.context, fileGroupsInPendingClustering);

    //拒绝策略中:pair 中 前者就是 inputRecords ,后者为 空集合
    //允许策略中:pair 中 前者也是 inputRecords ,后者为 (在命中匹配的 文件组中 进一步过滤出 过滤出正在进行 clustering 的文件组)
    Pair<HoodieData<HoodieRecord<T>>, Set<HoodieFileGroupId>> recordsAndPendingClusteringFileGroups =
        updateStrategy.handleUpdate(inputRecords);

    Set<HoodieFileGroupId> fileGroupsWithUpdatesAndPendingClustering = recordsAndPendingClusteringFileGroups.getRight();
    if (fileGroupsWithUpdatesAndPendingClustering.isEmpty()) {
      //在拒绝策略中
      //或者没有正在进行集群化的 文件组
      return recordsAndPendingClusteringFileGroups.getLeft();
    }

    // 如果是允许策略才走下面的逻辑
    // 回滚集群化操作 ,以避免冲突,需要配置 hoodie.clustering.rollback.pending.replacecommit.on.conflict 为true ,该参数默认是false
    if (config.isRollbackPendingClustering()) {
      Set<HoodieInstant> pendingClusteringInstantsToRollback = getAllFileGroupsInPendingClusteringPlans(table.getMetaClient()).entrySet().stream()
          .filter(e -> fileGroupsWithUpdatesAndPendingClustering.contains(e.getKey()))
          .map(Map.Entry::getValue)
          .collect(Collectors.toSet());
      //依次回滚
      pendingClusteringInstantsToRollback.forEach(instant -> {
        String commitTime = HoodieActiveTimeline.createNewInstantTime();
        table.scheduleRollback(context, commitTime, instant, false, config.shouldRollbackUsingMarkers());
        table.rollback(context, commitTime, instant, true, true);
      });
      table.getMetaClient().reloadActiveTimeline();
    }
    return recordsAndPendingClusteringFileGroups.getLeft();
  }

  @Override
  public HoodieWriteMetadata<HoodieData<WriteStatus>> execute(HoodieData<HoodieRecord<T>> inputRecords) {
    // Cache the tagged records, so we don't end up computing both
    // TODO: Consistent contract in HoodieWriteClient regarding preppedRecord storage level handling
    JavaRDD<HoodieRecord<T>> inputRDD = HoodieJavaRDD.getJavaRDD(inputRecords);
    if (inputRDD.getStorageLevel() == StorageLevel.NONE()) {
      inputRDD.persist(StorageLevel.MEMORY_AND_DISK_SER());
    } else {
      LOG.info("RDD PreppedRecords was persisted at: " + inputRDD.getStorageLevel());
    }

    WorkloadProfile workloadProfile = null;

    //预估工作负载
    if (isWorkloadProfileNeeded()) {
      context.setJobStatus(this.getClass().getSimpleName(), "Building workload profile");
      // buildProfile 方法是重点,内部会调用countByKey方法 来统计 每个分区路径 下的将来的工作的元数据信息
      workloadProfile = new WorkloadProfile(buildProfile(inputRecords), operationType, table.getIndex().canIndexLogFiles());//能否索引log files,bloom索引和桶索引都是false
      LOG.info("Input workload profile :" + workloadProfile);
    }

    // 根据工作负载拿到分区器
    // 这个分区器很不一般, 得对insert 和 update的数据 均衡地 做分区
    //
    final Partitioner partitioner = getPartitioner(workloadProfile);

    if (isWorkloadProfileNeeded()) {
      // 在 hoodie 的相关目录创建  .commit.inflight 后缀的文件, 而文件的内容就是 workloadProfile 的信息
      saveWorkloadProfileMetadataToInflight(workloadProfile, instantTime);
    }

    // handle records update with clustering
    HoodieData<HoodieRecord<T>> inputRecordsWithClusteringUpdate = clusteringHandleUpdate(inputRecords);

    context.setJobStatus(this.getClass().getSimpleName(), "Doing partition and writing data");

    //该操作内部会包含真正的实际写入操作的入口  (cow 和 mor 的不同点 也会在这里体现 )
    //十分重要
    HoodieData<WriteStatus> writeStatuses = mapPartitionsAsRDD(inputRecordsWithClusteringUpdate, partitioner);

    //设置,更新此次写入操作的元数据,并返回
    HoodieWriteMetadata<HoodieData<WriteStatus>> result = new HoodieWriteMetadata<>();

    updateIndexAndCommitIfNeeded(writeStatuses, result);
    return result;
  }

  private Pair<HashMap<String, WorkloadStat>, WorkloadStat> buildProfile(HoodieData<HoodieRecord<T>> inputRecords) {
    //该map 的key 会存储 分区路径; 一个 WorkloadStat对象包含一个分区路径将来的工作信息
    HashMap<String, WorkloadStat> partitionPathStatMap = new HashMap<>();

    WorkloadStat globalStat = new WorkloadStat();

    Map<Tuple2<String, Option<HoodieRecordLocation>>, Long> partitionLocationCounts = inputRecords
        .mapToPair(record -> Pair.of(
                // 之所以这里调用Option.ofNullable 方法 , 是因为 update部分的数据目前被封装了地址 ; 但是 insert 部分的数据目前没有被封装地址
                // 封装地址的逻辑在 BaseWriteHelper的类的tag 方法中
            new Tuple2<>(record.getPartitionPath(), Option.ofNullable(record.getCurrentLocation())), record))
        .countByKey();

    // count the number of both inserts and updates in each partition, update the counts to workLoadStats
    for (Map.Entry<Tuple2<String, Option<HoodieRecordLocation>>, Long> e : partitionLocationCounts.entrySet()) {
      String partitionPath = e.getKey()._1();
      Long count = e.getValue();
      Option<HoodieRecordLocation> locOption = e.getKey()._2();

      if (!partitionPathStatMap.containsKey(partitionPath)) {
        partitionPathStatMap.put(partitionPath, new WorkloadStat());
      }

      // 如果打tag 的时候没有打入地址,则属于新增的数据,以后进行insert
      // 如果打入了地址,则属于更新的数据,以后进行update
      // 不管文件地址存不存在,但是分区路径是一定都有的
      if (locOption.isPresent()) {
        // update
        partitionPathStatMap.get(partitionPath).addUpdates(locOption.get(), count);
        globalStat.addUpdates(locOption.get(), count);
      } else {
        // insert
        partitionPathStatMap.get(partitionPath).addInserts(count);
        globalStat.addInserts(count);
      }
    }
    //partitionPathStatMap 存储每个分区路径下更新和插入的数据量;  globalStat 存储全局的跟新和插入数据量
    return Pair.of(partitionPathStatMap, globalStat);
  }

  protected Partitioner getPartitioner(WorkloadProfile profile) {
    //hoodie.storage.layout.partitioner.class
    //这里用户可以配置自己的分区器
    // spark 桶索引 会反射创建 org.apache.hudi.table.action.commit.SparkBucketIndexPartitioner
    Option<String> layoutPartitionerClass = table.getStorageLayout().layoutPartitionerClass();
    if (layoutPartitionerClass.isPresent()) {
      return getLayoutPartitioner(profile, layoutPartitionerClass.get());
    } else if (WriteOperationType.isChangingRecords(operationType)) {
      // cow 表得到UpsertPartitioner    mor表得到 SparkUpsertDeltaCommitPartitioner
      // SparkUpsertDeltaCommitPartitioner 是 UpsertPartitioner的子类,重写了操作小文件的逻辑
      return getUpsertPartitioner(profile);
    } else {
      return getInsertPartitioner(profile);
    }
  }

  private HoodieData<WriteStatus> mapPartitionsAsRDD(HoodieData<HoodieRecord<T>> dedupedRecords, Partitioner partitioner) {
    JavaPairRDD<Tuple2<HoodieKey, Option<HoodieRecordLocation>>, HoodieRecord<T>> mappedRDD = HoodieJavaPairRDD.getJavaPairRDD(
        dedupedRecords.mapToPair(record -> Pair.of(new Tuple2<>(record.getKey(), Option.ofNullable(record.getCurrentLocation())), record)));

    JavaPairRDD<Tuple2<HoodieKey, Option<HoodieRecordLocation>>, HoodieRecord<T>> partitionedRDD;
    // 如果文件是hfile 格式,则需要排序
    if (table.requireSortedRecords()) {
      // Partition and sort within each partition as a single step. This is faster than partitioning first and then
      // applying a sort.
      Comparator<Tuple2<HoodieKey, Option<HoodieRecordLocation>>> comparator = (Comparator<Tuple2<HoodieKey, Option<HoodieRecordLocation>>> & Serializable)(t1, t2) -> {
        HoodieKey key1 = t1._1;
        HoodieKey key2 = t2._1;
        return key1.getRecordKey().compareTo(key2.getRecordKey());
      };

      partitionedRDD = mappedRDD.repartitionAndSortWithinPartitions(partitioner, comparator);
    } else {
      // Partition only
      partitionedRDD = mappedRDD.partitionBy(partitioner);
    }

    return HoodieJavaRDD.of(partitionedRDD.map(Tuple2::_2).mapPartitionsWithIndex((partition, recordItr) -> {
      if (WriteOperationType.isChangingRecords(operationType)) {
        return handleUpsertPartition(instantTime, partition, recordItr, partitioner);
      } else {
        return handleInsertPartition(instantTime, partition, recordItr, partitioner);
      }
    }, true).flatMap(List::iterator));
  }

  protected HoodieData<WriteStatus> updateIndex(HoodieData<WriteStatus> writeStatuses, HoodieWriteMetadata<HoodieData<WriteStatus>> result) {
    // cache writeStatusRDD before updating index, so that all actions before this are not triggered again for future
    // RDD actions that are performed after updating the index.
    writeStatuses.persist(config.getString(WRITE_STATUS_STORAGE_LEVEL_VALUE));
    Instant indexStartTime = Instant.now();
    // Update the index back
    HoodieData<WriteStatus> statuses = table.getIndex().updateLocation(writeStatuses, context, table);

    //设置 此次写入的 元数据信息 (用入参的writeStatuses 来更新 入参result)
    result.setIndexUpdateDuration(Duration.between(indexStartTime, Instant.now()));
    result.setWriteStatuses(statuses);
    return statuses;
  }

  protected void updateIndexAndCommitIfNeeded(HoodieData<WriteStatus> writeStatusRDD, HoodieWriteMetadata<HoodieData<WriteStatus>> result) {
    updateIndex(writeStatusRDD, result);
    result.setPartitionToReplaceFileIds(getPartitionToReplacedFileIds(result));
    commitOnAutoCommit(result);
  }

  @Override
  protected String getCommitActionType() {
    return  table.getMetaClient().getCommitActionType();
  }

  @Override
  protected void setCommitMetadata(HoodieWriteMetadata<HoodieData<WriteStatus>> result) {
    result.setCommitMetadata(Option.of(CommitUtils.buildMetadata(result.getWriteStatuses().map(WriteStatus::getStat).collectAsList(),
        result.getPartitionToReplaceFileIds(),
        extraMetadata, operationType, getSchemaToStoreInCommit(), getCommitActionType())));
  }

  @Override
  protected void commit(Option<Map<String, String>> extraMetadata, HoodieWriteMetadata<HoodieData<WriteStatus>> result) {
    context.setJobStatus(this.getClass().getSimpleName(), "Commit write status collect");
    commit(extraMetadata, result, result.getWriteStatuses().map(WriteStatus::getStat).collectAsList());
  }

  protected void commit(Option<Map<String, String>> extraMetadata, HoodieWriteMetadata<HoodieData<WriteStatus>> result, List<HoodieWriteStat> writeStats) {
    String actionType = getCommitActionType();
    LOG.info("Committing " + instantTime + ", action Type " + actionType + ", operation Type " + operationType);
    result.setCommitted(true);
    result.setWriteStats(writeStats);
    // Finalize write
    finalizeWrite(instantTime, writeStats, result);
    try {
      HoodieActiveTimeline activeTimeline = table.getActiveTimeline();
      HoodieCommitMetadata metadata = result.getCommitMetadata().get();
      writeTableMetadata(metadata, actionType);
      activeTimeline.saveAsComplete(new HoodieInstant(true, getCommitActionType(), instantTime),
          Option.of(metadata.toJsonString().getBytes(StandardCharsets.UTF_8)));
      LOG.info("Committed " + instantTime);
      result.setCommitMetadata(Option.of(metadata));
    } catch (IOException e) {
      throw new HoodieCommitException("Failed to complete commit " + config.getBasePath() + " at time " + instantTime,
          e);
    }
  }

  protected Map<String, List<String>> getPartitionToReplacedFileIds(HoodieWriteMetadata<HoodieData<WriteStatus>> writeStatuses) {
    return Collections.emptyMap();
  }

  @SuppressWarnings("unchecked")
  protected Iterator<List<WriteStatus>> handleUpsertPartition(String instantTime, Integer partition, Iterator recordItr,
                                                              Partitioner partitioner) {
    SparkHoodiePartitioner upsertPartitioner = (SparkHoodiePartitioner) partitioner;
    //这里的partition为分区编号
    BucketInfo binfo = upsertPartitioner.getBucketInfo(partition);
    //这里的bucketType 只有 upsert 和 insert
    BucketType btype = binfo.bucketType;
    try {
      // 注意 如果是 mor 表 ,走的一定是 BaseSparkDeltaCommitActionExecutor 的 重写的方法
      if (btype.equals(BucketType.INSERT)) {

        // SparkLazyInsertIterable
        return handleInsert(binfo.fileIdPrefix, recordItr);
      } else if (btype.equals(BucketType.UPDATE)) {

        // HoodieAppendHandle.doAppend()
        // HoodieMergeHandle =>  HoodieMergeHelper.newInstance().runMerge
        return handleUpdate(binfo.partitionPath, binfo.fileIdPrefix, recordItr);
      } else {
        throw new HoodieUpsertException("Unknown bucketType " + btype + " for partition :" + partition);
      }
    } catch (Throwable t) {
      String msg = "Error upserting bucketType " + btype + " for partition :" + partition;
      LOG.error(msg, t);
      throw new HoodieUpsertException(msg, t);
    }
  }

  protected Iterator<List<WriteStatus>> handleInsertPartition(String instantTime, Integer partition, Iterator recordItr,
                                                              Partitioner partitioner) {
    return handleUpsertPartition(instantTime, partition, recordItr, partitioner);
  }

  @Override
  public Iterator<List<WriteStatus>> handleUpdate(String partitionPath, String fileId,
                                                  Iterator<HoodieRecord<T>> recordItr)
      throws IOException {
    // This is needed since sometimes some buckets are never picked in getPartition() and end up with 0 records
    if (!recordItr.hasNext()) {
      LOG.info("Empty partition with fileId => " + fileId);
      return Collections.emptyIterator();
    }
    // these are updates
    HoodieMergeHandle upsertHandle = getUpdateHandle(partitionPath, fileId, recordItr);
    return handleUpdateInternal(upsertHandle, fileId);
  }

  protected Iterator<List<WriteStatus>> handleUpdateInternal(HoodieMergeHandle<?, ?, ?, ?> upsertHandle, String fileId)
      throws IOException {
    if (upsertHandle.getOldFilePath() == null) {
      throw new HoodieUpsertException(
          "Error in finding the old file path at commit " + instantTime + " for fileId: " + fileId);
    } else {
      HoodieMergeHelper.newInstance().runMerge(table, upsertHandle);
    }

    // TODO(vc): This needs to be revisited
    if (upsertHandle.getPartitionPath() == null) {
      LOG.info("Upsert Handle has partition path as null " + upsertHandle.getOldFilePath() + ", "
          + upsertHandle.writeStatuses());
    }
    // 此时迭代器中只有一个元素 , 且这个元素是一个 List<WriteStatus>
    return Collections.singletonList(upsertHandle.writeStatuses()).iterator();
  }

  protected HoodieMergeHandle getUpdateHandle(String partitionPath, String fileId, Iterator<HoodieRecord<T>> recordItr) {
    if (table.requireSortedRecords()) {
      return new HoodieSortedMergeHandle<>(config, instantTime, (HoodieSparkTable) table, recordItr, partitionPath, fileId, taskContextSupplier,
          keyGeneratorOpt);
    } else if (!WriteOperationType.isChangingRecords(operationType) && config.allowDuplicateInserts()) {
      return new HoodieConcatHandle<>(config, instantTime, table, recordItr, partitionPath, fileId, taskContextSupplier, keyGeneratorOpt);
    } else {
      return new HoodieMergeHandle<>(config, instantTime, table, recordItr, partitionPath, fileId, taskContextSupplier, keyGeneratorOpt);
    }
  }

  @Override
  public Iterator<List<WriteStatus>> handleInsert(String idPfx, Iterator<HoodieRecord<T>> recordItr)
      throws Exception {
    // This is needed since sometimes some buckets are never picked in getPartition() and end up with 0 records
    if (!recordItr.hasNext()) {
      LOG.info("Empty partition");
      return Collections.emptyIterator();
    }
    return new SparkLazyInsertIterable<>(recordItr, true, config, instantTime, table, idPfx,
        taskContextSupplier, new CreateHandleFactory<>());
  }

  public Partitioner getUpsertPartitioner(WorkloadProfile profile) {
    if (profile == null) {
      throw new HoodieUpsertException("Need workload profile to construct the upsert partitioner.");
    }
    return new UpsertPartitioner<>(profile, context, table, config);
  }

  public Partitioner getInsertPartitioner(WorkloadProfile profile) {
    return getUpsertPartitioner(profile);
  }

  public Partitioner getLayoutPartitioner(WorkloadProfile profile, String layoutPartitionerClass) {
    return (Partitioner) ReflectionUtils.loadClass(layoutPartitionerClass,
        new Class[] { WorkloadProfile.class, HoodieEngineContext.class, HoodieTable.class, HoodieWriteConfig.class },
        profile, context, table, config);
  }

  @Override
  protected void runPrecommitValidators(HoodieWriteMetadata<HoodieData<WriteStatus>> writeMetadata) {
    SparkValidatorUtils.runValidators(config, writeMetadata, context, table, instantTime);
  }
}
