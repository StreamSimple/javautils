package com.streamsimple.javautil.serde;

import java.nio.charset.StandardCharsets;
import org.junit.Assert;
import org.junit.Test;

public class StringSerdeTest
{
  @Test
  public void simpleSerdeTest()
  {
    final StringSerializer serializer = new StringSerializer.Builder()
        .setCharsetName(StandardCharsets.US_ASCII.name())
        .build();

    final StringDeserializer deserializer = new StringDeserializer.Builder()
        .setCharsetName(StandardCharsets.US_ASCII.name())
        .build();

    final String expected = "I'm flying";
    final byte[] bytes = serializer.serialize(expected);
    final String actual = deserializer.deserialize(bytes);

    Assert.assertEquals(expected, actual);
  }
}
