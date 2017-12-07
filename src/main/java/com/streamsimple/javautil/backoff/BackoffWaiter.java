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

public class BackoffWaiter
{
  private final BackoffCalculator calculator;

  public BackoffWaiter(final BackoffCalculator calculator)
  {
    if (calculator == null) {
      throw new NullPointerException();
    }

    this.calculator = calculator;
  }

  public void sleep() throws InterruptedException
  {
    Thread.sleep(calculator.next());
  }

  public void sleepUninterruptibly()
  {
    long diff = 0L;

    for (long waitTime = calculator.next(); waitTime > 0; waitTime -= diff) {
      final long startTime = System.currentTimeMillis();

      try {
        Thread.sleep(waitTime);
        break;
      } catch (InterruptedException e) {
        diff = System.currentTimeMillis() - startTime;
      }
    }
  }

  public void clear()
  {
    calculator.clear();
  }
}
