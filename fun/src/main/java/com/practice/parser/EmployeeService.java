package com.practice.parser;

import com.practice.common.NullOrEmpty;
import com.practice.model.Director;
import com.practice.model.Employee;
import com.practice.model.Employee.Type;
import com.practice.store.IEmployeeStore;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeService {
private final IEmployeeStore store;

public EmployeeService(final IEmployeeStore store) {
  this.store = store;
}

public List<String> getEmployeesSummary(final List<Integer> employeeIds) {
  final List<String> summaries = new ArrayList<>();
  for(final int id : employeeIds) {
    final String employeeSummary = getEmployeeSummary(id);
    if(NullOrEmpty.isFalse(employeeSummary)) {
      summaries.add(employeeSummary);
    }
  }
  return summaries;
}

public String getEmployeeSummary(final int id) {
  final Employee employee = store.get(id);
  if(employee == null) {
    System.err.println("Employee not found: "+id);
   return null;
  }
  final StringBuilder sb  = new StringBuilder();
  sb.append("[\n");
  sb.append(getEmployeeDetailsWithoutSubordinates(employee));
  final String subordinateSummary = getSubordinatesSummary(employee);
  if(NullOrEmpty.isFalse(subordinateSummary)) {
    sb.append(subordinateSummary);
  }
  sb.append("]\n");
  return sb.toString();
}
private String getEmployeeDetailsWithoutSubordinates(final Employee employee) {
  final StringBuilder sb  = new StringBuilder();
  sb.append("Name: ").append(employee.getName()).append(",\n");
  sb.append("Type: ").append(employee.getType()).append(",\n");
  if(employee.getType() == Type.DIRECTOR) {
    sb.append("Department: ").append(((Director)employee).getDepartment()).append(",\n");
  }
  return sb.toString();
}

private String getSubordinatesSummary(final Employee employee) {
  if(NullOrEmpty.isTrue(employee.getSubordinates())) {
    return null;
  }

  final StringBuilder sb  = new StringBuilder();
  sb.append("Subordinates: [\n");
  for(final int subordinateId : employee.getSubordinates()) {
    final Employee subordinate = store.get(subordinateId);
    if(subordinate == null) {
      System.err.println("Subordinate not found: "+subordinateId);
      continue;
    }
    sb.append("[\n");
    sb.append(getEmployeeDetailsWithoutSubordinates(subordinate));
    sb.append("]\n");

  }
  sb.append("]\n");
  return sb.toString();
}
}
