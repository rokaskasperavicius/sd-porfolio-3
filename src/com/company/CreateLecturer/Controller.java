package com.company.CreateLecturer;

import com.company.Models.Model;

public class Controller {
  final private Model model;
  final private View view;

  public Controller(Model model, View view){
    this.model = model;
    this.view = view;
  }

  void handleButtonClick(String lecturerName) {
    // Clear error text
    view.setErrorText("");

    if (lecturerName.equals("")) {
      view.setErrorText("Error: Empty lecturer name");
      return;
    }

    try {
      model.insertLecturer(lecturerName);
      view.clearField();
      view.appendLecturer(lecturerName);
    } catch (Exception e) {
      System.out.println(e);
      view.setErrorText("Error: Lecturer already exists");
    }
  }
}
