package com.practice;


import static com.practice.TestUtils.PS;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LineReaderTest {
  private IDataProvider reader;
  @Before
  public void setup() throws IOException {
    final File tmpDir = Files.createTempDir();
    final String filePath = tmpDir.getAbsolutePath() + PS + UUID.randomUUID();
    this.reader = TestUtils.getLineReader(filePath);
  }

  @After
  public void tearDown() throws IOException {
    reader.close();
  }

  @Test
  public void print() {
    while (reader.hasNext()) {
      System.out.println(reader.next());
    }
  }
}