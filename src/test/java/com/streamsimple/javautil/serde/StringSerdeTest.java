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
package com.streamsimple.javautil.serde;

import java.nio.charset.StandardCharsets;
import org.junit.Assert;
import org.junit.Test;

public class StringSerdeTest
{
  @Test
  public void simpleSerdeTest()
  {
    final StringSerializer serializer = new StringSerializer.Builder()
        .setCharsetName(StandardCharsets.US_ASCII.name())
        .build();

    final StringDeserializer deserializer = new StringDeserializer.Builder()
        .setCharsetName(StandardCharsets.US_ASCII.name())
        .build();

    final String expected = "I'm flying";
    final byte[] bytes = serializer.serialize(expected);
    final String actual = deserializer.deserialize(bytes);

    Assert.assertEquals(expected, actual);
  }
}
