package com.practice.model;

public final class Manager extends Employee{
  public Manager(int id, String name) {
    super(id, name, Type.MANAGER);
  }

  @Override
  public String toString() {
    return "Manager{" +
        "name='" + name + '\'' +
        ", subordinates=" + subordinates +
        '}';
  }
}
