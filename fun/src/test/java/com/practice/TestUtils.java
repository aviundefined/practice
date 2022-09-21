package com.practice;

import com.practice.dataprovider.DataProviderFileImpl;
import com.practice.dataprovider.IDataProvider;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestUtils {
  public static final String PS = File.pathSeparator;
  public static IDataProvider getLineReader(final String filePath) throws IOException {
    try (final BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
      bw.write("5");
      bw.newLine();
      bw.write("Avinash");
      bw.newLine();
      bw.write("Coder");
      bw.newLine();
      bw.newLine();

      bw.write("4");
      bw.newLine();
      bw.write("Ashwin");
      bw.newLine();
      bw.write("Coder");
      bw.newLine();
      bw.newLine();

      bw.write("3");
      bw.newLine();
      bw.write("Vishal");
      bw.newLine();
      bw.write("Coder");
      bw.newLine();
      bw.newLine();

      bw.write("2");
      bw.newLine();
      bw.write("Abhishek");
      bw.newLine();
      bw.write("Manager");
      bw.newLine();
      bw.write("3");
      bw.newLine();
      bw.write("4");
      bw.newLine();
      bw.write("5");
      bw.newLine();
      bw.newLine();

      bw.write("1");
      bw.newLine();
      bw.write("Kamasakshi");
      bw.newLine();
      bw.write("Director");
      bw.newLine();
      bw.write("Engineering");
      bw.newLine();
      bw.write("2");
    }
    return new DataProviderFileImpl(filePath);
  }
}
