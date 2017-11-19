package com.simplifi.it.javautil.net.hunt;

import com.simplifi.it.javautil.net.Port;

public interface PortChecker
{
  boolean isAvailable(Port port);
}
