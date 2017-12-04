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
