package com.streamsimple.javautil.sets;

import java.util.HashSet;
import java.util.Set;

public class SetUtils
{
  public static <T> Set<T> intersection(Set<T> setA, Set<T> setB)
  {
    Set<T> intersectionSet = new HashSet<T>();

    for (T element: setA) {
      if (setB.contains(element)) {
        intersectionSet.add(element);
      }
    }

    return intersectionSet;
  }
}
