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
import java.util.Map;

public final class MapBuilder<K, V, M extends Map<K, V>>
{
  private final M map;

  private MapBuilder(final M map)
  {
    this.map = map;
  }

  public MapBuilder<K, V, M> put(K key, V value)
  {
    map.put(key, value);
    return this;
  }

  public M build()
  {
    return map;
  }

  public static <K, V> MapBuilder<K, V, HashMap<K, V>> createHashMap()
  {
    return new MapBuilder<>(new HashMap<>());
  }
}
