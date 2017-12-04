package com.streamsimple.javautil.net;

import com.streamsimple.javautil.args.Preconditions;

public enum Scheme
{
  HTTP("http"), HTTPS("https");

  private String text;

  Scheme(String text)
  {
    this.text = Preconditions.checkNotNull(text);
  }

  public String getText()
  {
    return text;
  }
}
