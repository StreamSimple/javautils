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
    final long next = sum == 0? 1: sum;

    prev = curr;
    curr = next > maxBackoff? maxBackoff: next;
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
