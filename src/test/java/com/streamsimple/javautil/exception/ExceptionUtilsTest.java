package com.streamsimple.javautil.exception;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class ExceptionUtilsTest
{
  @Test
  public void throwNonMatchingExceptionTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IOException>() {
      public void run() throws IOException {
        throw new IllegalArgumentException();
      }
    }, IOException.class);

    Assert.assertFalse(result.isThrown());
    Assert.assertTrue(result.hasNonMatchingException());
    Assert.assertTrue(result.getNonMatchingException() instanceof IllegalArgumentException);
  }

  @Test
  public void noThrowExceptionTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IOException>() {
      public void run() throws IOException {
      }
    }, IOException.class);

    Assert.assertFalse(result.isThrown());
    Assert.assertFalse(result.hasNonMatchingException());
  }

  @Test
  public void throwMatchingExceptionTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IOException>() {
      public void run() throws IOException {
        throw new IOException();
      }
    }, IOException.class);

    Assert.assertTrue(result.isThrown());
    Assert.assertFalse(result.hasNonMatchingException());
  }

  @Test
  public void printStackTraceTest()
  {
    String result = ExceptionUtils.stackTraceToString(new RuntimeException());

    Assert.assertFalse(result.isEmpty());
  }
}
