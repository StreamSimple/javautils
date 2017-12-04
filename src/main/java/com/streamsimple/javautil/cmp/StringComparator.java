package com.streamsimple.javautil.cmp;

import java.util.Comparator;

public class StringComparator implements Comparator<String>
{
  public int compare(String string1, String string2)
  {
    return string1.compareTo(string2);
  }
}
