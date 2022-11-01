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

import org.apache.hudi.client.common.HoodieSparkEngineContext;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.fs.FSUtils;
import org.apache.hudi.common.model.HoodieBaseFile;
import org.apache.hudi.common.model.HoodieCommitMetadata;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecordLocation;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.model.HoodieWriteStat;
import org.apache.hudi.common.table.timeline.HoodieInstant;
import org.apache.hudi.common.table.timeline.HoodieTimeline;
import org.apache.hudi.common.util.NumericUtils;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.common.util.collection.Pair;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.table.HoodieTable;
import org.apache.hudi.table.WorkloadProfile;
import org.apache.hudi.table.WorkloadStat;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import scala.Tuple2;

/**
 * Packs incoming records to be upserted, into buckets (1 bucket = 1 RDD partition).
 */
public class UpsertPartitioner<T extends HoodieRecordPayload<T>> extends SparkHoodiePartitioner<T> {

  private static final Logger LOG = LogManager.getLogger(UpsertPartitioner.class);

  /**
   * List of all small files to be corrected.
   */
  protected List<SmallFile> smallFiles = new ArrayList<>();
  /**
   * Total number of RDD partitions, is determined by total buckets we want to pack the incoming workload into.
   */
  private int totalBuckets = 0;
  /**
   * Helps decide which bucket an incoming update should go to.
   */
  private HashMap<String, Integer> updateLocationToBucket;//存储update部分的 分桶信息
  /**
   * Helps us pack inserts into 1 or more buckets depending on number of incoming records.
   */
  private HashMap<String, List<InsertBucketCumulativeWeightPair>> partitionPathToInsertBucketInfos; //存储insert 部分的 分桶信息 (不过存储结构有所不同)


  /**
   * Remembers what type each bucket is for later.
   */
  //存储 桶编号 和对应的桶信息
  private HashMap<Integer, BucketInfo> bucketInfoMap;

  protected final HoodieWriteConfig config;

  public UpsertPartitioner(WorkloadProfile profile, HoodieEngineContext context, HoodieTable table,
      HoodieWriteConfig config) {
    super(profile, table);
    updateLocationToBucket = new HashMap<>();
    partitionPathToInsertBucketInfos = new HashMap<>();
    bucketInfoMap = new HashMap<>();
    this.config = config;
    //每个更新的Location 对应 一个运行时分区
    assignUpdates(profile);
    //根据数据量 来决定 运行时分区
    assignInserts(profile, context);

    LOG.info("Total Buckets :" + totalBuckets + ", buckets info => " + bucketInfoMap + ", \n"
        + "Partition to insert buckets => " + partitionPathToInsertBucketInfos + ", \n"
        + "UpdateLocations mapped to buckets =>" + updateLocationToBucket);
  }

  private void assignUpdates(WorkloadProfile profile) {

    Set<Entry<String, WorkloadStat>> partitionStatEntries = profile.getInputPartitionPathStatMap().entrySet();
    // 从 WorkloadProfile 所包含的 update 部分的 信息 来做遍历
    // 遍历每个分区路径下的 每一个更新文件(地址)
    // 最终每一个更新文件(地址) 对应 一个计算时分区 (在这里就是分桶) ,桶的编号从0 开始,顺序递增
    for (Map.Entry<String, WorkloadStat> partitionStat : partitionStatEntries) {  // 该map 的key 是分区路径

      WorkloadStat outputWorkloadStats = profile.getOutputPartitionPathStatMap().getOrDefault(partitionStat.getKey(), new WorkloadStat());
      for (Map.Entry<String, Pair<String, Long>> updateLocEntry :
          partitionStat.getValue().getUpdateLocationToCount().entrySet()) {

        addUpdateBucket(partitionStat.getKey(), updateLocEntry.getKey());

        if (profile.hasOutputWorkLoadStats()) {
          HoodieRecordLocation hoodieRecordLocation = new HoodieRecordLocation(updateLocEntry.getValue().getKey(), updateLocEntry.getKey());
          outputWorkloadStats.addUpdates(hoodieRecordLocation, updateLocEntry.getValue().getValue());
        }
      }
      if (profile.hasOutputWorkLoadStats()) {
        profile.updateOutputPartitionPathStatMap(partitionStat.getKey(), outputWorkloadStats);
      }
    }
  }

  //要主要hdfs 文件存储中的分桶   和    计算时内存中的分桶的区别
  private int addUpdateBucket(String partitionPath, String fileIdHint) {
    int bucket = totalBuckets;
    //注意 分桶编号是 此时的totalBuckets,这和 本类assignInserts 方法中 给insert部分 的数据分配桶的时候 相呼应
    updateLocationToBucket.put(fileIdHint, bucket);

    BucketInfo bucketInfo = new BucketInfo(BucketType.UPDATE, fileIdHint, partitionPath);
    bucketInfoMap.put(totalBuckets, bucketInfo);
    totalBuckets++;
    return bucket;
  }

  /**
   * Get the in pending clustering fileId for each partition path.
   * @return partition path to pending clustering file groups id
   */
  private Map<String, Set<String>> getPartitionPathToPendingClusteringFileGroupsId() {
    Map<String, Set<String>>  partitionPathToInPendingClusteringFileId =
        table.getFileSystemView().getFileGroupsInPendingClustering()
            .map(fileGroupIdAndInstantPair ->
                Pair.of(fileGroupIdAndInstantPair.getKey().getPartitionPath(), fileGroupIdAndInstantPair.getKey().getFileId()))
            .collect(Collectors.groupingBy(Pair::getKey, Collectors.mapping(Pair::getValue, Collectors.toSet())));
    return partitionPathToInPendingClusteringFileId;
  }

  /**
   * Exclude small file handling for clustering since update path is not supported.
   * @param pendingClusteringFileGroupsId  pending clustering file groups id of partition
   * @param smallFiles small files of partition
   * @return smallFiles not in clustering
   */
  private List<SmallFile> filterSmallFilesInClustering(final Set<String> pendingClusteringFileGroupsId, final List<SmallFile> smallFiles) {
    if (!pendingClusteringFileGroupsId.isEmpty()) {
      return smallFiles.stream()
          .filter(smallFile -> !pendingClusteringFileGroupsId.contains(smallFile.location.getFileId())).collect(Collectors.toList());
    } else {
      return smallFiles;
    }
  }

  private void assignInserts(WorkloadProfile profile, HoodieEngineContext context) {
    // for new inserts, compute buckets depending on how many records we have for each partition
    Set<String> partitionPaths = profile.getPartitionPaths();

    //平均每条记录的字节数 (这个估算的方法特别重要;我们在其他的系统中可以借鉴)
    long averageRecordSize =
        averageBytesPerRecord(table.getMetaClient().getActiveTimeline().getCommitTimeline().filterCompletedInstants(), config);
    LOG.info("AvgRecordSize => " + averageRecordSize);

    // 获取每个分区路径下小文件列表
    Map<String, List<SmallFile>> partitionSmallFilesMap =
        getSmallFilesForPartitions(new ArrayList<String>(partitionPaths), context);

    // 获取每个分区路径下 处于挂起状态的 文件组集合
    Map<String, Set<String>> partitionPathToPendingClusteringFileGroupsId = getPartitionPathToPendingClusteringFileGroupsId();

    //遍历分区路径
    for (String partitionPath : partitionPaths) {

      //得到该分区路径下 工作负载的统计量
      WorkloadStat pStat = profile.getWorkloadStat(partitionPath);
      WorkloadStat outputWorkloadStats = profile.getOutputPartitionPathStatMap().getOrDefault(partitionPath, new WorkloadStat());

      if (pStat.getNumInserts() > 0) {

        //针对特定的分区路径, 排除掉处于挂起状态的文件组中的 小文件
        List<SmallFile> smallFiles =
            filterSmallFilesInClustering(partitionPathToPendingClusteringFileGroupsId.getOrDefault(partitionPath, Collections.emptySet()),
                partitionSmallFilesMap.getOrDefault(partitionPath, new ArrayList<>()));

        this.smallFiles.addAll(smallFiles);

        LOG.info("For partitionPath : " + partitionPath + " Small Files => " + smallFiles);

        long totalUnassignedInserts = pStat.getNumInserts();//待插入该分区路径下的数据量
        List<Integer> bucketNumbers = new ArrayList<>();//记录桶编号
        List<Long> recordsPerBucket = new ArrayList<>();//记录每个桶的记录数

        // 遍历一个分区路径下的 所有小文件
        // 优先用小文件去 承接 新来的数据 , 防止小文件过多
        for (SmallFile smallFile : smallFiles) {
            //计算该小文件还能承载的数据量
          long recordsToAppend = Math.min((config.getParquetMaxFileSize() - smallFile.sizeBytes) / averageRecordSize,
              totalUnassignedInserts);
          if (recordsToAppend > 0) {
            // create a new bucket or re-use an existing bucket
            int bucket;

            if (updateLocationToBucket.containsKey(smallFile.location.getFileId())) {
              // 如果该小文件,在此次任务中已经被 update 命中过; 则不会增加计算时分区(桶编号)了;
              // 桶编号直接从updateLocationToBucket中取;  不会给 updateLocationToBucket 添加新元素
              // 否则会 增加计算时分区, else语句块中调用的 addUpdateBucket 方法 会增加计算时分桶
              bucket = updateLocationToBucket.get(smallFile.location.getFileId());
              LOG.info("Assigning " + recordsToAppend + " inserts to existing update bucket " + bucket);
            } else {
              // 会给 updateLocationToBucket 添加新元素,会增加新的计算时分区,且桶的编号为此时成员变量 totalBuckets 的值
              bucket = addUpdateBucket(partitionPath, smallFile.location.getFileId());
              LOG.info("Assigning " + recordsToAppend + " inserts to new update bucket " + bucket);
            }

            if (profile.hasOutputWorkLoadStats()) {
              // 记录此次 insert 操作的基本信息
              outputWorkloadStats.addInserts(smallFile.location, recordsToAppend);
            }
            bucketNumbers.add(bucket);
            recordsPerBucket.add(recordsToAppend);
            totalUnassignedInserts -= recordsToAppend;
            if (totalUnassignedInserts <= 0) {
              // stop the loop when all the inserts are assigned
              break;
            }
          }
        }

        // 如果已有的小文件不足以承载新来的数据,则另外分配桶
        if (totalUnassignedInserts > 0) {
          // hoodie.copyonwrite.insert.split.size
          long insertRecordsPerBucket = config.getCopyOnWriteInsertSplitSize();
          // hoodie.copyonwrite.insert.auto.split
          if (config.shouldAutoTuneInsertSplits()) {
            //hoodie.parquet.max.file.size
            // 计算一个文件最多能够容纳的数据量
            insertRecordsPerBucket = config.getParquetMaxFileSize() / averageRecordSize;
          }
            //计算还剩下的数据总量,一共需要多少桶(文件)来容纳
          int insertBuckets = (int) Math.ceil((1.0 * totalUnassignedInserts) / insertRecordsPerBucket);
          LOG.info("After small file assignment: unassignedInserts => " + totalUnassignedInserts
              + ", totalInsertBuckets => " + insertBuckets + ", recordsPerBucket => " + insertRecordsPerBucket);
          for (int b = 0; b < insertBuckets; b++) {
            bucketNumbers.add(totalBuckets);
            if (b < insertBuckets - 1) {
              recordsPerBucket.add(insertRecordsPerBucket);
            } else {
              //尾端 单独处理
              recordsPerBucket.add(totalUnassignedInserts - (insertBuckets - 1) * insertRecordsPerBucket);
            }
            //创建该桶  待写入文件的前缀, 并封装 桶信息
            BucketInfo bucketInfo = new BucketInfo(BucketType.INSERT, FSUtils.createNewFileIdPfx(), partitionPath);
            bucketInfoMap.put(totalBuckets, bucketInfo);
            if (profile.hasOutputWorkLoadStats()) {
              outputWorkloadStats.addInserts(new HoodieRecordLocation(HoodieWriteStat.NULL_COMMIT, bucketInfo.getFileIdPrefix()), recordsPerBucket.get(recordsPerBucket.size() - 1));
            }
            totalBuckets++;
          }
        }

        // Go over all such buckets, and assign weights as per amount of incoming inserts.
        List<InsertBucketCumulativeWeightPair> insertBuckets = new ArrayList<>();
        double currentCumulativeWeight = 0;

        for (int i = 0; i < bucketNumbers.size(); i++) {
          InsertBucket bkt = new InsertBucket();
          bkt.bucketNumber = bucketNumbers.get(i);
          //权重 为 该桶的数据量 除以 进入该分区路径的数据量
          bkt.weight = (1.0 * recordsPerBucket.get(i)) / pStat.getNumInserts();
          //更新 累计 权重
          currentCumulativeWeight += bkt.weight;
          insertBuckets.add(new InsertBucketCumulativeWeightPair(bkt, currentCumulativeWeight));
        }
        LOG.info("Total insert buckets for partition path " + partitionPath + " => " + insertBuckets);
        partitionPathToInsertBucketInfos.put(partitionPath, insertBuckets);

      }
      if (profile.hasOutputWorkLoadStats()) {
        profile.updateOutputPartitionPathStatMap(partitionPath, outputWorkloadStats);
      }
    }
  }

  private Map<String, List<SmallFile>> getSmallFilesForPartitions(List<String> partitionPaths, HoodieEngineContext context) {
    JavaSparkContext jsc = HoodieSparkEngineContext.getSparkContext(context);
    Map<String, List<SmallFile>> partitionSmallFilesMap = new HashMap<>();

    if (config.getParquetSmallFileLimit() <= 0) {
      return partitionSmallFilesMap;
    }

    if (partitionPaths != null && partitionPaths.size() > 0) {
      context.setJobStatus(this.getClass().getSimpleName(), "Getting small files from partitions");
      JavaRDD<String> partitionPathRdds = jsc.parallelize(partitionPaths, partitionPaths.size());
      partitionSmallFilesMap = partitionPathRdds.mapToPair((PairFunction<String, String, List<SmallFile>>)
          partitionPath -> new Tuple2<>(partitionPath, getSmallFiles(partitionPath))).collectAsMap();
    }

    return partitionSmallFilesMap;
  }

  /**
   * Returns a list of small files in the given partition path.
   */
  protected List<SmallFile> getSmallFiles(String partitionPath) {

    // smallFiles only for partitionPath
    List<SmallFile> smallFileLocations = new ArrayList<>();

    HoodieTimeline commitTimeline = table.getMetaClient().getCommitsTimeline().filterCompletedInstants();

    if (!commitTimeline.empty()) { // if we have some commits
      HoodieInstant latestCommitTime = commitTimeline.lastInstant().get();
      List<HoodieBaseFile> allFiles = table.getBaseFileOnlyView()
          .getLatestBaseFilesBeforeOrOn(partitionPath, latestCommitTime.getTimestamp()).collect(Collectors.toList());

      for (HoodieBaseFile file : allFiles) {
        if (file.getFileSize() < config.getParquetSmallFileLimit()) {
          String filename = file.getFileName();
          SmallFile sf = new SmallFile();
          sf.location = new HoodieRecordLocation(FSUtils.getCommitTime(filename), FSUtils.getFileId(filename));
          sf.sizeBytes = file.getFileSize();
          smallFileLocations.add(sf);
        }
      }
    }

    return smallFileLocations;
  }

  public List<BucketInfo> getBucketInfos() {
    return Collections.unmodifiableList(new ArrayList<>(bucketInfoMap.values()));
  }

  public BucketInfo getBucketInfo(int bucketNumber) {
    return bucketInfoMap.get(bucketNumber);
  }

  public List<InsertBucketCumulativeWeightPair> getInsertBuckets(String partitionPath) {
    return partitionPathToInsertBucketInfos.get(partitionPath);
  }

  @Override
  public int numPartitions() {
    return totalBuckets;
  }

  @Override
  public int getNumPartitions() {
    return totalBuckets;
  }

  @Override
  public int getPartition(Object key) {
    Tuple2<HoodieKey, Option<HoodieRecordLocation>> keyLocation =
        (Tuple2<HoodieKey, Option<HoodieRecordLocation>>) key;

     //location存在是属于update的情况,不存在是属于insert的情况
    if (keyLocation._2().isPresent()) {
      HoodieRecordLocation location = keyLocation._2().get();
      return updateLocationToBucket.get(location.getFileId());
    } else {
      String partitionPath = keyLocation._1().getPartitionPath();

      List<InsertBucketCumulativeWeightPair> targetBuckets = partitionPathToInsertBucketInfos.get(partitionPath);

      // pick the target bucket to use based on the weights.
      final long totalInserts = Math.max(1, profile.getWorkloadStat(partitionPath).getNumInserts());
      final long hashOfKey = NumericUtils.getMessageDigestHash("MD5", keyLocation._1().getRecordKey());
      final double r = 1.0 * Math.floorMod(hashOfKey, totalInserts) / totalInserts;

      //InsertBucketCumulativeWeightPair 类中的 compareTo 方法 只和 右边的累计权重有关 (传参的 r就是累计权重的赋值)

      //根据累计权重  去确定index ,再根据index确定最终的桶编号(index是 targetBuckets 的列表编号,和桶编号要区分)
      // 如果该分区有10条数据 , 计划每个桶只存3条数据,那么累计权重如下 (我认为累计权重 这个称呼不好)
      //   index                1      2     3      4
      // cumulativeWeight      0.3    0.6   0.9    1.0
      int index = Collections.binarySearch(targetBuckets, new InsertBucketCumulativeWeightPair(new InsertBucket(), r));

      if (index >= 0) {
        return targetBuckets.get(index).getKey().bucketNumber;
      }

      if ((-1 * index - 1) < targetBuckets.size()) {
        return targetBuckets.get((-1 * index - 1)).getKey().bucketNumber;
      }

      // return first one, by default
      return targetBuckets.get(0).getKey().bucketNumber;
    }
  }

  /**
   * Obtains the average record size based on records written during previous commits. Used for estimating how many
   * records pack into one file.
   */
  protected static long averageBytesPerRecord(HoodieTimeline commitTimeline, HoodieWriteConfig hoodieWriteConfig) {
    long avgSize = hoodieWriteConfig.getCopyOnWriteRecordSizeEstimate();
    long fileSizeThreshold = (long) (hoodieWriteConfig.getRecordSizeEstimationThreshold() * hoodieWriteConfig.getParquetSmallFileLimit());
    try {
      if (!commitTimeline.empty()) {
        // Go over the reverse ordered commits to get a more recent estimate of average record size.
        Iterator<HoodieInstant> instants = commitTimeline.getReverseOrderedInstants().iterator();
        while (instants.hasNext()) {
          HoodieInstant instant = instants.next();
          HoodieCommitMetadata commitMetadata = HoodieCommitMetadata
              .fromBytes(commitTimeline.getInstantDetails(instant).get(), HoodieCommitMetadata.class);
          long totalBytesWritten = commitMetadata.fetchTotalBytesWritten();
          long totalRecordsWritten = commitMetadata.fetchTotalRecordsWritten();
          if (totalBytesWritten > fileSizeThreshold && totalRecordsWritten > 0) {
            avgSize = (long) Math.ceil((1.0 * totalBytesWritten) / totalRecordsWritten);
            break;
          }
        }
      }
    } catch (Throwable t) {
      // make this fail safe.
      LOG.error("Error trying to compute average bytes/record ", t);
    }
    return avgSize;
  }
}
