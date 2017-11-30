package com.simplifi.it.javautil.serde;

public class SerdeUtils
{
  public static int NUM_BYTES_LONG = 8;

  /**
   * Serializes the given long value to an array of bytes.
   * @param val The long value to serialize.
   * @return The serialized long value.
   */
  public static byte[] serializeLong(long val)
  {
    byte[] buffer = new byte[NUM_BYTES_LONG];

    buffer[0] = (byte)((val >> 56) & 0xFFL);
    buffer[1] = (byte)((val >> 48) & 0xFFL);
    buffer[2] = (byte)((val >> 40) & 0xFFL);
    buffer[3] = (byte)((val >> 32) & 0xFFL);
    buffer[4] = (byte)((val >> 24) & 0xFFL);
    buffer[5] = (byte)((val >> 16) & 0xFFL);
    buffer[6] = (byte)((val >> 8) & 0xFFL);
    buffer[7] = (byte)(val & 0xFFL);

    return buffer;
  }

  /**
   * Deserializes the given long value.
   * @param buffer A serialized long value.
   * @return The deserialized long value.
   */
  public static long deserializeLong(byte[] buffer)
  {
    if (buffer.length != NUM_BYTES_LONG) {
      final String message = String.format("The provided buffer had length %s, but expected it to have length %s",
          buffer.length, NUM_BYTES_LONG);
      throw new IllegalArgumentException(message);
    }

    return (((long)buffer[0]) & 0xFFL) << 56 |
        ((((long)buffer[1]) & 0xFFL) << 48) |
        ((((long)buffer[2]) & 0xFFL) << 40) |
        ((((long)buffer[3]) & 0xFFL) << 32) |
        ((((long)buffer[4]) & 0xFFL) << 24) |
        ((((long)buffer[5]) & 0xFFL) << 16) |
        ((((long)buffer[6]) & 0xFFL) << 8) |
        (((long)buffer[7]) & 0xFFL);
  }
}