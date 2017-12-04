package com.streamsimple.javautil.cmp;

import java.util.Comparator;

public class FloatComparator implements Comparator<Float>
{
  public int compare(Float float1, Float float2)
  {
    return float1.compareTo(float2);
  }
}
