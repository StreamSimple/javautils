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
package com.streamsimple.javautil.math;

import org.junit.Assert;
import org.junit.Test;

public class IntUtilsTest
{
  @Test
  public void isNotInIntervalInclusiveExceptionLowerBoundTest()
  {
    IntUtils.isNotInIntervalInclusiveException(0, 2, 0);
  }

  @Test
  public void isNotInIntervalInclusiveExceptionUpperBoundTest()
  {
    IntUtils.isNotInIntervalInclusiveException(0, 2, 2);
  }

  @Test
  public void isNotInIntervalInclusiveExceptionMiddleTest()
  {
    IntUtils.isNotInIntervalInclusiveException(0, 2, 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void isNotInIntervalInclusiveExceptionLessTest()
  {
    Assert.assertTrue("Should throw IllegalArgumentException", true);

    IntUtils.isNotInIntervalInclusiveException(0, 2, -1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void isNotInIntervalInclusiveExceptionUpperTest()
  {
    Assert.assertTrue("Should throw IllegalArgumentException", true);

    IntUtils.isNotInIntervalInclusiveException(0, 2, 3);
  }

  @Test
  public void isUPowerOf2Test()
  {
    for (int i = 0; i < 31; i++) {
      Assert.assertTrue("At 2^i " + i, IntUtils.isPowerOf2(1 << i));
      Assert.assertFalse("At 2^i + 5 " + i, IntUtils.isPowerOf2((1 << i) + 5));
    }

    Assert.assertFalse(IntUtils.isPowerOf2(-1));
    Assert.assertFalse(IntUtils.isPowerOf2(Integer.MIN_VALUE));
  }
}
