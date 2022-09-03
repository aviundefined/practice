package com.practice;

import java.io.IOException;

public interface ILineReader {
  boolean hasNext();
  String next();
  void close() throws IOException;
}
