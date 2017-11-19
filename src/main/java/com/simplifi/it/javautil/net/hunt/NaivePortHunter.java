package com.simplifi.it.javautil.net.hunt;

import com.simplifi.it.javautil.net.Port;
import java.util.ArrayList;
import java.util.List;

/**
 * This port hunter checks if a port is available then adds the port to a list of available ports. <b>Note:</b>
 * this strategy can suffer from race conditions but should be sufficient for testing in a container or on a
 * developer's laptop.
 */
public class NaivePortHunter
{
  private final PortChecker portChecker;

  public NaivePortHunter()
  {
    portChecker = new PortCheckerImpl();
  }

  protected NaivePortHunter(final PortChecker portChecker)
  {
    this.portChecker = portChecker;
  }

  public List<Port> getPorts(final Port start, final int numPort)
  {
    validateNumPort(numPort);
    final int portMax = start.toInt() + numPort - 1;

    if (portMax > Port.MAX_PORT) {
      final String message = String.format("Not enough ports available to create %d ports", numPort);
      throw new IllegalArgumentException(message);
    }

    final List<Port> ports = new ArrayList<>();

    for (int portCounter = 0; portCounter < numPort;) {

      if (start.toInt() + portCounter > Port.MAX_PORT) {
        final String message = String.format("Only found %d ports of %d requested ports",
            ports.size(), numPort);
        throw new IllegalStateException(message);
      }

      final Port port = new Port(start.toInt() + portCounter);

      if (portChecker.isAvailable(port)) {
        ports.add(port);
        portCounter++;
      }
    }

    return ports;
  }

  public List<Port> getPorts(final Port start, final Port end, final int numPort)
  {
    validateNumPort(numPort);

    if (start.toInt() >= end.toInt()) {
      final String message = String.format("Start %d must be less then end %d", start.toInt(), end.toInt());
      throw new IllegalArgumentException(message);
    }

    final List<Port> ports = getPorts(start, numPort);

    if (ports.get(ports.size() - 1).toInt() > end.toInt()) {
      final String message = String.format("Could not allocate %d ports between %d and %d inclusive.",
          numPort, start.toInt(), end.toInt());
      throw new IllegalArgumentException(message);
    }

    return ports;
  }

  private static void validateNumPort(final int numPort)
  {
    if (numPort <= 0) {
      final String message = String.format("numPort must be positive but was %d", numPort);
      throw new IllegalArgumentException(message);
    }
  }
}
