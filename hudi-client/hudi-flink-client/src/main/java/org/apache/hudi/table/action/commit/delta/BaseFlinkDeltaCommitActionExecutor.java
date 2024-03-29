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

package org.apache.hudi.table.action.commit.delta;

import org.apache.hudi.client.WriteStatus;
import org.apache.hudi.common.engine.HoodieEngineContext;
import org.apache.hudi.common.model.HoodieRecord;
import org.apache.hudi.common.model.HoodieRecordPayload;
import org.apache.hudi.common.model.WriteOperationType;
import org.apache.hudi.config.HoodieWriteConfig;
import org.apache.hudi.execution.FlinkLazyInsertIterable;
import org.apache.hudi.io.ExplicitWriteHandleFactory;
import org.apache.hudi.io.FlinkAppendHandle;
import org.apache.hudi.table.HoodieTable;
import org.apache.hudi.table.action.commit.BaseFlinkCommitActionExecutor;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public abstract class BaseFlinkDeltaCommitActionExecutor<T extends HoodieRecordPayload<T>>
    extends BaseFlinkCommitActionExecutor<T> {

  public BaseFlinkDeltaCommitActionExecutor(HoodieEngineContext context,
                                            FlinkAppendHandle<?, ?, ?, ?> writeHandle,
                                            HoodieWriteConfig config,
                                            HoodieTable table,
                                            String instantTime,
                                            WriteOperationType operationType) {
    super(context, writeHandle, config, table, instantTime, operationType);
  }

  @Override
  public Iterator<List<WriteStatus>> handleUpdate(String partitionPath, String fileId, Iterator<HoodieRecord<T>> recordItr) {
    FlinkAppendHandle appendHandle = (FlinkAppendHandle) writeHandle;
    // 核心方法
    appendHandle.doAppend();
    List<WriteStatus> writeStatuses = appendHandle.close();
    return Collections.singletonList(writeStatuses).iterator();
  }

  // Iterator<List<WriteStatus>>  中的 泛型 有点恶心, 想分批 又没有分批 , 一次 computeNext() 就把所有的数据处理完了， 这么设计的意义是什么？？
  @Override
  public Iterator<List<WriteStatus>> handleInsert(String idPfx, Iterator<HoodieRecord<T>> recordItr) {
    return new FlinkLazyInsertIterable<>(recordItr, true, config, instantTime, table,
        idPfx, taskContextSupplier, new ExplicitWriteHandleFactory(writeHandle));
  }
}
