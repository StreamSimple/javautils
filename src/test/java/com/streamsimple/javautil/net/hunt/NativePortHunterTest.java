/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.streamsimple.javautil.net.hunt;

import com.streamsimple.javautil.net.Port;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.Assert;
import org.junit.Test;

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
