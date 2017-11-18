package com.simplifi.it.javautil.backoff;

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
    final long result = curr;
    final long sum = prev + curr;
    final long next = sum == 0? 1: sum;

    prev = curr;
    curr = next;
    return result;
  }

  @Override
  public void clear()
  {
    curr = 0L;
    prev = 0L;
  }
}
