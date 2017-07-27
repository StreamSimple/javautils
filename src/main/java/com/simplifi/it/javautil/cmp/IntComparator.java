package com.simplifi.it.javautil.cmp;

import java.util.Comparator;

public class IntComparator implements Comparator<Integer>
{
  public int compare(Integer int1, Integer int2)
  {
    return int1.compareTo(int2);
  }
}
