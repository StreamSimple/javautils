package com.streamsimple.javautil.sets;

import java.util.HashSet;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

public class SetUtilsTest
{
  @Test
  public void disjointIntersectionTest()
  {
    Set<String> setA = new HashSet<String>();
    setA.add("A");
    setA.add("B");
    setA.add("C");

    Set<String> setB = new HashSet<String>();
    setB.add("1");
    setB.add("2");

    Set<String> intersection = SetUtils.intersection(setA, setB);
    Assert.assertTrue(intersection.isEmpty());
  }

  @Test
  public void intersectionTest()
  {
    Set<String> setA = new HashSet<String>();
    setA.add("A");
    setA.add("B");
    setA.add("C");

    Set<String> setB = new HashSet<String>();
    setB.add("A");
    setB.add("B");
    setB.add("1");
    setB.add("2");

    Set<String> intersection = SetUtils.intersection(setA, setB);

    Set<String> expected = new HashSet<String>();
    expected.add("A");
    expected.add("B");

    Assert.assertEquals(expected, intersection);
  }
}
