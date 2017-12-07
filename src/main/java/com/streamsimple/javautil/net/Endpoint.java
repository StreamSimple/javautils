package com.streamsimple.javautil.net;

import com.streamsimple.javautil.args.Preconditions;

public class Endpoint
{
  private final Host host;
  private final Port port;

  public Endpoint(final Host host, final Port port)
  {
    this.host = Preconditions.checkNotNull(host);
    this.port = Preconditions.checkNotNull(port);
  }

  public Host getHost()
  {
    return host;
  }

  public Port getPort()
  {
    return port;
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

    Endpoint endpoint = (Endpoint)o;

    if (!host.equals(endpoint.host)) {
      return false;
    }
    return port.equals(endpoint.port);
  }

  @Override
  public int hashCode()
  {
    int result = host.hashCode();
    result = 31 * result + port.hashCode();
    return result;
  }

  @Override
  public String toString()
  {
    return host + ":" + port;
  }
}
