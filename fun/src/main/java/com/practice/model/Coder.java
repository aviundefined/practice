package com.practice.model;

public final class Coder extends Employee{

  public Coder(int id, String name) {
    super(id, name, Type.CODER);
  }

  @Override
  public String toString() {
    return "Coder{" +
        "name='" + name + '\'' +
        ", subordinates=" + subordinates +
        '}';
  }
}
