package com.simplifi.it.javautil.exception;

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
}
