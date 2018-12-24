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
package com.streamsimple.javautil.files;

import com.streamsimple.javautils.testutils.DirTestWatcher;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

public class FileUtilsTest
{
  @Rule
  public final DirTestWatcher dirTestWatcher = new DirTestWatcher.Builder().build();

  @Test
  public void testDeleteRecursive() throws IOException
  {
    final Path dirPath = dirTestWatcher.makeSubDir(Paths.get("a", "b")).toPath();
    final Path filePath = dirPath.resolve("test.txt");

    Files.write(filePath, "hello".getBytes(), StandardOpenOption.CREATE_NEW);
    Assert.assertTrue(Files.exists(filePath));

    FilesUtils.deleteRecursive(dirPath.getParent());
    Assert.assertFalse(Files.exists(dirPath.getParent()));
  }
}
