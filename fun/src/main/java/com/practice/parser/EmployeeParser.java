package com.practice.parser;

import com.practice.IDataProvider;
import com.practice.common.NullOrEmpty;
import com.practice.model.BaseManager;
import com.practice.model.Director;
import com.practice.model.BaseEmployee;
import com.practice.model.BaseEmployee.Type;
import com.practice.model.Orgnization;
import com.practice.store.IEmployeeStore;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeParser implements IEmployeeParser {
  private final IDataProvider dataProvider;
  private final IEmployeeStore employeeStore;
  public EmployeeParser(IDataProvider dataProvider, IEmployeeStore employeeStore) {
    this.dataProvider = dataProvider;
    this.employeeStore = employeeStore;
  }

  public final Orgnization parse() throws IOException {
    final List<Integer> employees = new ArrayList<>();
    while (dataProvider.hasNext()) {
      try {
        final int id = Integer.parseInt(dataProvider.next());
        final String name = dataProvider.next();
        final BaseEmployee.Type type = BaseEmployee.Type.valueOf(dataProvider.next().toUpperCase());
        final BaseEmployee employee = EmployeeFactory.get().newEmployee(id, name, type);
        if(type == Type.DIRECTOR) {
          ((Director) employee).setDepartment(dataProvider.next());
        }
        final List<Integer> subordinates = new ArrayList<>();
        while (dataProvider.hasNext()) {
          final String str = dataProvider.next();
          if(NullOrEmpty.isTrue(str)) {
            break;
          }
          subordinates.add(Integer.parseInt(str));
        }

        if(type == Type.DIRECTOR || type == Type.MANAGER) {
          ((BaseManager) employee).setSubordinates(subordinates);
        }
        employees.add(employeeStore.save(employee));
      } catch (Exception e) {
        System.err.println("Error in parsing record: " + e);
      }
    }
    dataProvider.close();
    return new Orgnization(employees);
  }
}
