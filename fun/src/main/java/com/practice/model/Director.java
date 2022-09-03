package com.practice.model;

public final class Director extends Employee {

  private String department;
  public Director(int id, String name) {
    super(id, name, Type.DIRECTOR);
  }

  public final String getDepartment() {
    return department;
  }

  public final void setDepartment(String department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Director{" +
        "department='" + department + '\'' +
        ", name='" + name + '\'' +
        ", subordinates=" + subordinates +
        '}';
  }
}
