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

import com.streamsimple.javautil.err.Result;
import org.junit.Assert;
import org.junit.Test;

public class HostTest
{
  @Test
  public void successHostCreateTest()
  {
    final Host expectedHost = new Host("localhost");
    final Result<Host> result = Host.create("localhost");

    Assert.assertFalse(result.hasError());
    Assert.assertTrue(result.hasResult());
    Assert.assertNull(result.getError());
    Assert.assertEquals(expectedHost, result.getResult());
  }

  @Test
  public void errorHostCreateTest()
  {
    final Result<Host> result = Host.create("barf.20384i0s8afy08234");

    Assert.assertTrue(result.hasError());
    Assert.assertFalse(result.hasResult());
    Assert.assertNotNull(result.getError());
    Assert.assertNull(result.getResult());
  }
}
