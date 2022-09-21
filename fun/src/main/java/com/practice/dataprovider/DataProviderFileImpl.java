package com.practice.dataprovider;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public final class DataProviderFileImpl implements IDataProvider {
  private final Iterator<String> itr;
  private final BufferedReader br;

  public DataProviderFileImpl(final String filePath) throws FileNotFoundException {
    this.br = new BufferedReader(new FileReader(filePath));
    this.itr = this.br.lines().iterator();
  }
  @Override
  public boolean hasNext() {
   return itr.hasNext();
  }

  @Override
  public String next() {
    return itr.next();
  }

  @Override
  public void close() throws IOException {
    this.br.close();
  }
}
