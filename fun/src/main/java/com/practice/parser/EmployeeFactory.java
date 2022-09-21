package com.practice.parser;

import com.practice.model.BaseEmployee;
import com.practice.model.BaseEmployee.Type;
import com.practice.model.Coder;
import com.practice.model.Director;
import com.practice.model.Manager;

public class EmployeeFactory {

  private static volatile EmployeeFactory instance;

  private EmployeeFactory() {
    // Important: Private constructor for singleton pattern.
  }
  public static EmployeeFactory get() {
    if(instance == null) {
      synchronized (EmployeeFactory.class) {
        if (instance == null) {
          instance  = new EmployeeFactory();
        }
      }
    }
    return instance;
  }

  public final BaseEmployee newEmployee(final int id, final String name, final Type type) {
    return switch (type) {
      case DIRECTOR -> new Director(id, name);
      case MANAGER -> new Manager(id, name);
      case CODER -> new Coder(id, name);
    };
  }
}
