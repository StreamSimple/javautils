/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
