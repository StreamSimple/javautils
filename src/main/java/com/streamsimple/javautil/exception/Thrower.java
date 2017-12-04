package com.streamsimple.javautil.exception;

public interface Thrower<T extends Exception>
{
  void run() throws T;
}
