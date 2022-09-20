package com.practice.store;

import com.practice.model.BaseEmployee;
import java.util.HashMap;
import java.util.Map;

public class EmployeeStore implements IEmployeeStore{

  EmployeeStore() {

  }
  private final Map<Integer, BaseEmployee> employeeMap = new HashMap<>();
  @Override
  public int save(BaseEmployee employee) {
    this.employeeMap.put(employee.getId(), employee);
    return employee.getId();
  }

  @Override
  public BaseEmployee get(int id) {
    return employeeMap.get(id);
  }

  @Override
  public void clear() {
    this.employeeMap.clear();
  }
}
