package com.streamsimple.javautil.poll;

import java.util.concurrent.TimeoutException;
import org.junit.Assert;
import org.junit.Test;

public class PollerTest
{
  @Test
  public void simplePollSuccessTest() throws Exception
  {
    String result = (String)new Poller<String>()
        .setInterval(100L)
        .setTimeout(3000L)
        .poll(new Poller.Func<String>()
        {
          public Poller.Result<String> run()
          {
            return Poller.Result.done("A");
          }
        });

    Assert.assertEquals("A", result);
  }

  @Test
  public void simplePollTimeoutTest() throws Exception
  {
    final MutableInt count = new MutableInt(0);
    boolean threwException = false;

    try {
      String result = (String)new Poller<String>()
          .setInterval(100L)
          .setTimeout(2000L)
          .poll(new Poller.Func<String>()
          {
            public Poller.Result<String> run()
            {
              count.inc();
              return Poller.Result.notDone();
            }
          });

      Assert.assertEquals("A", result);
    } catch (TimeoutException e) {
      threwException = true;
    }

    Assert.assertTrue(threwException);
    Assert.assertTrue(1 <= count.getInt() && count.getInt() <= 21);
  }

  public static class MutableInt
  {
    private int value = 0;

    public MutableInt(int value)
    {
      this.value = value;
    }

    public void inc()
    {
      this.value++;
    }

    public int getInt()
    {
      return this.value;
    }
  }
}
