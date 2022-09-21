package com.practice.dataprovider;

import static com.practice.TestUtils.PS;

import com.google.common.io.Files;
import com.practice.TestUtils;
import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataProviderArrayImplTest {

  private IDataProvider reader;
  @Before
  public void setup() throws IOException {
    this.reader = TestUtils.getDataProviderArrayImpl();
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
