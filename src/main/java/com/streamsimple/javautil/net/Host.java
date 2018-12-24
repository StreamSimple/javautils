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

import com.streamsimple.javautil.err.Result;
import com.streamsimple.javautil.err.ReturnErrorImpl;
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

    Host host1 = (Host)o;

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
