package com.practice.model;

import java.util.List;

public class Orgnization {

  private final List<Integer> employeeIds;

  public Orgnization(List<Integer> employeeIds) {
    this.employeeIds = employeeIds;
  }

  public final List<Integer> getEmployeeIds() {
    return employeeIds;
  }
}
