package com.practice.store;

import com.practice.model.BaseEmployee;

public interface IEmployeeStore {
  int save(final BaseEmployee employee);
  BaseEmployee get(final int id);
  void clear();
}
