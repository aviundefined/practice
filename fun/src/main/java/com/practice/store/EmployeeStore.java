package com.practice.store;

import com.practice.model.Employee;
import java.util.HashMap;
import java.util.Map;

public class EmployeeStore implements IEmployeeStore{

  EmployeeStore() {

  }
  private final Map<Integer, Employee> employeeMap = new HashMap<>();
  @Override
  public int save(Employee employee) {
    this.employeeMap.put(employee.getId(), employee);
    return employee.getId();
  }

  @Override
  public Employee get(int id) {
    return employeeMap.get(id);
  }

  @Override
  public void clear() {
    this.employeeMap.clear();
  }
}
