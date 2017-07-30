package com.simplifi.it.javautil.exception;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ExceptionUtils
{
  public static class ThrowResult
  {
    private boolean thrown;
    private Exception nonMatchingException;

    public ThrowResult(boolean thrown, Exception nonMatchingException)
    {
      this.thrown = thrown;
      this.nonMatchingException = nonMatchingException;
    }

    public boolean isThrown()
    {
      return thrown;
    }

    public boolean hasNoExceptions()
    {
      return !isThrown() && !hasNonMatchingException();
    }

    public boolean hasNonMatchingException()
    {
      return nonMatchingException != null;
    }

    public Exception getNonMatchingException()
    {
      return nonMatchingException;
    }
  }

  public static <T extends Exception> ThrowResult threw(Thrower<T> thrower, Class<T> clazz)
  {
    boolean thrown = false;
    Exception nonMatchingException = null;

    try {
      thrower.run();
    } catch (Exception e) {
      thrown = e.getClass().equals(clazz);

      if (!thrown) {
        nonMatchingException = e;
      }
    }

    return new ThrowResult(thrown, nonMatchingException);
  }

  public static String stackTraceToString(Exception e)
  {
    String result = null;

    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      PrintStream printStream = new PrintStream(byteArrayOutputStream, true, Charset.defaultCharset().name());

      e.printStackTrace(printStream);
      printStream.flush();
      byteArrayOutputStream.flush();

      result = new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
    } catch (UnsupportedEncodingException encodingException) {
      throw new RuntimeException(encodingException);
    } catch (IOException ioException) {
      throw new RuntimeException(ioException);
    }

    return result;
  }
}
