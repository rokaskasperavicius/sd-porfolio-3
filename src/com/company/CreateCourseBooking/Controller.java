package com.company.CreateCourseBooking;

import com.company.Models.CourseBooking;
import com.company.Models.Model;

public class Controller {
  final private Model model;
  final private View view;

  public Controller(Model model, View view){
    this.model = model;
    this.view = view;
  }

  void handleButtonClick(String courseName, String lecturerName, String slotName, String roomName) {
    // Clear error text
    view.setErrorText("");

    if (courseName == null) {
      view.setErrorText("Error: Please select a course");
      return;
    }

    if (lecturerName == null) {
      view.setErrorText("Error: Please select a lecturer");
      return;
    }

    if (slotName == null) {
      view.setErrorText("Error: Please select a slot");
      return;
    }

    if (roomName == null) {
      view.setErrorText("Error: Please select a room");
      return;
    }

    Integer courseCapacity = model.getCourseCapacity(courseName);
    Integer roomCapacity = model.getRoomCapacity(roomName);

    if (courseCapacity > roomCapacity) {
      view.setWarningText("Warning: the course (" + courseCapacity + ") has more people than there is space in the room (" + roomCapacity + ")");
    } else {
      view.setWarningText("");
    }

    try {
      model.insertCourseBooking(courseName, lecturerName, slotName, roomName);
      view.clearFields();
      view.setTableData(new CourseBooking(lecturerName, courseName, courseCapacity, roomName, roomCapacity, slotName));
    } catch (RuntimeException e) {
      view.setErrorText(e.getMessage());
    }
  }
}
