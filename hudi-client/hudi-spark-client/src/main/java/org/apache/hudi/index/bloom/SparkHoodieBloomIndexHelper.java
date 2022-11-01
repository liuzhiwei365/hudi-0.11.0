/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.hudi.index.bloom;

import org.apache.hudi.common.data.HoodieData;
import org.apache.hudi.common.data.HoodiePairData;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieKey;
import org.apache.hudi.common.model.HoodieRecordLocation;
import org.apache.hudi.common.util.collection.Pair;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.data.HoodieJavaPairRDD;
import org.apache.hudi.data.HoodieJavaRDD;
import org.apache.hudi.io.HoodieKeyLookupResult;
import org.apache.hudi.table.HoodieTable;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.Partitioner;
import org.apache.spark.api.java.JavaRDD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import scala.Tuple2;

import static org.apache.hudi.metadata.HoodieTableMetadataUtil.getCompletedMetadataPartitions;
import static org.apache.hudi.metadata.MetadataPartitionType.BLOOM_FILTERS;

/**
 * Helper for {@link HoodieBloomIndex} containing Spark-specific logic.
 */
public class SparkHoodieBloomIndexHelper extends BaseHoodieBloomIndexHelper {

  private static final Logger LOG = LogManager.getLogger(SparkHoodieBloomIndexHelper.class);

  private static final SparkHoodieBloomIndexHelper SINGLETON_INSTANCE =
      new SparkHoodieBloomIndexHelper();

  private SparkHoodieBloomIndexHelper() {
  }

  public static SparkHoodieBloomIndexHelper getInstance() {
    return SINGLETON_INSTANCE;
  }

  @Override
  public HoodiePairData<HoodieKey, HoodieRecordLocation> findMatchingFilesForRecordKeys(
      HoodieWriteConfig config, HoodieEngineContext context, HoodieTable hoodieTable,
      HoodiePairData<String, String> partitionRecordKeyPairs, // <分区路径, recordKey>
      HoodieData<Pair<String, HoodieKey>> fileComparisonPairs, // Pair<文件id , HoodieKey<recordKey , 分区路径>>
      Map<String, List<BloomIndexFileInfo>> partitionToFileInfo,
      Map<String, Long> recordsPerPartition) { // 新数据的每个分区的记录数

    //将普通数据结构变为 分布式的RDD结构
    JavaRDD<Tuple2<String, HoodieKey>> fileComparisonsRDD =
        HoodieJavaRDD.getJavaRDD(fileComparisonPairs)
            .map(pair -> new Tuple2<>(pair.getLeft(), pair.getRight()));

    //新数据所涉及到分区路径个数
    int inputParallelism = HoodieJavaPairRDD.getJavaPairRDD(partitionRecordKeyPairs).partitions().size();
    //hoodie.bloom.index.parallelism
    int joinParallelism = Math.max(inputParallelism, config.getBloomIndexParallelism());
    LOG.info("InputParallelism: ${" + inputParallelism + "}, IndexParallelism: ${"
        + config.getBloomIndexParallelism() + "}");


    JavaRDD<List<HoodieKeyLookupResult>> keyLookupResultRDD;
    //hoodie.bloom.index.use.metadata
    if (config.getBloomIndexUseMetadata()
            //hoodie.table.metadata.partitions
        && getCompletedMetadataPartitions(hoodieTable.getMetaClient().getTableConfig())
        .contains(BLOOM_FILTERS.getPartitionPath())) {
      // Step 1: Sort by file id
      //Pair<文件id , HoodieKey<recordKey , 分区路径>>
      JavaRDD<Tuple2<String, HoodieKey>> sortedFileIdAndKeyPairs =
          fileComparisonsRDD.sortBy(Tuple2::_1, true, joinParallelism);

      // Step 2: Use bloom filter to filter and the actual log file to get the record location
      keyLookupResultRDD = sortedFileIdAndKeyPairs.mapPartitionsWithIndex(
          new HoodieMetadataBloomIndexCheckFunction(hoodieTable), true);

    } else if (config.useBloomIndexBucketizedChecking()) {
      //hoodie.bloom.index.bucketized.checking
      //计算每个文件组 在本次数据插入中,需要比较记录 的次数
      Map<String, Long> comparisonsPerFileGroup = computeComparisonsPerFileGroup(
          config, recordsPerPartition, partitionToFileInfo, fileComparisonsRDD, context);
      //hoodie.bloom.index.keys.per.bucket
      //hoodie.bloom.index.parallelism
      //根据记录数计划分区数 , 避免查找时数据倾斜分布
      Partitioner partitioner = new BucketizedBloomCheckPartitioner(joinParallelism, comparisonsPerFileGroup,
          config.getBloomIndexKeysPerBucket());

      //Pair<文件id , HoodieKey<recordKey , 分区路径>>  =>  Tuple2<Pair<文件id ,recordKey> , Pair<文件id , HoodieKey<recordKey , 分区路径>> >
      keyLookupResultRDD = fileComparisonsRDD.mapToPair(t -> new Tuple2<>(Pair.of(t._1, t._2.getRecordKey()), t))
           //用Pair<文件id ,recordKey> 作为分区的key; 注意BucketizedBloomCheckPartitioner内部的 getPartition方法
          .repartitionAndSortWithinPartitions(partitioner)
          .map(Tuple2::_2)
           //Pair<文件id , HoodieKey<recordKey , 分区路径>>
           //数据分片与桶数量一样   (一个文件组完整地包含多个桶)
          .mapPartitionsWithIndex(new HoodieBloomIndexCheckFunction(hoodieTable, config), true);

    } else {
           //数据分片与文件组数量一样
      keyLookupResultRDD = fileComparisonsRDD.sortBy(Tuple2::_1, true, joinParallelism)
          .mapPartitionsWithIndex(new HoodieBloomIndexCheckFunction(hoodieTable, config), true);
    }

    //keyLookupResultRDD 的类型 Iterator<List<HoodieKeyLookupResult>> ,
    // 迭代器的元素个数与 数据分片(计算时数据分区)个数一致
    // 这时候 数据分片的数量 有可能和等于桶的数量 也有可能等于文件组的数量

    // 如果数据分片数 等于桶数, 那么List 的容量等于 一个fileGroup 下 桶的数量
    // 如果数据分片数 等于文件组数 ,那么 List的 容量一定是 1 ,只含有一个元素
    return HoodieJavaPairRDD.of(keyLookupResultRDD.flatMap(List::iterator)
        .filter(lr -> lr.getMatchingRecordKeys().size() > 0)
        .flatMapToPair(lookupResult -> lookupResult.getMatchingRecordKeys().stream()
            .map(recordKey -> new Tuple2<>(new HoodieKey(recordKey, lookupResult.getPartitionPath()),
                new HoodieRecordLocation(lookupResult.getBaseInstantTime(), lookupResult.getFileId())))
            .collect(Collectors.toList()).iterator()));
  }

  /**
   * Compute the estimated number of bloom filter comparisons to be performed on each file group.
   */

  //计算每个文件组 在本次数据插入中,需要比较记录 的次数
  private Map<String, Long> computeComparisonsPerFileGroup(
      final HoodieWriteConfig config,
      final Map<String, Long> recordsPerPartition,
      final Map<String, List<BloomIndexFileInfo>> partitionToFileInfo,//Map<分区路径, List<文件信息>>
      final JavaRDD<Tuple2<String, HoodieKey>> fileComparisonsRDD, // Tuple2<文件id , HoodieKey<recordKey , 分区路径>>
      final HoodieEngineContext context) {
    Map<String, Long> fileToComparisons;

    //hoodie.bloom.index.prune.by.ranges
    //如果开启了range剪枝,需要比较记录 的次数少一些,效率高一些
    if (config.getBloomIndexPruneByRanges()) {
      context.setJobStatus(this.getClass().getSimpleName(), "Compute all comparisons needed between records and files");
      fileToComparisons = fileComparisonsRDD.mapToPair(t -> t).countByKey();
    } else {
      fileToComparisons = new HashMap<>();
      partitionToFileInfo.forEach((key, value) -> {
        for (BloomIndexFileInfo fileInfo : value) {
          // each file needs to be compared against all the records coming into the partition
          fileToComparisons.put(fileInfo.getFileId(), recordsPerPartition.get(key));
        }
      });
    }
    return fileToComparisons;
  }
}
