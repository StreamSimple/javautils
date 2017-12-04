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
