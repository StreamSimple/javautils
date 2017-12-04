package com.streamsimple.javautil.err;

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

    this.message = message;
  }

  public String getMessage()
  {
    return message;
  }

  public static final ReturnError create(String message, Object... args)
  {
    return new ReturnErrorImpl(String.format(message, args));
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    ReturnErrorImpl that = (ReturnErrorImpl) o;

    return message.equals(that.message);
  }

  @Override
  public int hashCode()
  {
    return message.hashCode();
  }

  @Override
  public String toString()
  {
    return "ReturnErrorImpl{" +
        "message='" + message + '\'' +
        '}';
  }
}
