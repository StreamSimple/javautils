package com.streamsimple.javautil.net.hunt;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;
import com.streamsimple.javautil.net.Port;

public class NativePortHunterTest
{
  @Test
  public void simpleStartRequest()
  {
    final MockPortChecker portChecker = new MockPortChecker();
    final NaivePortHunter portHunter = new NaivePortHunter(portChecker);

    List<Port> expectedPorts = new ArrayList<>();
    expectedPorts.add(new Port(100));
    expectedPorts.add(new Port(101));
    expectedPorts.add(new Port(102));

    List<Port> actualPorts = portHunter.getPorts(new Port(100), 3);

    Assert.assertEquals(expectedPorts, actualPorts);
  }

  @Test
  public void simpleStartSkipRequest()
  {
    final Set<Port> skipPorts = new HashSet<>();
    skipPorts.add(new Port(100));
    skipPorts.add(new Port(102));

    final MockPortChecker portChecker = new MockPortChecker(skipPorts);
    final NaivePortHunter portHunter = new NaivePortHunter(portChecker);

    List<Port> expectedPorts = new ArrayList<>();
    expectedPorts.add(new Port(101));
    expectedPorts.add(new Port(103));
    expectedPorts.add(new Port(104));

    List<Port> actualPorts = portHunter.getPorts(new Port(100), 3);

    Assert.assertEquals(expectedPorts, actualPorts);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidStartRequest()
  {
    final MockPortChecker portChecker = new MockPortChecker();
    final NaivePortHunter portHunter = new NaivePortHunter(portChecker);

    portHunter.getPorts(new Port(100), 70000);
  }

  @Test
  public void simpleRangeRequest()
  {
    final MockPortChecker portChecker = new MockPortChecker();
    final NaivePortHunter portHunter = new NaivePortHunter(portChecker);

    List<Port> expectedPorts = new ArrayList<>();
    expectedPorts.add(new Port(101));
    expectedPorts.add(new Port(102));

    List<Port> actualPorts = portHunter.getPorts(new Port(101), new Port(110), 2);

    Assert.assertEquals(expectedPorts, actualPorts);
  }

  @Test(expected = IllegalArgumentException.class)
  public void invalidRangeRequest()
  {
    final MockPortChecker portChecker = new MockPortChecker();
    final NaivePortHunter portHunter = new NaivePortHunter(portChecker);

    portHunter.getPorts(new Port(100), new Port(101), 5);
  }

  public static class MockPortChecker implements PortChecker
  {
    private final Set<Port> forbiddenPorts;

    public MockPortChecker()
    {
      this.forbiddenPorts = new HashSet<>();
    }

    public MockPortChecker(final Set<Port> forbiddenPorts)
    {
      this.forbiddenPorts = forbiddenPorts;
    }

    @Override
    public boolean isAvailable(Port port)
    {
      return !forbiddenPorts.contains(port);
    }
  }
}
