package com.practice.model;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseManager extends BaseEmployee {

  protected final List<Integer> subordinates = new ArrayList<>();
  public BaseManager(int id, String name, Type type) {
    super(id, name, type);
  }

  public final void setSubordinates(final List<Integer> subordinates) {
    this.subordinates.clear();
    this.subordinates.addAll(subordinates);
  }

  public List<Integer> getSubordinates() {
    return subordinates;
  }
}
