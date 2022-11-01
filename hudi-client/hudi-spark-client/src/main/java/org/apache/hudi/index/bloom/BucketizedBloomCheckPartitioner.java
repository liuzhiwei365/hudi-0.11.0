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

package org.apache.hudi.index.bloom;

import org.apache.hudi.common.util.NumericUtils;
import org.apache.hudi.common.util.collection.Pair;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.spark.Partitioner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Partitions bloom filter checks by spreading out comparisons across buckets of work.
 *
 * Each bucket incurs the following cost
 * 
 * <pre>
 *   1) Read bloom filter from file footer
 *   2) Check keys against bloom filter
 *   3) [Conditional] If any key had a hit, open file and check
 * </pre>
 *
 * The partitioner performs a two phase bin packing algorithm, to pack enough work into each bucket such that cost of
 * (1) & (3) is amortized. Also, avoids any skews in the sort based approach, by directly partitioning by the file to be
 * checked against and ensuring each partition has similar number of buckets. Performance tests show that this approach
 * could bound the amount of skew to std_dev(numberOfBucketsPerPartition) * cost of (3), lower than sort partitioning.
 *
 * Approach has two goals :
 * 
 * <pre>
 *   1) Pack as many buckets from same file group into same partition, to amortize cost of (1) and (2) further
 *   2) Spread buckets across partitions evenly to achieve skew reduction
 * </pre>
 */
public class BucketizedBloomCheckPartitioner extends Partitioner {

  private static final Logger LOG = LogManager.getLogger(BucketizedBloomCheckPartitioner.class);

  private int partitions;

  /**
   * Stores the final mapping of a file group to a list of partitions for its keys.
   */
  // 用来保存每个文件组对应的 计算分区编号  的列表
  private Map<String, List<Integer>> fileGroupToPartitions;

  /**
   * Create a partitioner that computes a plan based on provided workload characteristics.
   *
   * @param targetPartitions maximum number of partitions to target
   * @param fileGroupToComparisons number of expected comparisons per file group
   * @param keysPerBucket maximum number of keys to pack in a single bucket
   */
  public BucketizedBloomCheckPartitioner(int targetPartitions, Map<String, Long> fileGroupToComparisons,
      int keysPerBucket) {
    this.fileGroupToPartitions = new HashMap<>();

    // 用来存放每一个文件组需要多少个桶
    Map<String, Integer> bucketsPerFileGroup = new HashMap<>();
    // 桶可以认为是对文件组的进一步细化,计算每一个文件组需要多少个桶,keysPerBucket最终来源于参数 hoodie.bloom.index.keys.per.bucket
    fileGroupToComparisons.forEach((f, c) -> bucketsPerFileGroup.put(f, (int) Math.ceil((c * 1.0) / keysPerBucket)));
    // 计算本次插入需要的桶的总数量
    int totalBuckets = bucketsPerFileGroup.values().stream().mapToInt(i -> i).sum();
    // If totalBuckets > targetPartitions, no need to have extra partitions
    this.partitions = Math.min(targetPartitions, totalBuckets);


    //PHASE 1 与PHASE 2 加起来相当于一个简单的散列算法

    // PHASE 1 : 开始将最小数量的bucket填充到分区中，从每个文件中取出除一个bucket以外的所有bucket
    // [[注意到Math.floor((1.0 * totalBuckets) / partitions]]
    int minBucketsPerPartition = Math.max((int) Math.floor((1.0 * totalBuckets) / partitions), 1);
    LOG.info(String.format("TotalBuckets %d, min_buckets/partition %d", totalBuckets, minBucketsPerPartition));
    //用于存储每个分区编号 将来会涉及的桶的个数 (一个分区会计算多个桶)
    int[] bucketsFilled = new int[partitions];
    //用来保存每个文件组,将来的 计算分区数 (不是在hdfs的存储分区数,有很大区别,注意理解)
    Map<String, AtomicInteger> bucketsFilledPerFileGroup = new HashMap<>();
    int partitionIndex = 0;
    for (Map.Entry<String, Integer> e : bucketsPerFileGroup.entrySet()) {
      for (int b = 0; b < Math.max(1, e.getValue() - 1); b++) {
        // keep filled counts upto date
        bucketsFilled[partitionIndex]++;
        AtomicInteger cnt = bucketsFilledPerFileGroup.getOrDefault(e.getKey(), new AtomicInteger(0));
        cnt.incrementAndGet();
        bucketsFilledPerFileGroup.put(e.getKey(), cnt);

        // mark this partition against the file group
        List<Integer> partitionList = this.fileGroupToPartitions.getOrDefault(e.getKey(), new ArrayList<>());
        partitionList.add(partitionIndex);
        this.fileGroupToPartitions.put(e.getKey(), partitionList);

        // switch to new partition if needed
        if (bucketsFilled[partitionIndex] >= minBucketsPerPartition) {
          partitionIndex = (partitionIndex + 1) % partitions;
        }
      }
    }

    // PHASE 2 : 对于其余未分配的bucket,在分区上循环一次。因为我们扣留了一桶每个文件组都是统一的,其余的也是跨文件组的统一混合
    for (Map.Entry<String, Integer> e : bucketsPerFileGroup.entrySet()) {
      int remaining = e.getValue() - bucketsFilledPerFileGroup.get(e.getKey()).intValue();
      for (int r = 0; r < remaining; r++) {
        // mark this partition against the file group
        this.fileGroupToPartitions.get(e.getKey()).add(partitionIndex);
        bucketsFilled[partitionIndex]++;
        partitionIndex = (partitionIndex + 1) % partitions;
      }
    }

    //只有打日志的作用
    if (LOG.isDebugEnabled()) {
      LOG.debug("Partitions assigned per file groups :" + fileGroupToPartitions);
      StringBuilder str = new StringBuilder();
      for (int i = 0; i < bucketsFilled.length; i++) {
        str.append("p" + i + " : " + bucketsFilled[i] + ",");
      }
      LOG.debug("Num buckets assigned per file group :" + str);
    }
  }

  @Override
  public int numPartitions() {
    return partitions;
  }

  @Override
  public int getPartition(Object key) {
    //用Pair<文件id ,recordKey> 作为分区的key
    final Pair<String, String> parts = (Pair<String, String>) key;
    final long hashOfKey = NumericUtils.getMessageDigestHash("MD5", parts.getRight());

    //拿到该文件组对应的 分区编号的 列表
    final List<Integer> candidatePartitions = fileGroupToPartitions.get(parts.getLeft());

    //取模
    final int idx = (int) Math.floorMod((int) hashOfKey, candidatePartitions.size());
    assert idx >= 0;
    return candidatePartitions.get(idx);
  }

  Map<String, List<Integer>> getFileGroupToPartitions() {
    return fileGroupToPartitions;
  }
}
