package com.practice.model;

public abstract class BaseEmployee {

  public enum Type {
    DIRECTOR,
    MANAGER,
    CODER
  }
  protected final int id;
  protected final String name;
  protected final Type type;
  public BaseEmployee(final int id, final String name, final Type type) {
    this.id = id;
    this.name = name;
    this.type = type;
  }

  public final String getName() {
    return name;
  }

  public final int getId() {
    return id;
  }

  public final Type getType() {
    return type;
  }
}
