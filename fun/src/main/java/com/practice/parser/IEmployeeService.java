package com.practice.parser;

import java.util.List;

public interface IEmployeeService {
   List<String> getEmployeesSummary(final List<Integer> employeeIds);
   String getEmployeeSummary(final int employeeId);
}
