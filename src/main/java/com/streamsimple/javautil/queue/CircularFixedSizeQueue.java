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
package com.streamsimple.javautil.queue;

import com.streamsimple.guava.common.base.Preconditions;
import java.util.NoSuchElementException;

public class CircularFixedSizeQueue<E>
{
  private final E[] array;
  private int start = 0;
  private int end = 0;
  private boolean empty = true;

  public CircularFixedSizeQueue(int maxSize)
  {
    Preconditions.checkArgument(maxSize > 1);
    this.array = (E[])new Object[maxSize];
  }

  public void clear()
  {
    start = 0;
    end = 0;
    empty = true;

    for (int i = 0; i < array.length; i++) {
      array[i] = null;
    }
  }

  public void offer(E element)
  {
    int oldEnd = end;
    array[end] = element;
    end = circularMod(end + 1, array.length);

    if (!empty && start == oldEnd) {
      start = end;
    }
    empty = false;
  }

  public E get(int index)
  {
    if (index >= size()) {
      throw new IndexOutOfBoundsException();
    }

    int arrayIndex = circularMod(start + index, array.length);
    return array[arrayIndex];
  }

  public E poll() throws NoSuchElementException
  {
    if (isEmpty()) {
      throw new NoSuchElementException();
    }

    E result = array[start];
    start = circularMod(start + 1, array.length);
    empty = start == end;
    return result;
  }

  public E peek()
  {
    if (isEmpty()) {
      return null;
    }

    return array[start];
  }

  public int size()
  {
    if (empty) {
      return 0;
    } else if (start < end) {
      return end - start;
    } else {
      return array.length - (start - end);
    }
  }

  public boolean isEmpty()
  {
    return empty;
  }

  public static int circularMod(int val, int mod)
  {
    int result = val % mod;

    if (result < 0) {
      return result + mod;
    } else {
      return result;
    }
  }
}
