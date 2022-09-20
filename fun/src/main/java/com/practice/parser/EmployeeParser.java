package com.practice.parser;

import com.practice.ILineReader;
import com.practice.common.NullOrEmpty;
import com.practice.model.BaseManager;
import com.practice.model.Director;
import com.practice.model.BaseEmployee;
import com.practice.model.BaseEmployee.Type;
import com.practice.store.IEmployeeStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeParser {
  private final ILineReader reader;
  private final IEmployeeStore store;
  public EmployeeParser(ILineReader reader, IEmployeeStore store) {
    this.reader = reader;
    this.store = store;
  }

  public final List<Integer> parse() throws IOException {
    final List<Integer> employees = new ArrayList<>();
    while (reader.hasNext()) {
      try {
        final int id = Integer.parseInt(reader.next());
        final String name = reader.next();
        final BaseEmployee.Type type = BaseEmployee.Type.valueOf(reader.next().toUpperCase());
        final BaseEmployee employee = EmployeeFactory.get().newEmployee(id, name, type);
        if(type == Type.DIRECTOR) {
          ((Director) employee).setDepartment(reader.next());
        }
        final List<Integer> subordinates = new ArrayList<>();
        while (reader.hasNext()) {
          final String str = reader.next();
          if(NullOrEmpty.isTrue(str)) {
            break;
          }
          subordinates.add(Integer.parseInt(str));
        }

        if(type == Type.DIRECTOR || type == Type.MANAGER) {
          ((BaseManager) employee).setSubordinates(subordinates);
        }
        employees.add(store.save(employee));
      } catch (Exception e) {
        System.err.println("Error in parsing record: " + e);
      }
    }
    reader.close();
    return employees;
  }
}
