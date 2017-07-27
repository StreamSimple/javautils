package com.simplifi.it.javautil.cmp;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double>
{
  public int compare(Double double1, Double double2)
  {
    return double1.compareTo(double2);
  }
}
