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

package org.apache.hudi.io;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.engine.TaskContextSupplier;
import org.apache.hudi.common.model.HoodieBaseFile;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.util.Option;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.exception.HoodieUpsertException;
import org.apache.hudi.keygen.BaseKeyGenerator;
import org.apache.hudi.keygen.KeyGenUtils;
import org.apache.hudi.table.HoodieTable;

import org.apache.avro.generic.GenericRecord;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Hoodie merge handle which writes records (new inserts or updates) sorted by their key.
 *
 * The implementation performs a merge-sort by comparing the key of the record being written to the list of
 * keys in newRecordKeys (sorted in-memory).
 */
public class HoodieSortedMergeHandle<T extends HoodieRecordPayload, I, K, O> extends HoodieMergeHandle<T, I, K, O> {

  private final Queue<String> newRecordKeysSorted = new PriorityQueue<>();

  //普通写入调用路线
  public HoodieSortedMergeHandle(HoodieWriteConfig config, String instantTime, HoodieTable<T, I, K, O> hoodieTable,
                                 Iterator<HoodieRecord<T>> recordItr, String partitionPath, String fileId, TaskContextSupplier taskContextSupplier,
                                 Option<BaseKeyGenerator> keyGeneratorOpt) {
    super(config, instantTime, hoodieTable, recordItr, partitionPath, fileId, taskContextSupplier, keyGeneratorOpt);
    newRecordKeysSorted.addAll(keyToNewRecords.keySet());
  }

  /**
   * Called by compactor code path.
   */
  // 用于 合并服务 调用路线
  public HoodieSortedMergeHandle(HoodieWriteConfig config, String instantTime, HoodieTable<T, I, K, O> hoodieTable,
      Map<String, HoodieRecord<T>> keyToNewRecordsOrig, String partitionPath, String fileId,
      HoodieBaseFile dataFileToBeMerged, TaskContextSupplier taskContextSupplier, Option<BaseKeyGenerator> keyGeneratorOpt) {
    super(config, instantTime, hoodieTable, keyToNewRecordsOrig, partitionPath, fileId, dataFileToBeMerged,
        taskContextSupplier, keyGeneratorOpt);

    newRecordKeysSorted.addAll(keyToNewRecords.keySet());
  }

  /**
   * Go through an old record. Here if we detect a newer version shows up, we write the new one to the file.
   */
  @Override
  public void write(GenericRecord oldRecord) {
    String key = KeyGenUtils.getRecordKeyFromGenericRecord(oldRecord, keyGeneratorOpt);

    // To maintain overall sorted order across updates and inserts, write any new inserts whose keys are less than
    // the oldRecord's key.

    //newRecordKeysSorted 是一个新数据的 keys 组成的排序后的最小堆
    // 要注意 老数据的 key 也是从小到大按顺序来的 ；这点不能忽视

    //事实上 只有 newRecordKeysSorted.peek() < key 的情况才会表明  该条数据是新增 的情况
    //     newRecordKeysSorted.peek()  >= key 的情况都会 交给 父类去处理
    while (!newRecordKeysSorted.isEmpty() && newRecordKeysSorted.peek().compareTo(key) <= 0) {
      String keyToPreWrite = newRecordKeysSorted.remove();
      if (keyToPreWrite.equals(key)) {
        // will be handled as an update later
        break; //跳出while循环
      }

      // This is a new insert
      HoodieRecord<T> hoodieRecord = keyToNewRecords.get(keyToPreWrite).newInstance();
      if (writtenRecordKeys.contains(keyToPreWrite)) {
        throw new HoodieUpsertException("Insert/Update not in sorted order");
      }
      try {
        if (useWriterSchemaForCompaction) {
          writeRecord(hoodieRecord, hoodieRecord.getData().getInsertValue(tableSchemaWithMetaFields, config.getProps()));
        } else {
          writeRecord(hoodieRecord, hoodieRecord.getData().getInsertValue(tableSchema, config.getProps()));
        }
        insertRecordsWritten++;
        writtenRecordKeys.add(keyToPreWrite);
      } catch (IOException e) {
        throw new HoodieUpsertException("Failed to write records", e);
      }
    }

    super.write(oldRecord);
  }

  @Override
  public List<WriteStatus> close() {
    // write out any pending records (this can happen when inserts are turned into updates)
    while (!newRecordKeysSorted.isEmpty()) {
      try {
        String key = newRecordKeysSorted.poll();
        HoodieRecord<T> hoodieRecord = keyToNewRecords.get(key);
        if (!writtenRecordKeys.contains(hoodieRecord.getRecordKey())) {
          if (useWriterSchemaForCompaction) {
            writeRecord(hoodieRecord, hoodieRecord.getData().getInsertValue(tableSchemaWithMetaFields, config.getProps()));
          } else {
            writeRecord(hoodieRecord, hoodieRecord.getData().getInsertValue(tableSchema, config.getProps()));
          }
          insertRecordsWritten++;
        }
      } catch (IOException e) {
        throw new HoodieUpsertException("Failed to close UpdateHandle", e);
      }
    }
    newRecordKeysSorted.clear();
    keyToNewRecords.clear();

    return super.close();
  }
}