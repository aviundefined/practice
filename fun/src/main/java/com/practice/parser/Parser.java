package com.practice.parser;

import com.practice.ILineReader;
import com.practice.common.NullOrEmpty;
import com.practice.model.Director;
import com.practice.model.Employee;
import com.practice.model.Employee.Type;
import com.practice.store.IEmployeeStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {
  private final ILineReader reader;
  private final IEmployeeStore store;
  public Parser(ILineReader reader, IEmployeeStore store) {
    this.reader = reader;
    this.store = store;
  }

  public final List<Integer> parse() throws IOException {
    final List<Integer> employees = new ArrayList<>();
    int i = 0;
    while (reader.hasNext()) {
      try {
        final int id = Integer.parseInt(reader.next());
        final String name = reader.next();
        final Employee.Type type = Employee.Type.valueOf(reader.next().toUpperCase());
        final Employee employee = EmployeeFactory.get().newEmployee(id, name, type);
        if(type == Type.DIRECTOR) {
          ((Director) employee).setDepartment(reader.next());
        }
        while (reader.hasNext()) {
          final String str = reader.next();
          if(NullOrEmpty.isTrue(str)) {
            break;
          }
          employee.addSubordinate(Integer.parseInt(str));
        }
        employees.add(store.save(employee));
      }catch (Exception e) {
        System.err.println("Error in parsing record: " + e);
      }
    }
    reader.close();
    return employees;
  }
}
