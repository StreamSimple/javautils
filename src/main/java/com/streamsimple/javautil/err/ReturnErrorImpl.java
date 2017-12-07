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

    ReturnErrorImpl that = (ReturnErrorImpl)o;

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
