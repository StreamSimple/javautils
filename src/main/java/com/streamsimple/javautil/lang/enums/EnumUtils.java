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

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class EnumUtils
{
  public static <T extends Enum<T>> Map<String, T> createNameToEnumMap(T[] enumVals)
  {
    final Map<String, T> enumMap = new HashMap<>();

    for (T enumVal: enumVals) {
      enumMap.put(enumVal.name(), enumVal);
    }

    return Collections.unmodifiableMap(enumMap);
  }
}
