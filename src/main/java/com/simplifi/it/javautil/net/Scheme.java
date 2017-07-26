package com.simplifi.it.javautil.net;

import com.simplifi.it.javautil.args.Preconditions;

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
