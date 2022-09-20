package com.practice.parser;


import static com.practice.TestUtils.PS;

import com.google.common.io.Files;
import com.practice.TestUtils;
import com.practice.store.IEmployeeStore;
import com.practice.store.StoreFactory;
import com.practice.store.StoreFactory.StoreType;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeParserTest {

  private EmployeeParser employeeParser;
  private IEmployeeStore store;
  private EmployeeService employeeService;

  @Before
  public void setup() throws IOException {
    final File tmpDir = Files.createTempDir();
    final String filePath = tmpDir.getAbsolutePath() + PS + UUID.randomUUID();
    store = StoreFactory.get().getEmployeeStore(StoreType.IN_MEMORY);
    employeeParser = new EmployeeParser(TestUtils.getLineReader(filePath), store);
    employeeService = new EmployeeService(store);

  }

  @After
  public void tearDown() {
    StoreFactory.get().clear();
    store = null;
    employeeParser = null;
    employeeService = null;
  }

  @Test
  public void testParse() throws IOException {
    final List<Integer> employees = employeeParser.parse();
    System.out.println("employees: "+employees);
    final List<String> employeesSummary = employeeService.getEmployeesSummary(employees);
    System.out.println("employeesSummary: "+employeesSummary);
    Assert.assertEquals(employees.size(), employeesSummary.size());
  }
}