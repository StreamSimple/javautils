package com.streamsimple.javautil.args;

import com.streamsimple.javautil.exception.ExceptionUtils;
import com.streamsimple.javautil.exception.Thrower;
import org.junit.Assert;
import org.junit.Test;

public class PreconditionsTest
{
  @Test
  public void nonNullTest()
  {
    String val1 = "a";
    String val2 = Preconditions.checkNotNull(val1);
    Assert.assertEquals(val1, val2);
  }

  @Test
  public void nullTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<NullPointerException>() {
      public void run() throws NullPointerException {
        Preconditions.checkNotNull(null);
      }
    }, NullPointerException.class);

    Assert.assertTrue(result.isThrown());
  }
}
