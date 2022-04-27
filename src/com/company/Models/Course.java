package com.company.Models;

public class Course {
  final private String name;
  final private int capacity;

  public Course(String name, int capacity) {
    this.name = name;
    this.capacity = capacity;
  }

  public String getName() {
    return this.name;
  }

  public int getCapacity() {
    return this.capacity;
  }
}
