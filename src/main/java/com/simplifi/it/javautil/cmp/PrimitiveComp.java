package com.simplifi.it.javautil.cmp;

public class PrimitiveComp
{
  private PrimitiveComp()
  {
  }

  public static int compare(byte value1, byte value2)
  {
    return value1 - value2;
  }

  public static int compare(short value1, short value2)
  {
    return value1 - value2;
  }

  public static int compare(int value1, int value2)
  {
    return value1 - value2;
  }

  public static int compare(long value1, long value2)
  {
    if (value1 < value2) {
      return -1;
    } else if (value1 == value2) {
      return 0;
    } else {
      return 1;
    }
  }

  public static int compare(float value1, float value2)
  {
    if (value1 < value2) {
      return -1;
    } else if (value1 == value2) {
      return 0;
    } else {
      return 1;
    }
  }

  public static int compare(double value1, double value2)
  {
    if (value1 < value2) {
      return -1;
    } else if (value1 == value2) {
      return 0;
    } else {
      return 1;
    }
  }
}
