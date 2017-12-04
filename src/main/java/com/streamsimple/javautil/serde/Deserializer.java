package com.streamsimple.javautil.serde;

public interface Deserializer<T>
{
  T deserialize(byte[] bytes);
}
