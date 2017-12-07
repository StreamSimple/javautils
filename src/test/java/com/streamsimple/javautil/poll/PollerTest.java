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

import java.util.concurrent.TimeoutException;
import org.junit.Assert;
import org.junit.Test;

public class PollerTest
{
  @Test
  public void simplePollSuccessTest() throws Exception
  {
    String result = (String)new Poller<String>()
        .setInterval(100L)
        .setTimeout(3000L)
        .poll(new Poller.Func<String>()
        {
          public Poller.Result<String> run()
          {
            return Poller.Result.done("A");
          }
        });

    Assert.assertEquals("A", result);
  }

  @Test
  public void simplePollTimeoutTest() throws Exception
  {
    final MutableInt count = new MutableInt(0);
    boolean threwException = false;

    try {
      String result = (String)new Poller<String>()
          .setInterval(100L)
          .setTimeout(2000L)
          .poll(new Poller.Func<String>()
          {
            public Poller.Result<String> run()
            {
              count.inc();
              return Poller.Result.notDone();
            }
          });

      Assert.assertEquals("A", result);
    } catch (TimeoutException e) {
      threwException = true;
    }

    Assert.assertTrue(threwException);
    Assert.assertTrue(1 <= count.getInt() && count.getInt() <= 21);
  }

  public static class MutableInt
  {
    private int value = 0;

    public MutableInt(int value)
    {
      this.value = value;
    }

    public void inc()
    {
      this.value++;
    }

    public int getInt()
    {
      return this.value;
    }
  }
}
