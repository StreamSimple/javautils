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
 * This is a class which provides common utility methods for dealing with
 * integers.
 *
 * @author Topeka Labs
 */
public final class IntUtils
{
  public static final int POW_2_0 = 1;
  public static final int POW_2_8 = 256;
  public static final int POW_2_16 = POW_2_8 * POW_2_8;
  public static final int POW_2_32 = POW_2_16 * POW_2_16;

  public static final int POW_2_10 = 1024;
  public static final int POW_2_20 = POW_2_10 * POW_2_10;
  public static final int POW_2_30 = POW_2_10 * POW_2_20;

  public static final int KB = POW_2_10;
  public static final int MB = POW_2_20;
  public static final int GB = POW_2_30;

  public static final IntInterval BIT_INTERVAL = new IntInterval(0, 31);
  public static final IntInterval BIT_COUNT = new IntInterval(1, 32);

  private static final int[] MASK_LSB = new int[64];

  static {
    //Initialize MASK_LSB

    for (int counter = 0;
         counter < 64;
         counter++) {
      int mask = 1 << counter;
      mask = dragBitsRight(mask);
      MASK_LSB[counter] = mask;
    }
  }

  private IntUtils()
  {
    //Do nothing.
  }

  public static void isNotInIntervalInclusiveException(int startingIndex,
                                                       int endingIndex,
                                                       int value)
  {
    IntUtils.isGTEException(endingIndex, startingIndex);

    IntUtils.isGTException(endingIndex, value, "value");
    IntUtils.isLTException(startingIndex, value, "value");
  }

  /**
   * This method throws an IllegalArgumentException if the two given values don't
   * equal.
   *
   * @param value This is compared to equal for equality.
   * @param equal This is compared to value for equality.
   */
  public static void isNotEqualToException(int equal,
                                           int value)
  {
    if (value != equal) {
      throw new IllegalArgumentException("The given value " +
          value +
          " must be equal to " +
          equal +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the two given values don't
   * equal. The name of value is added to the exception message.
   *
   * @param value     This is compared to equal for equality.
   * @param equal     This is compared to value for equality.
   * @param valueName This is the name of value.
   */
  public static void isNotEqualToException(int equal,
                                           int value,
                                           String valueName)
  {
    if (value != equal) {
      throw new IllegalArgumentException("The given value " +
          value + " (" + valueName + ")" +
          " must be equal to " +
          equal +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the two given values don't
   * equal. The name of value is added to the exception message. The name of equals
   * is added to the exception message.
   *
   * @param value     This is compared to equal for equality.
   * @param equal     This is compared to value for equality.
   * @param valueName This is the name of value.
   * @param equalName This is the name of equal.
   */
  public static void isNotEqualToException(int equal,
                                           int value,
                                           String equalName,
                                           String valueName)
  {
    if (value != equal) {
      throw new IllegalArgumentException("The given value " +
          value + " (" + valueName + ")" +
          " must be equal to " +
          equal + " (" + equalName + ")" +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * less than or equal to the threshold.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   */
  public static void isLTEException(int threshold,
                                    int value)
  {
    if (value <= threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " must be greater than " +
          threshold +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * less than or equal to the threshold. The name of value is added to the exception message.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   * @param valueName The name of the given value.
   */
  public static void isLTEException(int threshold,
                                    int value,
                                    String valueName)
  {
    if (value <= threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " " +
          valueName +
          " " +
          " must be greater than " +
          threshold +
          ".");
    }
  }

  public static void isLTEException(int threshold,
                                    int value,
                                    String thresholdName,
                                    String valueName)
  {
    if (value <= threshold) {
      throw new IllegalArgumentException("The given value " + value +
          " " + valueName +
          " must be greater than " +
          thresholdName + " " +
          threshold + ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * less than the threshold.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   */
  public static void isLTException(int threshold,
                                   int value)
  {
    if (value < threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " must be greater than or equal to " +
          threshold +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * less than the threshold. The name of value is added to the exception message.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   * @param valueName The name of the given value.
   */
  public static void isLTException(int threshold,
                                   int value,
                                   String valueName)
  {
    if (value < threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " " +
          valueName +
          " must be greater than or equal to " +
          threshold +
          ".");
    }
  }

  /**
   * @param threshold
   * @param value
   * @param thresholdName
   * @param valueName
   */
  public static void isLTException(int threshold,
                                   int value,
                                   String thresholdName,
                                   String valueName)
  {
    if (value < threshold) {
      throw new IllegalArgumentException("The given value " +
          valueName + " " + value +
          " is less than " +
          thresholdName + " " + threshold);
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * greater than the threshold.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   */
  public static void isGTException(int threshold,
                                   int value)
  {
    if (value > threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " must be less or equal to " +
          threshold +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * greater than the threshold. The name of value is added to the exception message.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   * @param valueName The name of the given value.
   */
  public static void isGTException(int threshold,
                                   int value,
                                   String valueName)
  {
    if (value > threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " " +
          valueName +
          " " +
          " must be less than or equal to " +
          threshold +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * greater than or equal to the threshold.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   */
  public static void isGTEException(int threshold,
                                    int value)
  {
    if (value >= threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " must be less than " +
          threshold +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is
   * greater than or equal to the threshold. The name of value is added to the exception message.
   *
   * @param value     The value to check.
   * @param threshold The threshold to compare against.
   * @param valueName The name of the given value.
   */
  public static void isGTEException(int threshold,
                                    int value,
                                    String valueName)
  {
    if (value >= threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " " +
          valueName +
          " " +
          " must be less than " +
          threshold +
          ".");
    }
  }

  public static void isGTEException(int threshold,
                                    int value,
                                    String thresholdName,
                                    String valueName)
  {
    if (value >= threshold) {
      throw new IllegalArgumentException("The given value " +
          value +
          " " +
          valueName +
          " " +
          " must be less than " +
          threshold +
          " " +
          thresholdName +
          ".");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is not
   * positive.
   *
   * @param value The value to check.
   */
  public static void isNonPositiveException(int value)
  {
    if (value <= 0) {
      throw new IllegalArgumentException("The given value " + value + " must be positive.");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is not
   * positive.
   *
   * @param value     The value to check.
   * @param valueName The name of the value.
   */
  public static void isNonPositiveException(int value,
                                            String valueName)
  {
    if (value <= 0) {
      throw new IllegalArgumentException("The given value " + valueName + " " + value + " must be positive.");
    }
  }

  public static void isZeroException(int value)
  {
    if (value == 0) {
      throw new IllegalArgumentException("The given value " + value + " cannot be zero.");
    }
  }

  public static void isZeroException(int value,
                                     String valueName)
  {
    if (value == 0) {
      throw new IllegalArgumentException("The given value " +
          valueName + " " + value +
          " cannot be zero.");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is negative.
   *
   * @param value The value to check.
   */
  public static void isNegativeException(int value)
  {
    if (value < 0) {
      throw new IllegalArgumentException("The given value " + value + " must be nonegative.");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is negative.
   *
   * @param value     The value to check.
   * @param valueName The name of the value.
   */
  public static void isNegativeException(int value, String valueName)
  {
    if (value < 0) {
      throw new IllegalArgumentException("The given value " + valueName + " " + value + " must be nonegative.");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is non negative.
   *
   * @param value The value to check.
   */
  public static void isNonNegativeException(int value)
  {
    if (value >= 0) {
      throw new IllegalArgumentException("The given value " + value + " must be negative.");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is non negative.
   *
   * @param value     The value to check.
   * @param valueName The name of the value.
   */
  public static void isNonNegativeException(int value, String valueName)
  {
    if (value >= 0) {
      throw new IllegalArgumentException("The given value " + valueName + " " + value + " must be negative.");
    }
  }

  /**
   * This method throws and IllegalArgumentException if the given value is positive.
   *
   * @param value The value to check.
   */
  public static void isPositiveException(int value)
  {
    if (value > 0) {
      throw new IllegalArgumentException("The given value " + value + " must be nonpositive.");
    }
  }

  /**
   * This method throws an IllegalArgumentException if the given value is positive.
   *
   * @param value     The value to check.
   * @param valueName The name of the value.
   */
  public static void isPositiveException(int value, String valueName)
  {
    if (value > 0) {
      throw new IllegalArgumentException("The given value " + valueName + " " + value + " must be nonpositive.");
    }
  }

  public static void isNotUnsignedPowerOf2Exception(int value)
  {
    if (!IntUtils.isUPowerOf2(value)) {
      throw new IllegalArgumentException("The given value " +
          IntUtils.uToString(value) +
          " is not a power of 2");
    }
  }

  public static void isNotUnsignedPowerOf2Exception(int value,
                                                    String valueName)
  {
    if (!IntUtils.isUPowerOf2(value)) {
      throw new IllegalArgumentException("The given value " +
          valueName + " " +
          IntUtils.uToString(value) +
          " is not a power of 2");
    }
  }

  public static void isTrivialNonPositivePowerOf2Exception(int value)
  {
    isLTException(1, value);
    isNotUnsignedPowerOf2Exception(value);
  }

  public static void isTrivialNonPositivePowerOf2Exception(int value,
                                                           String valueName)
  {
    isLTException(1, value, valueName);
    isNotUnsignedPowerOf2Exception(value, valueName);
  }

  /**
   * This method returns the absolute value of the provided value.
   *
   * @param value The value to take the absolute value of.
   * @return The absolute value of the given value.
   */
  public static int abs(int value)
  {
    if (value < 0) {
      return -value;
    }

    return value;
  }

  /**
   * This method throws an IllegalArgumentException if the two provided integers
   * cannot be added without an overflow or underflow occurring.
   *
   * @param a An integer to add.
   * @param b An integer to add.
   */
  public static void safeAddException(int a, int b)
  {
    if (a == 0 || b == 0) {
      return;
    }

    if ((a < 0 && b > 0) ||
        (a > 0 && b < 0)) {
      return;
    }

    if (a < 0) {
      if (a + b >= 0) {
        throw new IllegalArgumentException("Adding the two numbers " + a +
            " and " + b + " causes an underflow.");
      }
    } else {
      if (a + b <= 0) {
        throw new IllegalArgumentException("Adding the two numbers " + a +
            " and " + b + " causes an overflow.");
      }
    }
  }

  /**
   * This method returns true if the two provided integers can be added with an
   * overflow or underflow.
   *
   * @param a An integer to be added.
   * @param b An integer to be added.
   * @return The sum of the two provided integers.
   */
  public static boolean isSafeAdd(int a, int b)
  {
    if (a == 0 || b == 0) {
      return true;
    }

    if ((a < 0 && b > 0) ||
        (a > 0 && b < 0)) {
      return true;
    }

    if (a < 0) {
      if (a + b >= 0) {
        return false;
      }
    } else {
      if (a + b <= 0) {
        return false;
      }
    }

    return true;
  }

  /**
   * This method safely adds two integers. An IllegalArgumentException is thrown
   * if an underflow or overflow would occur.
   *
   * @param a An integer to add.
   * @param b An integer to add.
   * @return The sum of the two input integers.
   */
  public static int safeAdd(int a, int b)
  {
    safeAddException(a, b);
    return a + b;
  }

  public static boolean ugt(int a, int b)
  {
    return ult(b, a);
  }

  public static boolean ugte(int a, int b)
  {
    return ulte(b, a);
  }

  public static boolean ulte(int a, int b)
  {
    return a == b || ult(a, b);
  }

  public static boolean ult(int a, int b)
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

  public static int divCeil(int value, int divisor)
  {
    int result = value / divisor;

    if (value % divisor != 0) {
      result++;
    }

    return result;
  }

  public static int uCompareTo(int a, int b)
  {
    if (a == b) {
      return 0;
    }

    if (ult(a, b)) {
      return -1;
    }

    return 1;
  }

  public static int uDiv(int value, int divisor)
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

  public static int uMod(int value, int mod)
  {
    int quotient = uDiv(value, mod);
    return value - quotient * mod;
  }

  public static int bitReverse(int value)
  {
    int byte1 = getByte0(value);
    int byte2 = getByte1(value);
    int byte3 = getByte2(value);
    int byte4 = getByte3(value);

    byte1 = ByteUtils.getReversedByte(byte1);
    byte2 = ByteUtils.getReversedByte(byte2);
    byte3 = ByteUtils.getReversedByte(byte3);
    byte4 = ByteUtils.getReversedByte(byte4);

    int rValue = 0;
    rValue |= byte1 << 24;
    rValue |= byte2 << 16;
    rValue |= byte3 << 8;
    rValue |= byte4;

    return rValue;
  }

  public static int getByte0(int val)
  {
    return val & 0xFF;
  }

  public static byte getByte0B(int val)
  {
    return (byte)getByte0(val);
  }

  public static int getByte1(int val)
  {
    return (val >> 8) & 0xFF;
  }

  public static byte getByte1B(int val)
  {
    return (byte)getByte1(val);
  }

  public static int getByte2(int val)
  {
    return (val >> 16) & 0xFF;
  }

  public static byte getByte2B(int val)
  {
    return (byte)getByte2(val);
  }

  public static int getByte3(int val)
  {
    return (val >> 24) & 0xFF;
  }

  public static byte getByte3B(int val)
  {
    return (byte)getByte3(val);
  }

  public static void setBytes(int value,
                              int offset,
                              byte[] bytes)
  {
    bytes[offset] = getByte0B(value);
    bytes[++offset] = getByte1B(value);
    bytes[++offset] = getByte2B(value);
    bytes[++offset] = getByte3B(value);
  }

  public static boolean isUPowerOf2(int value)
  {
    return (~value + 1L & value) == value;
  }

  public static boolean isBitSet(int value, int bit)
  {
    return (value & (1 << bit)) > 0;
  }

  //SWAR
  public static int numOneBits(int v)
  {
    v = (v & 0x55555555) + ((v >> 1) & 0x55555555);
    v = (v & 0x33333333) + ((v >> 2) & 0x33333333);
    v = (v & 0x0f0f0f0f) + ((v >> 4) & 0x0f0f0f0f);
    v = (v & 0x00ff00ff) + ((v >> 8) & 0x00ff00ff);
    v = (v & 0x0000ffff) + ((v >> 16) & 0x0000ffff);

    return v;
  }

  public static int maxSetBitPosition(int value)
  {
    value = dragBitsRight(value);
    return numOneBits(value) - 1;
  }

  public static int dragBitsRight(int value)
  {
    value |= value >> 1;
    value |= value >> 2;
    value |= value >> 4;
    value |= value >> 8;
    value |= value >> 16;

    return value;
  }

  public static int maskLSB(int numBits)
  {
    IntUtils.BIT_COUNT.isInIntervalInclusiveException(numBits, "numBits");
    return MASK_LSB[numBits - 1];
  }

  public static int ulog2Floor(int value)
  {
    return maxSetBitPosition(value);
  }

  public static String uToOctalString(int value)
  {
    return uToString(value, 8);
  }

  public static String uToHexString(int value)
  {
    return uToString(value, 16);
  }

  public static String uToString(int value)
  {
    return uToString(value, 10);
  }

  public static String uToString(int value, int radix)
  {
    if (value == 0) {
      return "0";
    }

    StringBuilder sb = new StringBuilder();

    for (;
         IntUtils.ugt(value, 0);
         value = IntUtils.uDiv(value, radix)) {
      sb.append(IntUtils.uMod(value, radix));
    }

    return sb.toString();
  }
}
