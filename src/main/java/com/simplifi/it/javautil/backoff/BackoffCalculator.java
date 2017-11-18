package com.simplifi.it.javautil.backoff;

public interface BackoffCalculator
{
  long next();

  void clear();
}
