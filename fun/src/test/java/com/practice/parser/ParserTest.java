package com.practice.parser;


import static com.practice.TestUtils.PS;

import com.google.common.io.Files;
import com.practice.TestUtils;
import com.practice.model.Employee;
import com.practice.store.EmployeeStore;
import com.practice.store.IEmployeeStore;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest  {

  private Parser parser;
  private IEmployeeStore store;
  private EmployeeService employeeService;

  @Before
  public void setup() throws IOException {
    final File tmpDir = Files.createTempDir();
    final String filePath = tmpDir.getAbsolutePath() + PS + UUID.randomUUID();
    store = new EmployeeStore();
    parser = new Parser(TestUtils.getLineReader(filePath), store);
    employeeService = new EmployeeService(store);

  }

  @After
  public void tearDown() {
    store = null;
    parser = null;
    employeeService = null;
  }

  @Test
  public void testParse() throws IOException {
    final List<Integer> employees = parser.parse();
    System.out.println("employees: "+employees);
    final List<String> employeesSummary = employeeService.getEmployeesSummary(employees);
    System.out.println("employeesSummary: "+employeesSummary);
    Assert.assertEquals(employees.size(), employeesSummary.size());
  }
}