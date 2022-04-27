package com.company.CreateCourse;

import com.company.Models.Model;

public class Controller {
  final private Model model;
  final private View view;

  public Controller(Model model, View view){
    this.model = model;
    this.view = view;
  }

  void handleButtonClick(String courseName, String courseCapacity) {
    // Clear error text
    view.setErrorText("");

    if (courseName.equals("")) {
      view.setErrorText("Error: Empty course name");
      return;
    }

    if (courseCapacity.equals("")) {
      view.setErrorText("Error: Empty capacity value");
      return;
    }

    int parsedCapacity;

    try {
      parsedCapacity = Integer.parseInt(courseCapacity);
    } catch (Exception e) {
      view.setErrorText("Error: Invalid capacity value");
      return;
    }

    try {
      model.insertCourse(courseName, parsedCapacity);
      view.clearFields();
      view.appendCourse(courseName + " (" + parsedCapacity + ")");
    } catch (Exception e) {
      view.setErrorText("Error: Course already exists");
    }
  }
}
