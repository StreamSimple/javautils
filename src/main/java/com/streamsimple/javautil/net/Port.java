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
package com.streamsimple.javautil.net;

import java.util.Comparator;
import com.streamsimple.javautil.err.Result;
import com.streamsimple.javautil.err.ReturnErrorImpl;

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

    Port port1 = (Port)o;

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
    return Integer.toString(port);
  }

  public static class Compare implements Comparator<Port>
  {
    public static final Compare INSTANCE = new Compare();

    private Compare()
    {
    }

    @Override
    public int compare(Port portA, Port portB)
    {
      return portA.toInt() - portB.toInt();
    }
  }
}
