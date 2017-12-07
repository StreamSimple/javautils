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
package com.streamsimple.javautil.net;

import org.junit.Assert;
import org.junit.Test;
import com.streamsimple.javautil.err.Result;
import com.streamsimple.javautil.exception.ExceptionUtils;
import com.streamsimple.javautil.exception.Thrower;

public class PortTest
{
  @Test
  public void tooSmallPortTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>()
    {
      public void run() throws IllegalArgumentException
      {
        new Port(Port.MIN_PORT - 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertTrue(result.isThrown());
  }

  @Test
  public void tooLargePortTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>()
    {
      public void run() throws IllegalArgumentException
      {
        new Port(Port.MAX_PORT + 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertTrue(result.isThrown());
  }

  @Test
  public void validPortTest()
  {
    ExceptionUtils.ThrowResult result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>()
    {
      public void run() throws IllegalArgumentException
      {
        new Port(Port.MIN_PORT + 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertFalse(result.isThrown());

    result = ExceptionUtils.threw(new Thrower<IllegalArgumentException>()
    {
      public void run() throws IllegalArgumentException
      {
        new Port(Port.MAX_PORT - 1);
      }
    }, IllegalArgumentException.class);

    Assert.assertFalse(result.isThrown());
  }

  @Test
  public void isValidPortTest()
  {
    Assert.assertFalse(Port.isValidPort(Port.MIN_PORT - 1));
    Assert.assertFalse(Port.isValidPort(Port.MAX_PORT + 1));
    Assert.assertTrue(Port.isValidPort(Port.MIN_PORT + 1));
    Assert.assertTrue(Port.isValidPort(Port.MAX_PORT - 1));
  }

  @Test
  public void validPortCreationTest()
  {
    final Port expected = new Port(15);
    final Result<Port> result = Port.create(15);

    Assert.assertFalse(result.hasError());
    Assert.assertTrue(result.hasResult());
    Assert.assertNull(result.getError());
    Assert.assertEquals(expected, result.getResult());
  }

  @Test
  public void errorPortCreationTest()
  {
    final Result<Port> result = Port.create(-1);

    Assert.assertTrue(result.hasError());
    Assert.assertFalse(result.hasResult());
    Assert.assertNotNull(result.getError());
    Assert.assertNull(result.getResult());
  }
}
