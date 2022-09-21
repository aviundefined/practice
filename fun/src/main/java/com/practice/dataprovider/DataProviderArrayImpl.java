package com.practice.dataprovider;

import java.io.IOException;

public final class DataProviderArrayImpl implements IDataProvider {

  private final String[] tokens;
  int curr = 0;
  public DataProviderArrayImpl(String[] tokens) {
    this.tokens = tokens;
  }

  @Override
  public boolean hasNext() {
    return curr < tokens.length;
  }

  @Override
  public String next() {
    final String token = tokens[curr];
    curr++;
    return token;
  }

  @Override
  public void close() throws IOException {
    // do nothing
  }
}
