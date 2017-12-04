package com.streamsimple.javautil.serde;

import com.streamsimple.javautil.args.Preconditions;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;

public class StringSerializer implements Serializer<String>
{
  private final Charset charset;

  private StringSerializer(final String charsetName)
  {
    this.charset = Charset.forName(charsetName);
  }

  @Override
  public byte[] serialize(String value)
  {
    return value.getBytes(charset);
  }

  public static class Builder
  {
    private String charsetName = StandardCharsets.UTF_8.name();

    public Builder()
    {
    }

    public Builder setCharsetName(final String charsetName)
    {
      Preconditions.checkNotNull(charsetName);

      try {
        Charset.forName(charsetName);
      } catch (UnsupportedCharsetException e) {
        throw new IllegalArgumentException(e.getMessage());
      }

      this.charsetName = charsetName;
      return this;
    }

    public StringSerializer build()
    {
      return new StringSerializer(charsetName);
    }
  }
}
