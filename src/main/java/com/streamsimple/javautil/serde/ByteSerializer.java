package com.streamsimple.javautil.serde;

public class ByteSerializer implements Serializer<byte[]>
{
  @Override
  public byte[] serialize(byte[] bytes)
  {
    return bytes;
  }
}
