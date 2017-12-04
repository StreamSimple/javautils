package com.streamsimple.javautil.cmp;

import java.util.Comparator;

public class LongComparator implements Comparator<Long>
{
  public int compare(Long long1, Long long2)
  {
    return long1.compareTo(long2);
  }
}
