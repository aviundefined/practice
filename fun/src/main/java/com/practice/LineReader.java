package com.practice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public final class LineReader implements ILineReader{
  private final Iterator<String> itr;
  private final BufferedReader br;

  public LineReader(final String filePath) throws FileNotFoundException {
    this.br = new BufferedReader(new FileReader(filePath));
    this.itr = this.br.lines().iterator();
  }
  @Override
  public final boolean hasNext() {
   return itr.hasNext();
  }

  @Override
  public final String next() {
    return itr.next();
  }

  @Override
  public void close() throws IOException {
    this.br.close();
  }
}
