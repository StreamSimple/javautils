package com.simplifi.it.javautil.serde;

public class ByteSerializer implements Serializer<byte[]>
{
  @Override
  public byte[] serialize(byte[] bytes)
  {
    return bytes;
  }
}
