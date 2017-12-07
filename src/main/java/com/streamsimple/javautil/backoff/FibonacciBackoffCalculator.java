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

public class FibonacciBackoffCalculator implements BackoffCalculator
{
  private final long maxBackoff;
  private long curr = 0L;
  private long prev = 0L;

  protected FibonacciBackoffCalculator(final long maxBackoff)
  {
    this.maxBackoff = maxBackoff;
  }

  @Override
  public long next()
  {
    if (curr == maxBackoff) {
      return maxBackoff;
    }

    final long result = curr;
    final long sum = prev + curr;
    final long next = sum == 0 ? 1 : sum;

    prev = curr;
    curr = next > maxBackoff ? maxBackoff : next;
    return result;
  }

  @Override
  public void clear()
  {
    curr = 0L;
    prev = 0L;
  }

  public static class Builder
  {
    public Builder()
    {
    }

    public FibonacciBackoffCalculator build(long maxBackoff)
    {
      return new FibonacciBackoffCalculator(maxBackoff);
    }
  }
}
