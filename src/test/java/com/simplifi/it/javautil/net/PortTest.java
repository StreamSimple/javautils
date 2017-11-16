package com.simplifi.it.javautil.net;

import com.simplifi.it.javautil.err.Result;
import com.simplifi.it.javautil.exception.ExceptionUtils;
import com.simplifi.it.javautil.exception.Thrower;
import org.junit.Assert;
import org.junit.Test;

public class PortTest
{
  @Test
  public void tooSmallPortTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>() {
      public void run() throws IllegalArgumentException {
        new Port(Port.MIN_PORT - 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertTrue(result.isThrown());
  }

  @Test
  public void tooLargePortTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>() {
      public void run() throws IllegalArgumentException {
        new Port(Port.MAX_PORT + 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertTrue(result.isThrown());
  }

  @Test
  public void validPortTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>() {
      public void run() throws IllegalArgumentException {
        new Port(Port.MIN_PORT + 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertFalse(result.isThrown());

    result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>() {
      public void run() throws IllegalArgumentException {
        new Port(Port.MAX_PORT - 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertFalse(result.isThrown());
  }

  @Test
  public void isValidPortTest()
  {
    Assert.assertFalse(Port.isValidPort(Port.MIN_PORT - 1));
    Assert.assertFalse(Port.isValidPort(Port.MAX_PORT + 1));
    Assert.assertTrue(Port.isValidPort(Port.MIN_PORT + 1));
    Assert.assertTrue(Port.isValidPort(Port.MAX_PORT - 1));
  }

  @Test
  public void validPortCreationTest()
  {
    final Port expected = new Port(15);
    final Result<Port> result = Port.create(15);

    Assert.assertFalse(result.hasError());
    Assert.assertTrue(result.hasResult());
    Assert.assertNull(result.getError());
    Assert.assertEquals(expected, result.getResult());
  }

  @Test
  public void errorPortCreationTest()
  {
    final Result<Port> result = Port.create(-1);

    Assert.assertTrue(result.hasError());
    Assert.assertFalse(result.hasResult());
    Assert.assertNotNull(result.getError());
    Assert.assertNull(result.getResult());
  }
}
