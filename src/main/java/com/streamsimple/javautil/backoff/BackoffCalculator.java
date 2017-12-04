package com.streamsimple.javautil.backoff;

public interface BackoffCalculator
{
  long next();

  void clear();
}
