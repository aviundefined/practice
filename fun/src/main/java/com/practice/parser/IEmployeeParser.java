package com.practice.parser;

import com.practice.model.Orgnization;
import java.io.IOException;

public interface IEmployeeParser {
  Orgnization parse() throws IOException;
}
