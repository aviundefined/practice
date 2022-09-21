package com.practice.parser;

import com.practice.common.NullOrEmpty;
import com.practice.model.BaseManager;
import com.practice.model.Director;
import com.practice.model.BaseEmployee;
import com.practice.model.BaseEmployee.Type;
import com.practice.store.IEmployeeStore;
import java.util.ArrayList;
import java.util.List;

public final class EmployeeService implements IEmployeeService {
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
  final BaseEmployee employee = store.get(id);
  if(employee == null) {
    System.err.println("Employee not found: "+id);
   return null;
  }
  final StringBuilder sb  = new StringBuilder();
  sb.append("{");
  sb.append(getEmployeeDetailsWithoutSubordinates(employee));
  if(employee.getType() == Type.DIRECTOR || employee.getType() == Type.MANAGER) {
    sb.append("\n");
    final String subordinateSummary = getSubordinatesSummary((BaseManager) employee);
    if(NullOrEmpty.isFalse(subordinateSummary)) {
      sb.append(subordinateSummary);
    }
  }
  sb.append("}\n");
  return sb.toString();
}
private String getEmployeeDetailsWithoutSubordinates(final BaseEmployee employee) {
  final StringBuilder sb  = new StringBuilder();
  sb.append("Id: ").append(employee.getId()).append(", ");
  sb.append("Name: ").append(employee.getName()).append(", ");
  sb.append("Type: ").append(employee.getType());
  if(employee.getType() == Type.DIRECTOR) {
    sb.append(", ").append("Department: ").append(((Director)employee).getDepartment());
  }
  return sb.toString();
}

private String getSubordinatesSummary(final BaseManager manager) {
  if(NullOrEmpty.isTrue(manager.getSubordinates())) {
    return null;
  }

  final StringBuilder sb  = new StringBuilder();
  sb.append("\tSubordinates: [\n");
  for(final int subordinateId : manager.getSubordinates()) {
    final BaseEmployee subordinate = store.get(subordinateId);
    if(subordinate == null) {
      System.err.println("Subordinate not found: "+subordinateId);
      continue;
    }
    sb.append("\t{ ");
    sb.append(getEmployeeDetailsWithoutSubordinates(subordinate));
    sb.append(" }\n");

  }
  sb.append("\t]\n");
  return sb.toString();
}
}
