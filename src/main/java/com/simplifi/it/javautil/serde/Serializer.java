package com.simplifi.it.javautil.serde;

public interface Serializer<T>
{
  byte[] serialize(T obj);
}
