package com.practice.store;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StoreFactory {
  private static final String EMPLOYEE_STORE_KEY = "EMPLOYEE_STORE_KEY";
  private static volatile StoreFactory instance;
  private final ConcurrentMap<String, IEmployeeStore> employeeStores = new ConcurrentHashMap<>();
  private StoreFactory() {

  }
  public static StoreFactory get() {
    if(instance == null) {
      synchronized (StoreFactory.class) {
        if(instance == null) {
          instance = new StoreFactory();
        }
      }
    }
    return instance;
  }

  public final IEmployeeStore getEmployeeStore() {
    return this.employeeStores.computeIfAbsent(EMPLOYEE_STORE_KEY, k -> new EmployeeStore());
  }

  public final void clear() {
    for(final IEmployeeStore es : employeeStores.values()) {
      es.clear();
    }
  }
}
