package com.streamsimple.javautil.net.hunt;

import com.streamsimple.javautil.net.Port;

public interface PortChecker
{
  boolean isAvailable(Port port);
}
