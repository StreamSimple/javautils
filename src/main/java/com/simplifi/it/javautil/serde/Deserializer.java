package com.simplifi.it.javautil.serde;

public interface Deserializer<T>
{
  T deserialize(byte[] bytes);
}
