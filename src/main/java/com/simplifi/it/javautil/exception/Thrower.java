package com.simplifi.it.javautil.exception;

public interface Thrower<T extends Exception>
{
  void run() throws T;
}
