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
import com.streamsimple.javautil.lang.KeyValue;
import com.streamsimple.javautil.list.ResizingArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * MinHeap
 * @param <V>
 */
public class HeapFastRemove<K, V>
{
  private ResizingArrayList<V> heap = new ResizingArrayList<V>(ResizingArrayList.PowerOfTwoResizePolicy.INSTANCE);
  private ResizingArrayList<K> keys = new ResizingArrayList<K>(ResizingArrayList.PowerOfTwoResizePolicy.INSTANCE);

  private Map<K, Integer> objectToIndex = new HashMap<>();
  private Comparator<V> comparator;

  public HeapFastRemove(Comparator<V> comparator)
  {
    this.comparator = comparator;
  }

  private void listAdd(K key, V element)
  {
    heap.add(element);
    keys.add(key);
  }

  private void listRemoveLast()
  {
    heap.removeLast();
    keys.removeLast();
  }

  private void listSet(int index, K key, V element)
  {
    heap.set(index, element);
    keys.set(index, key);
  }

  public boolean offer(K key, V element)
  {
    Preconditions.checkNotNull(element);

    if (objectToIndex.containsKey(element)) {
      return false;
    }

    int index = size();
    listAdd(key, element);
    objectToIndex.put(key, index);
    percolateUp(index, element, key);

    return true;
  }

  public V peek()
  {
    if (isEmpty()) {
      return null;
    } else {
      return heap.get(0);
    }
  }

  public V poll()
  {
    if (isEmpty()) {
      return null;
    } else {
      return remove(0);
    }
  }

  public KeyValue<K, V> pollWithKey()
  {
    if (isEmpty()) {
      return null;
    } else {
      K key = keys.get(0);
      V val = remove(0);

      return new KeyValue<>(key, val);
    }
  }

  public void remove(K key)
  {
    Integer index = objectToIndex.get(key);

    if (index == null) {
      throw new NoSuchElementException();
    }

    objectToIndex.remove(key);
    percolateDown(index);
    heap.removeLast();
  }

  private V remove(int index)
  {
    K key = keys.get(index);
    V element = heap.get(index);
    objectToIndex.remove(key);
    percolateDown(index);
    listRemoveLast();
    return element;
  }

  private void percolateUp(int index, V element, K key)
  {
    while (index != 0) {
      int parentIndex = parent(index);
      V parent = heap.get(parentIndex);
      K parentKey = keys.get(parentIndex);

      if (comparator.compare(parent, element) <= 0) {
        return;
      }

      listSet(index, parentKey, parent);
      listSet(parentIndex, key, element);

      objectToIndex.put(parentKey, index);
      objectToIndex.put(key, parentIndex);

      index = parentIndex;
    }
  }

  private void percolateDown(int index)
  {
    while (index < size()) {
      final int firstIndex = firstChild(index);
      final int secondIndex = secondChild(index);

      final V first = nullGetElement(firstIndex);
      final V second = nullGetElement(secondIndex);

      final K firstKey = nullGetKey(firstIndex);
      final K secondKey = nullGetKey(secondIndex);

      final int nextIndex;
      final V next;
      final K nextKey;

      if (nullCompare(first, second) <= 0) {
        nextIndex = firstIndex;
        next = first;
        nextKey = firstKey;
      } else {
        nextIndex = secondIndex;
        next = second;
        nextKey = secondKey;
      }

      if (next == null) {
        return;
      }

      listSet(index, nextKey, next);
      objectToIndex.put(nextKey, index);

      if (secondIndex == size() - 1 &&
          firstIndex == nextIndex) {
        listSet(firstIndex, secondKey, second);
        objectToIndex.put(secondKey, firstIndex);
        // We are done
        return;
      } else {
        index = nextIndex;
      }
    }
  }

  private V nullGetElement(int index)
  {
    return index < size() ? heap.get(index) : null;
  }

  private K nullGetKey(int index)
  {
    return index < size() ? keys.get(index) : null;
  }

  private int nullCompare(V first, V second)
  {
    if (first == null) {
      return 0;
    } else if (second == null) {
      return -1;
    } else {
      return comparator.compare(first, second);
    }
  }

  public boolean isEmpty()
  {
    return size() == 0;
  }

  public int size()
  {
    return heap.size();
  }

  public static int parent(int index)
  {
    return index / 2;
  }

  public static int firstChild(int index)
  {
    return index * 2 + 1;
  }

  public static int secondChild(int index)
  {
    return index * 2 + 2;
  }
}
