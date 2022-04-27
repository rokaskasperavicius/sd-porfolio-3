package com.company.CreateRoom;

import com.company.Models.Model;

public class Controller {
  final private Model model;
  final private View view;

  public Controller(Model model, View view){
    this.model = model;
    this.view = view;
  }

  void handleButtonClick(String roomName, String roomCapacity) {
    // Clear error text
    view.setErrorText("");

    if (roomName.equals("")) {
      view.setErrorText("Error: Empty room name");
      return;
    }

    if (roomCapacity.equals("")) {
      view.setErrorText("Error: Empty room capacity");
      return;
    }

    int parsedCapacity;

    try {
      parsedCapacity = Integer.parseInt(roomCapacity);
    } catch (Exception e) {
      view.setErrorText("Error: Invalid capacity value");
      return;
    }

    try {
      model.insertRoom(roomName, parsedCapacity);
      view.clearFields();
      view.appendRoom(roomName + " (" + parsedCapacity + ")");
    } catch (Exception e) {
      view.setErrorText("Error: Room already exists");
    }
  }
}
