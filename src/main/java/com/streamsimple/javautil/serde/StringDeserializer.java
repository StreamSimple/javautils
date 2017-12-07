package com.streamsimple.javautil.serde;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.charset.UnsupportedCharsetException;
import com.streamsimple.javautil.args.Preconditions;

public class StringDeserializer implements Deserializer<String>
{
  private final Charset charset;

  private StringDeserializer(final String charsetName)
  {
    this.charset = Charset.forName(charsetName);
  }

  @Override
  public String deserialize(byte[] bytes)
  {
    return new String(bytes, charset);
  }

  public static class Builder
  {
    private String charsetName = StandardCharsets.UTF_8.name();

    public Builder()
    {
    }

    public StringDeserializer.Builder setCharsetName(final String charsetName)
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

    public StringDeserializer build()
    {
      return new StringDeserializer(charsetName);
    }
  }
}
