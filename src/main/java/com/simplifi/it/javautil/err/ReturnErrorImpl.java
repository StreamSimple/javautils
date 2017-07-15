package com.simplifi.it.javautil.err;

public class ReturnErrorImpl implements ReturnError
{
  private String message;

  public ReturnErrorImpl()
  {
  }

  public ReturnErrorImpl(String message)
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
