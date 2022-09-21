package com.practice.parser;


import static com.practice.TestUtils.PS;

import com.google.common.collect.Lists;
import com.google.common.io.Files;
import com.practice.TestUtils;
import com.practice.store.IEmployeeStore;
import com.practice.store.StoreFactory;
import com.practice.store.StoreFactory.StoreType;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class EmployeeServiceTest {

  private IEmployeeService service;
  private IEmployeeStore store;
  private IEmployeeParser employeeParser;
  @Before
  public void setUp() throws IOException {
    store = StoreFactory.get().getEmployeeStore(StoreType.IN_MEMORY );
    final String filePath = Files.createTempDir() + PS + UUID.randomUUID();
    employeeParser = new EmployeeParser(TestUtils.getLineReader(filePath), store);
    employeeParser.parse();
    service = new EmployeeService(store);
  }

  @After
  public void tearDown() {
    StoreFactory.get().clear();
    service = null;
    store = null;
    employeeParser = null;
  }

  @Test
  public void testAll() {
    final List<Integer> employeeIds = Lists.newArrayList(1, 2, 3, 4, 5);
    final List<String> employeesSummary = service.getEmployeesSummary(employeeIds);
    Assert.assertEquals(employeeIds.size(), employeesSummary.size());
    System.out.println("employeesSummary: "+employeesSummary);
  }

  @Test
  public void testOneByOne() {
    final List<Integer> employeeIds = Lists.newArrayList(1, 2, 3, 4, 5);
    int count = 0;
    for(count = 0; count < employeeIds.size(); count++) {
      final String summary = service.getEmployeeSummary(employeeIds.get(count));
      Assert.assertNotNull(summary);
    }
    Assert.assertEquals(count, employeeIds.size());
  }
}