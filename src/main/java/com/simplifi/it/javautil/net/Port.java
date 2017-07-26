package com.simplifi.it.javautil.net;

public class Port
{
  public static final int MIN_PORT = 0;
  public static final int MAX_PORT = 65535;

  private int port;

  public Port(int port)
  {
    if (port < MIN_PORT) {
      throw new IllegalArgumentException("The given port " + port + " is less than " + MIN_PORT);
    }

    if (port > MAX_PORT) {
      throw new IllegalArgumentException("The given port " + port + " is greater than " + MAX_PORT);
    }

    this.port = port;
  }

  public int toInt()
  {
    return port;
  }

  public static final boolean isValidPort(int port)
  {
    return port > MIN_PORT && port < MAX_PORT;
  }
}
