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

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ReturnErrorImplTest
{
  @Test
  public void testJoin()
  {
    final String expected = "Errors:\nmessageA\nmessageB";
    final ReturnError errA = ReturnErrorImpl.create("messageA");
    final ReturnError errB = ReturnErrorImpl.create("messageB");
    final List<ReturnError> errs = new ArrayList<>();
    errs.add(errA);
    errs.add(errB);

    final ReturnError actual = ReturnErrorImpl.join(errs);

    Assert.assertEquals(expected, actual.getMessage());
  }
}
