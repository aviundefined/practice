package com.practice.store;

import com.practice.model.Employee;

public interface IEmployeeStore {
  int save(final Employee employee);
  Employee get(final int id);
}
