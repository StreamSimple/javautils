/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsimple.javautil.poll;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Poller<T>
{
  public static final long DEFAULT_INTERVAL = 500L;
  public static final long DEFAULT_TIMEOUT = TimeUnit.MINUTES.toMillis(1L);

  private long interval = DEFAULT_INTERVAL;
  private long timeout = DEFAULT_TIMEOUT;

  public Poller()
  {
    // Do nothing
  }

  public Poller setInterval(long interval)
  {
    if (interval < 0L) {
      throw new IllegalArgumentException();
    }

    this.interval = interval;
    return this;
  }

  public Poller setTimeout(long timeout)
  {
    if (timeout < 0L) {
      throw new IllegalArgumentException();
    }

    this.timeout = timeout;
    return this;
  }

  public T poll(Func<T> func) throws TimeoutException
  {
    long start = System.currentTimeMillis();
    long current = start;
    Result<T> result;

    while (timeout == 0 || (current - start) <= timeout) {
      result = func.run();

      if (result.done) {
        return result.result;
      }

      if (interval > 0) {
        try {
          Thread.sleep(interval);
        } catch (InterruptedException e) {
          //
        }
      }

      current = System.currentTimeMillis();
    }

    throw new TimeoutException();
  }

  public static class Result<T>
  {
    private boolean done;
    private T result;

    private Result()
    {
    }

    private Result(boolean done, T result)
    {
      this.done = done;
      this.result = result;
    }

    public static <T> Result<T> notDone()
    {
      return new Result<T>(false, null);
    }

    public static <T> Result<T> done()
    {
      return new Result<T>(true, null);
    }

    public static <T> Result<T> done(T value)
    {
      return new Result<T>(true, value);
    }
  }

  public interface Func<T>
  {
    Result<T> run();
  }
}
