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

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class ExceptionUtils
{
  public static class ThrowResult
  {
    private boolean thrown;
    private Exception nonMatchingException;

    public ThrowResult(boolean thrown, Exception nonMatchingException)
    {
      this.thrown = thrown;
      this.nonMatchingException = nonMatchingException;
    }

    public boolean isThrown()
    {
      return thrown;
    }

    public boolean hasNoExceptions()
    {
      return !isThrown() && !hasNonMatchingException();
    }

    public boolean hasNonMatchingException()
    {
      return nonMatchingException != null;
    }

    public Exception getNonMatchingException()
    {
      return nonMatchingException;
    }
  }

  public static <T extends Exception> ThrowResult threw(Thrower<T> thrower, Class<T> clazz)
  {
    boolean thrown = false;
    Exception nonMatchingException = null;

    try {
      thrower.run();
    } catch (Exception e) {
      thrown = e.getClass().equals(clazz);

      if (!thrown) {
        nonMatchingException = e;
      }
    }

    return new ThrowResult(thrown, nonMatchingException);
  }

  public static String stackTraceToString(Exception e)
  {
    String result = null;

    try {
      ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
      PrintStream printStream = new PrintStream(byteArrayOutputStream, true, Charset.defaultCharset().name());

      e.printStackTrace(printStream);
      printStream.flush();
      byteArrayOutputStream.flush();

      result = new String(byteArrayOutputStream.toByteArray(), Charset.defaultCharset());
    } catch (UnsupportedEncodingException encodingException) {
      throw new RuntimeException(encodingException);
    } catch (IOException ioException) {
      throw new RuntimeException(ioException);
    }

    return result;
  }
}
