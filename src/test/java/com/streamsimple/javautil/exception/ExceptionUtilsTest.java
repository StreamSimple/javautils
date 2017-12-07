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
package com.streamsimple.javautil.exception;

import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;

public class ExceptionUtilsTest
{
  @Test
  public void throwNonMatchingExceptionTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IOException>()
    {
      public void run() throws IOException
      {
        throw new IllegalArgumentException();
      }
    }, IOException.class);

    Assert.assertFalse(result.isThrown());
    Assert.assertTrue(result.hasNonMatchingException());
    Assert.assertTrue(result.getNonMatchingException() instanceof IllegalArgumentException);
  }

  @Test
  public void noThrowExceptionTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IOException>()
    {
      public void run() throws IOException
      {
      }
    }, IOException.class);

    Assert.assertFalse(result.isThrown());
    Assert.assertFalse(result.hasNonMatchingException());
  }

  @Test
  public void throwMatchingExceptionTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IOException>()
    {
      public void run() throws IOException
      {
        throw new IOException();
      }
    }, IOException.class);

    Assert.assertTrue(result.isThrown());
    Assert.assertFalse(result.hasNonMatchingException());
  }

  @Test
  public void printStackTraceTest()
  {
    String result = ExceptionUtils.stackTraceToString(new RuntimeException());

    Assert.assertFalse(result.isEmpty());
  }
}
