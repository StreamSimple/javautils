package com.streamsimple.javautil.net.hunt;

import java.io.IOException;
import java.net.ServerSocket;
import com.streamsimple.javautil.net.Port;

public class PortCheckerImpl implements PortChecker
{
  @Override
  public boolean isAvailable(Port port)
  {
    final ServerSocket serverSocket;

    try {
      serverSocket = new ServerSocket(port.toInt());
    } catch (IOException e) {
      return false;
    }

    try {
      serverSocket.close();
    } catch (IOException e) {
      // Do nothing
    }

    return true;
  }
}
