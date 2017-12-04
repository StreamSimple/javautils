package com.streamsimple.javautil.serde;

import org.junit.Assert;
import org.junit.Test;

public class SerdeUtilsTest
{
  @Test
  public void simpleLongSerdeTest()
  {
    final long value = 0x0102030405060708L;
    final byte[] valueBytes = SerdeUtils.serializeLong(value);
    final long actualValue = SerdeUtils.deserializeLong(valueBytes);

    Assert.assertEquals(value, actualValue);
  }
}
