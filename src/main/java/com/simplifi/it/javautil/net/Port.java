package com.simplifi.it.javautil.net;

import com.simplifi.it.javautil.err.Result;
import com.simplifi.it.javautil.err.ReturnErrorImpl;

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
    return MIN_PORT < port && port < MAX_PORT;
  }

  public static final Result<Port> create(int port)
  {
    if (isValidPort(port)) {
      return new Result<>(new Port(port));
    } else {
      return new Result<>(ReturnErrorImpl.create("Invalid port %d", port));
    }
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Port port1 = (Port) o;

    return port == port1.port;
  }

  @Override
  public int hashCode()
  {
    return port;
  }

  @Override
  public String toString()
  {
    return "Port{" +
        "port=" + port +
        '}';
  }
}
