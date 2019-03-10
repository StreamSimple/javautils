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

/**
 * @author Topeka Labs
 */
public class ByteUtils
{
  private static final byte[] BIT_REVERSAL_MAP_BYTE = new byte[256];
  private static final int[] BIT_REVERSAL_MAP_INT = new int[256];
  private static final long[] BIT_REVERSAL_MAP_LONG = new long[256];

  static {
    buildBitReversalMaps();
  }

  private static void buildBitReversalMaps()
  {
    for (int value = 0;
         value <= 0xFF;
         value++) {
      int reversedValue = 0;

      for (int bitPosition = 0;
           bitPosition < 8;
           bitPosition++) {
        int reversedPosition = 7 - bitPosition;
        reversedValue |= ((value & (1 << bitPosition)) >>
            bitPosition) << reversedPosition;
      }

      BIT_REVERSAL_MAP_BYTE[value] = (byte)reversedValue;
      BIT_REVERSAL_MAP_INT[value] = reversedValue;
      BIT_REVERSAL_MAP_LONG[value] = (long)reversedValue;
    }
  }

  public static byte getReversedByte(byte value)
  {
    int valueInt = 0xFF & ((int)value);
    return BIT_REVERSAL_MAP_BYTE[valueInt];
  }

  public static int getReversedByte(int value)
  {
    isNotByteException(value);
    return BIT_REVERSAL_MAP_INT[value];
  }

  public static long getReversedByte(long value)
  {
    isNotByteException(value);
    return BIT_REVERSAL_MAP_LONG[(int)value];
  }

  public static boolean isByte(int val)
  {
    return val <= 0xFF;
  }

  public static boolean isByte(long val)
  {
    return val <= 0xFFL;
  }

  public static void isNotByteException(int val)
  {
    if (isByte(val)) {
      return;
    }

    throw new IllegalArgumentException("The given value must be less than or equal to 0xFF");
  }

  public static void isNotByteException(long val)
  {
    if (isByte(val)) {
      return;
    }

    throw new IllegalArgumentException("The given value must be less than or equal to 0xFF");
  }

  public static void isNotByteException(int val, String name)
  {
    if (isByte(val)) {
      return;
    }

    throw new IllegalArgumentException("The given value for " +
        name +
        " must be less than or equal to 0xFF");
  }

  public static void isNotByteException(long val, String name)
  {
    if (isByte(val)) {
      return;
    }

    throw new IllegalArgumentException("The given value for " +
        name +
        " must be less than or equal to 0xFF");
  }

  //SWAR
  public static int numOneBits(byte v)
  {
    return numOneBits((int)v);
  }

  private static int numOneBits(int v)
  {
    v = (v & 0x5555) + ((v >> 1) & 0x5555);
    v = (v & 0x3333) + ((v >> 2) & 0x3333);
    v = (v & 0x0f0f) + ((v >> 4) & 0x0f0f);

    return v;
  }

  public static int maxSetBitPosition(byte value)
  {
    int t = (int)value;
    t |= t >> 1;
    t |= t >> 2;
    t |= t >> 4;

    return numOneBits(t) - 1;
  }

  public static boolean ugt(byte a, byte b)
  {
    return ult(b, a);
  }

  public static boolean ugte(byte a, byte b)
  {
    return ulte(b, a);
  }

  public static boolean ulte(byte a, byte b)
  {
    return a == b || ult(a, b);
  }

  public static boolean ult(byte a, byte b)
  {
    if (a > 0) {
      if (b > 0) {
        if (a < b) {
          return true;
        }

        return false;
      } else if (b == 0) {
        return false;
      } else {
        return true;
      }
    } else if (a == 0) {
      if (b == 0) {
        return false;
      }

      return true;
    } else {
      if (b >= 0) {
        return false;
      } else {
        return a > b;
      }
    }
  }

  public static long divCeil(byte value, byte divisor)
  {
    long result = value / divisor;

    if (value % divisor != 0) {
      result++;
    }

    return result;
  }

  public static int uCompareTo(byte a, byte b)
  {
    if (a == b) {
      return 0;
    }

    if (ult(a, b)) {
      return -1;
    }

    return 1;
  }

  public static int uDiv(byte value, byte divisor)
  {
    if (divisor == 0) {
      throw new IllegalArgumentException("Should not divide by zero.");
    }

    if (value == 0) {
      return 0;
    }

    if (IntUtils.ult(value, divisor)) {
      return 0;
    }

    int maxBitPosValue = maxSetBitPosition(value);
    int maxBitPosDivisor = maxSetBitPosition(divisor);
    int quotient = 0;

    for (int diff = maxBitPosValue - maxBitPosDivisor;
         diff >= 0;
         diff--) {
      int tempDivisor = divisor << diff;

      if (IntUtils.ult(value, tempDivisor)) {
        continue;
      }

      value -= tempDivisor;
      quotient |= 1 << diff;
    }

    return quotient;
  }

  public static int uMod(byte value, byte mod)
  {
    int quotient = uDiv(value, mod);
    return value - quotient * mod;
  }

}
