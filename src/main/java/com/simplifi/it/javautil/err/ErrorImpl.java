package com.simplifi.it.javautil.err;

public class ErrorImpl implements Error
{
  private String message;

  public ErrorImpl()
  {
  }

  public ErrorImpl(String message)
  {
    if (message == null) {
      throw new IllegalArgumentException();
    }
  }

  public String getMessage()
  {
    return message;
  }
}
