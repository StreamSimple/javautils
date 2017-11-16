package com.simplifi.it.javautil.net;

import org.junit.Assert;
import org.junit.Test;
import com.simplifi.it.javautil.err.Result;

public class HostTest
{
  @Test
  public void successHostCreateTest()
  {
    final Host expectedHost = new Host("localhost");
    final Result<Host> result = Host.create("localhost");

    Assert.assertFalse(result.hasError());
    Assert.assertTrue(result.hasResult());
    Assert.assertNull(result.getError());
    Assert.assertEquals(expectedHost, result.getResult());
  }
  
  @Test
  public void errorHostCreateTest()
  {
    final Result<Host> result = Host.create("barf.20384i0s8afy08234");

    Assert.assertTrue(result.hasError());
    Assert.assertFalse(result.hasResult());
    Assert.assertNotNull(result.getError());
    Assert.assertNull(result.getResult());
  }
}
