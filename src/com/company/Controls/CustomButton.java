package com.company.Controls;

import javafx.scene.control.Button;

public class CustomButton {
  public Button button;

  public CustomButton(String text) {
    this.button = new Button(text);

    // Allow button to span multiple columns / rows
    button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

    // Show correct cursors on all buttons
    button.setStyle("-fx-cursor: hand");
  }
}
