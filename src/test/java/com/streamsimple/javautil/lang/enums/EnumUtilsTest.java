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
package com.streamsimple.javautil.lang.enums;

import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.junit.Test;

public class EnumUtilsTest
{
  enum TestEnum
  {
    ENUM_1,
    ENUM_2,
    ENUM_3
  }

  @Test
  public void simpleEnumMapTest()
  {
    final Map<String, TestEnum> expected = new HashMap<>();
    expected.put(TestEnum.ENUM_1.name(), TestEnum.ENUM_1);
    expected.put(TestEnum.ENUM_2.name(), TestEnum.ENUM_2);
    expected.put(TestEnum.ENUM_3.name(), TestEnum.ENUM_3);

    final Map<String, TestEnum> actual = EnumUtils.createNameToEnumMap(TestEnum.values());

    Assert.assertEquals(expected, actual);
  }
}
