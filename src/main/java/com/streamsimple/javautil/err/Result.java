package com.streamsimple.javautil.err;

public class Result<T>
{
  private T result;
  private ReturnError error;

  public Result(T result)
  {
    if (result == null) {
      throw new NullPointerException();
    }

    this.result = result;
  }

  public Result(ReturnError error)
  {
    if (error == null) {
      throw new NullPointerException();
    }

    this.error = error;
  }

  public boolean hasResult()
  {
    return result != null;
  }

  public T getResult()
  {
    return result;
  }

  public boolean hasError()
  {
    return error != null;
  }

  public ReturnError getError()
  {
    return error;
  }

  public static <T> Result<T> create(T result)
  {
    return new Result(result);
  }

  public static <T> Result<T> create(ReturnError error)
  {
    return new Result(error);
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

    Result<?> result1 = (Result<?>) o;

    if (result != null ? !result.equals(result1.result) : result1.result != null) {
      return false;
    }
    return error != null ? error.equals(result1.error) : result1.error == null;
  }

  @Override
  public int hashCode()
  {
    int result1 = result != null ? result.hashCode() : 0;
    result1 = 31 * result1 + (error != null ? error.hashCode() : 0);
    return result1;
  }

  @Override
  public String toString()
  {
    return "Result{" +
        "result=" + result +
        ", error=" + error +
        '}';
  }
}
