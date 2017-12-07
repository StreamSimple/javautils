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
package com.streamsimple.javautil.backoff;

import org.junit.Assert;
import org.junit.Test;

public class BackoffWaiterTest
{
  @Test
  public void simpleWaiterTest() throws InterruptedException
  {
    final MockBackoffCalculator calculator = new MockBackoffCalculator();
    final BackoffWaiter waiter = new BackoffWaiter(calculator);

    long startTime = System.currentTimeMillis();
    waiter.sleep();
    long endTime = System.currentTimeMillis();

    Assert.assertTrue((endTime - startTime) < 1000L);

    startTime = System.currentTimeMillis();
    waiter.sleep();
    endTime = System.currentTimeMillis();

    Assert.assertTrue((endTime - startTime) >= 2000L);

    try {
      startTime = System.currentTimeMillis();
      Thread.currentThread().interrupt();
      waiter.sleep();
    } catch (InterruptedException e) {
      endTime = System.currentTimeMillis();
      Assert.assertTrue((endTime - startTime) < 1000L);
    }

    startTime = System.currentTimeMillis();
    Thread.currentThread().interrupt();
    waiter.sleepUninterruptibly();
    endTime = System.currentTimeMillis();
    Assert.assertTrue((endTime - startTime) >= 2000L);
  }

  public static class MockBackoffCalculator implements BackoffCalculator
  {
    private long curr = 0L;

    @Override
    public long next()
    {
      try {
        return curr;
      } finally {
        curr = 2000L;
      }
    }

    @Override
    public void clear()
    {
      curr = 0L;
    }
  }
}
