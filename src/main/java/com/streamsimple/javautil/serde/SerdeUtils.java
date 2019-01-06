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

public class SerdeUtils
{
  public static int NUM_BYTES_LONG = 8;
  public static int NUM_BYTES_INT = 4;

  /**
   * Big endian
   * @param buffer
   * @param offset
   * @return
   */
  public static int deserializeInt(byte[] buffer, int offset)
  {
    return ((((int)buffer[0 + offset]) & 0xFF) << 24) |
        ((((int)buffer[1 + offset]) & 0xFF) << 16) |
        ((((int)buffer[2 + offset]) & 0xFF) << 8) |
        (((int)buffer[3 + offset]) & 0xFF);
  }

  /**
   * Serializes the given integer value in big endian format.
   * @param val The value to serialize.
   * @return The serialized integer value.
   */
  public static byte[] serializeInt(int val)
  {
    byte[] buffer = new byte[NUM_BYTES_INT];

    buffer[0] = (byte)((val >> 24) & 0xFF);
    buffer[1] = (byte)((val >> 16) & 0xFF);
    buffer[2] = (byte)((val >> 8) & 0xFF);
    buffer[3] = (byte)(val & 0xFF);

    return buffer;
  }

  /**
   * Little endian
   * @param val
   * @return
   */
  public static byte[] serializeIntLE(int val)
  {
    byte[] buffer = new byte[NUM_BYTES_INT];

    buffer[0] = (byte)(val & 0xFF);
    buffer[1] = (byte)((val >> 8) & 0xFF);
    buffer[2] = (byte)((val >> 16) & 0xFF);
    buffer[3] = (byte)((val >> 24) & 0xFF);

    return buffer;
  }

  /**
   * Serializes the given long value to an array of bytes. BigEndian.
   * @param val The long value to serialize.
   * @return The serialized long value.
   */
  public static byte[] serializeLong(long val)
  {
    byte[] buffer = new byte[NUM_BYTES_LONG];

    buffer[0] = (byte)((val >> 56) & 0xFFL);
    buffer[1] = (byte)((val >> 48) & 0xFFL);
    buffer[2] = (byte)((val >> 40) & 0xFFL);
    buffer[3] = (byte)((val >> 32) & 0xFFL);
    buffer[4] = (byte)((val >> 24) & 0xFFL);
    buffer[5] = (byte)((val >> 16) & 0xFFL);
    buffer[6] = (byte)((val >> 8) & 0xFFL);
    buffer[7] = (byte)(val & 0xFFL);

    return buffer;
  }

  public static void serializeLong(long val, int offset, byte[] buffer)
  {
    buffer[0 + offset] = (byte)((val >> 56) & 0xFFL);
    buffer[1 + offset] = (byte)((val >> 48) & 0xFFL);
    buffer[2 + offset] = (byte)((val >> 40) & 0xFFL);
    buffer[3 + offset] = (byte)((val >> 32) & 0xFFL);
    buffer[4 + offset] = (byte)((val >> 24) & 0xFFL);
    buffer[5 + offset] = (byte)((val >> 16) & 0xFFL);
    buffer[6 + offset] = (byte)((val >> 8) & 0xFFL);
    buffer[7 + offset] = (byte)(val & 0xFFL);
  }

  /**
   * Deserializes the given BigEndian long value.
   * @param buffer A serialized long value.
   * @return The deserialized long value.
   */
  public static long deserializeLong(byte[] buffer)
  {
    return (((long)buffer[0]) & 0xFFL) << 56 |
        ((((long)buffer[1]) & 0xFFL) << 48) |
        ((((long)buffer[2]) & 0xFFL) << 40) |
        ((((long)buffer[3]) & 0xFFL) << 32) |
        ((((long)buffer[4]) & 0xFFL) << 24) |
        ((((long)buffer[5]) & 0xFFL) << 16) |
        ((((long)buffer[6]) & 0xFFL) << 8) |
        (((long)buffer[7]) & 0xFFL);
  }
}
