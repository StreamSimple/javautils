package com.simplifi.it.javautil.args;

public class Preconditions
{
  private Preconditions()
  {
  }

  public static <T> T checkNotNull(T value)
  {
    if (value == null) {
      throw new NullPointerException();
    }

    return value;
  }
}
