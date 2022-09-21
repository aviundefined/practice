package com.practice.dataprovider;

import java.io.IOException;

public interface IDataProvider {
  boolean hasNext();
  String next();
  void close() throws IOException;
}
