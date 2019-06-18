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
package com.streamsimple.javautil.collection.maps;

import java.util.HashMap;
import org.junit.Assert;
import org.junit.Test;

public class MapUtilsTest
{
  @Test
  public void containsTrue()
  {
    final HashMap<String, String> parent = MapBuilder.<String, String>createHashMap()
        .put("key1", "val1")
        .put("key2", "val2")
        .put("key3", "val3")
        .put("key4", "val4")
        .build();

    final HashMap<String, String> child = MapBuilder.<String, String>createHashMap()
        .put("key1", "val1")
        .put("key2", "val2")
        .build();

    Assert.assertTrue(MapUtils.contains(parent, child));
  }

  @Test
  public void containsFalse()
  {
    final HashMap<String, String> parent = MapBuilder.<String, String>createHashMap()
        .put("key1", "val1")
        .put("key2", "val2")
        .put("key3", "val3")
        .put("key4", "val4")
        .build();

    final HashMap<String, String> child = MapBuilder.<String, String>createHashMap()
        .put("key1", "val1")
        .put("key2", "val2")
        .put("key3", "val3")
        .put("key4", "val4")
        .put("key5", "val5")
        .build();

    Assert.assertFalse(MapUtils.contains(parent, child));
  }
}
