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

import org.junit.Assert;
import org.junit.Test;

public class CircularFixedSizeQueueTest
{
  @Test(expected = IllegalArgumentException.class)
  public void dontAllowQueuesSmallerThanTwo()
  {
    new CircularFixedSizeQueue<String>(1);
  }

  @Test
  public void offerPollOfferPoll()
  {
    final CircularFixedSizeQueue<Integer> queue = new CircularFixedSizeQueue<Integer>(3);

    queue.offer(10);
    Assert.assertEquals(1, queue.size());
    Assert.assertEquals(10, (int)queue.peek());
    checkContent(queue, new int[]{10});

    Assert.assertEquals(10, (int)queue.poll());
    Assert.assertTrue(queue.isEmpty());
    Assert.assertEquals(0, queue.size());
  }

  @Test
  public void simpleQueueOfferTest()
  {
    final CircularFixedSizeQueue<Integer> queue = new CircularFixedSizeQueue<Integer>(3);

    Assert.assertTrue(queue.isEmpty());
    Assert.assertEquals(0, queue.size());

    queue.offer(1);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(1, queue.size());
    Assert.assertEquals(1, (int)queue.peek());
    checkContent(queue, new int[]{1});

    queue.offer(2);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(2, queue.size());
    Assert.assertEquals(1, (int)queue.peek());
    checkContent(queue, new int[]{1, 2});

    queue.offer(3);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(3, queue.size());
    Assert.assertEquals(1, (int)queue.peek());
    checkContent(queue, new int[]{1, 2, 3});

    queue.offer(4);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(3, queue.size());
    Assert.assertEquals(2, (int)queue.peek());
    checkContent(queue, new int[]{2, 3, 4});

    queue.offer(5);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(3, queue.size());
    Assert.assertEquals(3, (int)queue.peek());
    checkContent(queue, new int[]{3, 4, 5});

    queue.offer(6);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(3, queue.size());
    Assert.assertEquals(4, (int)queue.peek());
    checkContent(queue, new int[]{4, 5, 6});

    queue.offer(7);

    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(3, queue.size());
    Assert.assertEquals(5, (int)queue.peek());
    checkContent(queue, new int[]{5, 6, 7});

    Assert.assertEquals(5, (int)queue.poll());
    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(2, queue.size());
    checkContent(queue, new int[]{6, 7});

    Assert.assertEquals(6, (int)queue.poll());
    Assert.assertFalse(queue.isEmpty());
    Assert.assertEquals(1, queue.size());
    checkContent(queue, new int[]{7});

    Assert.assertEquals(7, (int)queue.poll());
    Assert.assertTrue(queue.isEmpty());
    Assert.assertEquals(0, queue.size());
  }

  private void checkContent(CircularFixedSizeQueue<Integer> queue, int[] expected)
  {
    for (int i = 0; i < expected.length; i++) {
      Assert.assertEquals(expected[i], (int)queue.get(i));
    }
  }
}
