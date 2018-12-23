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

package com.streamsimple.javautil.test;

import com.streamsimple.guava.common.base.Preconditions;

import java.util.concurrent.TimeoutException;
import java.util.function.Supplier;

public class WaitUtils
{
  /**
   * Wait for the specified test to return true. The test will be performed
   * initially and then every {@code checkEveryMillis} until at least
   * {@code waitForMillis} time has expired. If {@code check} is null or
   * {@code waitForMillis} is less than {@code checkEveryMillis} this method
   * will throw an {@link IllegalArgumentException}.
   *
   * @param check            the test to perform
   * @param checkEveryMillis how often to perform the test
   * @param waitForMillis    the amount of time after which no more tests will be
   *                         performed
   * @throws TimeoutException     if the test does not return true in the allotted
   *                              time
   * @throws InterruptedException if the method is interrupted while waiting
   */
  public static void waitFor(Supplier<Boolean> check, int checkEveryMillis,
      int waitForMillis) throws TimeoutException, InterruptedException
  {
    Preconditions.checkNotNull(check, ERROR_MISSING_ARGUMENT);
    Preconditions.checkArgument(waitForMillis >= checkEveryMillis,
        ERROR_INVALID_ARGUMENT);

    long st = monotonicNow();
    boolean result = check.get();

    while (!result && (monotonicNow() - st < waitForMillis)) {
      Thread.sleep(checkEveryMillis);
      result = check.get();
    }

    if (!result) {
      throw new TimeoutException("Timed out waiting for condition. " +
          "Thread diagnostics:\n" +
          TimedOutTestsListener.buildThreadDiagnosticString());
    }
  }

  /**
   * Current time from some arbitrary time base in the past, counting in
   * milliseconds, and not affected by settimeofday or similar system clock
   * changes.  This is appropriate to use when computing how much longer to
   * wait for an interval to expire.
   * This function can return a negative value and it must be handled correctly
   * by callers. See the documentation of System#nanoTime for caveats.
   *
   * @return a monotonic clock that counts in milliseconds.
   */
  private static long monotonicNow()
  {
    return System.nanoTime() / NANOSECONDS_PER_MILLISECOND;
  }

  /**
   * number of nano seconds in 1 millisecond
   */
  private static final long NANOSECONDS_PER_MILLISECOND = 1000000;

  /**
   * Error string used in {@link #waitFor(Supplier, int, int)}.
   */
  private static final String ERROR_MISSING_ARGUMENT =
      "Input supplier interface should be initailized";
  private static final String ERROR_INVALID_ARGUMENT =
      "Total wait time should be greater than check interval time";
}
