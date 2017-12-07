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
package com.streamsimple.javautil.sets;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class SetUtilsTest
{
  @Test
  public void disjointIntersectionTest()
  {
    Set<String> setA = new HashSet<String>();
    setA.add("A");
    setA.add("B");
    setA.add("C");

    Set<String> setB = new HashSet<String>();
    setB.add("1");
    setB.add("2");

    Set<String> intersection = SetUtils.intersection(setA, setB);
    Assert.assertTrue(intersection.isEmpty());
  }

  @Test
  public void intersectionTest()
  {
    Set<String> setA = new HashSet<String>();
    setA.add("A");
    setA.add("B");
    setA.add("C");

    Set<String> setB = new HashSet<String>();
    setB.add("A");
    setB.add("B");
    setB.add("1");
    setB.add("2");

    Set<String> intersection = SetUtils.intersection(setA, setB);

    Set<String> expected = new HashSet<String>();
    expected.add("A");
    expected.add("B");

    Assert.assertEquals(expected, intersection);
  }
}
