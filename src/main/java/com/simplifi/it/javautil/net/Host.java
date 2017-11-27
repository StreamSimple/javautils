package com.simplifi.it.javautil.net;

import com.simplifi.it.javautil.err.Result;
import com.simplifi.it.javautil.err.ReturnErrorImpl;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Host
{
  public static final Host LOCAL = new Host("localhost");

  private final String host;

  public Host(final String host)
  {
    if (host == null) {
      throw new NullPointerException();
    }

    try {
      InetAddress.getByName(host);
    } catch (UnknownHostException e) {
      throw new IllegalArgumentException(e);
    }

    this.host = host;
  }

  public static final boolean isValid(final String host)
  {
    if (host == null) {
      throw new NullPointerException();
    }

    try {
      InetAddress.getByName(host);
      return true;
    } catch (UnknownHostException e) {
      return false;
    }
  }

  public static final Result<Host> create(final String host)
  {
    if (host == null) {
      throw new NullPointerException();
    }

    if (isValid(host)) {
      return Result.create(new Host(host));
    } else {
      return Result.create(ReturnErrorImpl.create(host));
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

    Host host1 = (Host) o;

    return host.equals(host1.host);
  }

  @Override
  public int hashCode()
  {
    return host.hashCode();
  }

  @Override
  public String toString()
  {
    return host;
  }
}
