package com.simplifi.it.javautil.net;

public class PortUtils
{
  public static final int DEFAULT_HTTP_PORT = 80;
  public static final int DEFAULT_HTTPS_PORT = 443;
  public static final int DEFAULT_SSH_PORT = 22;

  public static final Port HTTP_PORT = new Port(DEFAULT_HTTP_PORT);
  public static final Port HTTPS_PORT = new Port(DEFAULT_HTTPS_PORT);
  public static final Port SSH_PORT = new Port(DEFAULT_SSH_PORT);
}
