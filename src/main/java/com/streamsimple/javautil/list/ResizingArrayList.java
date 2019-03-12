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
package com.streamsimple.javautil.list;

import com.streamsimple.javautil.math.IntUtils;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class ResizingArrayList<T> extends ArrayList<T>
{
  private final ResizePolicy resizePolicy;

  public ResizingArrayList(final ResizePolicy resizePolicy)
  {
    this.resizePolicy = resizePolicy;
  }

  @Override
  public T remove(int index)
  {
    T result = super.remove(index);
    resize();
    return result;
  }

  @Override
  public boolean remove(Object o)
  {
    boolean result = super.remove(o);
    resize();
    return result;
  }

  public T removeLast()
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    return remove(size() - 1);
  }

  private void resize()
  {
    if (resizePolicy.resize(size())) {
      trimToSize();
    }
  }

  interface ResizePolicy
  {
    boolean resize(int size);
  }

  public static class PowerOfTwoResizePolicy implements ResizePolicy
  {
    public static final PowerOfTwoResizePolicy INSTANCE = new PowerOfTwoResizePolicy();

    private PowerOfTwoResizePolicy()
    {
    }

    @Override
    public boolean resize(int size)
    {
      return IntUtils.isPowerOf2(size);
    }
  }
}
