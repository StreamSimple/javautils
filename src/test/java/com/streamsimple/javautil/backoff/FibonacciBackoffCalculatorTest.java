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

public class FibonacciBackoffCalculatorTest
{
  @Test
  public void simpleFibTest()
  {
    final FibonacciBackoffCalculator calculator = new FibonacciBackoffCalculator.Builder().build(144L);

    final long[] expected = new long[] {0L, 1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L, 144L, 144L, 144L};

    final long[] actual = new long[] {
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next()
    };

    Assert.assertArrayEquals(expected, actual);

    calculator.clear();

    final long[] clearExpected = new long[] {0L, 1L, 1L, 2L, 3L};

    final long[] clearActual = new long[] {
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next()
    };

    Assert.assertArrayEquals(clearExpected, clearActual);
  }

  @Test
  public void simpleLimitTest()
  {
    final FibonacciBackoffCalculator calculator = new FibonacciBackoffCalculator.Builder().build(10L);

    final long[] expected = new long[] {0L, 1L, 1L, 2L, 3L, 5L, 8L, 10L, 10L};

    final long[] actual = new long[] {
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next(),
        calculator.next()
    };

    Assert.assertArrayEquals(expected, actual);
  }
}
