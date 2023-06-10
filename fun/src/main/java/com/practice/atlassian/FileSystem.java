package com.practice.atlassian;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
  private final Map<String, Integer> paths = new HashMap<>();
  public FileSystem() {

  }

  public boolean createPath(String path, int value) {
    if (path == null
      || "".equals(path.trim())
      || "/".equals(path.trim())
      || !path.trim().startsWith("/")
      || this.paths.containsKey(path)) {
      return false;
    }

    /*
     *  /foo/bar/value -> 1
     */
    final int lastSlash = path.lastIndexOf("/");
    final String parent = path.substring(0, lastSlash);
    if (parent.length() > 1 && !this.paths.containsKey(parent)) {
      return false;
    }
    this.paths.put(path, value);
    return true;
  }

  public int get(String path) {
    return this.paths.getOrDefault(path, -1);
  }
}
