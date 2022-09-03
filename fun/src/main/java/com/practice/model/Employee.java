package com.practice.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Employee {

  public enum Type {
    DIRECTOR,
    MANAGER,
    CODER
  }
  protected final int id;
  protected final String name;
  protected final Type type;
  protected final List<Integer> subordinates = new ArrayList<>();
  public Employee(final int id, final String name, final Type type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public final String getName() {
    return name;
  }

  public final int getId() {
    return id;
  }

  public final Type getType() {
    return type;
  }

  public final void addSubordinate(final int employeeId) {
    this.subordinates.add(employeeId);
  }

  public final List<Integer> getSubordinates() {
    return new ArrayList<>(subordinates);
  }
}
