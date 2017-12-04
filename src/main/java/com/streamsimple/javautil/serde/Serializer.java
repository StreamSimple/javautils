package com.streamsimple.javautil.serde;

public interface Serializer<T>
{
  byte[] serialize(T obj);
}
