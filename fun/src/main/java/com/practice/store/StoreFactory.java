package com.practice.store;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class StoreFactory {

  public enum StoreType {
    IN_MEMORY,
    DB
  }
  private static volatile StoreFactory instance;

  private final ConcurrentMap<StoreType, IEmployeeStore> employeeStores = new ConcurrentHashMap<>();
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

  public final IEmployeeStore getEmployeeStore(StoreType storeType) {
    return this.employeeStores.computeIfAbsent(storeType, k -> new EmployeeStore());
  }

  public final void clear() {
    for(final IEmployeeStore es : employeeStores.values()) {
      es.clear();
    }
  }
}
