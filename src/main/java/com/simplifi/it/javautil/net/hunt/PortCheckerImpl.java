package com.simplifi.it.javautil.net.hunt;

import com.simplifi.it.javautil.net.Port;
import java.io.IOException;
import java.net.ServerSocket;

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
    }

    return true;
  }
}
