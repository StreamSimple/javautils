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
package com.streamsimple.javautil.queue;

import com.streamsimple.javautil.cmp.IntComparator;
import org.junit.Assert;
import org.junit.Test;

public class HeapFastRemoveTest
{
  @Test
  public void pollEmptyTest()
  {
    final HeapFastRemove<String, Integer> heap = new HeapFastRemove<>(new IntComparator());

    Assert.assertNull(heap.poll());
  }

  @Test
  public void peekEmptyTest()
  {
    final HeapFastRemove<String, Integer> heap = new HeapFastRemove<>(new IntComparator());

    Assert.assertNull(heap.peek());
  }

  @Test
  public void simpleHeapTest()
  {
    final HeapFastRemove<String, Integer> heap = new HeapFastRemove<>(new IntComparator());

    Assert.assertTrue(heap.isEmpty());

    heap.offer("a", 10);
    Assert.assertEquals(1, heap.size());
    heap.offer("b", 7);
    Assert.assertEquals(2, heap.size());
    heap.offer("c", 3);
    Assert.assertEquals(3, heap.size());
    heap.offer("d", 5);
    Assert.assertEquals(4, heap.size());

    Assert.assertFalse(heap.isEmpty());

    Assert.assertEquals(3, (int)heap.poll());
    Assert.assertEquals(3, heap.size());
    Assert.assertEquals(5, (int)heap.poll());
    Assert.assertEquals(2, heap.size());
    Assert.assertEquals(7, (int)heap.poll());
    Assert.assertEquals(1, heap.size());
    Assert.assertEquals(10, (int)heap.poll());
    Assert.assertEquals(0, heap.size());
    Assert.assertTrue(heap.isEmpty());
  }
}
