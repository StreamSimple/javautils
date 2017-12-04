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
