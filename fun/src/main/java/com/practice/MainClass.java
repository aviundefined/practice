package com.practice;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainClass {

  public static void main(String[] args) throws IOException {
    final String path = "/Users/avi/Downloads/Phone_number.txt";
    final String outfilePath = "/Users/avi/Downloads/Phone_number_out.txt";
    final File outfile = new File(outfilePath);
    if(outfile.exists()) {
      outfile.delete();
    }
    final Set<String> set = new HashSet<>();
    try (final BufferedWriter bw = new BufferedWriter(new FileWriter(outfile))) {
      final List<String> lines = Files.readAllLines(Path.of(path));
      for(final String line : lines) {
        if( line == null || line.isEmpty() || line.trim().equals("")) {
          continue;
        }
        final String[] phones = line.split(" ");
        final String phone = phones[0].trim();
        if(phone.length() != 10) {
          continue;
        }
        if(set.add(phone)) {
          bw.write(phone);
          bw.write('\n');
        }
      }
    }

  }
}
