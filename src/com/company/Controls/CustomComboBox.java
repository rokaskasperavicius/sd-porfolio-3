package com.company.Controls;

import javafx.scene.control.ComboBox;

public class CustomComboBox<T> extends ComboBox<T> {
  public ComboBox<String> comboBox;

  public CustomComboBox() {
    this.comboBox = new ComboBox<>();

    // Allow button to span multiple columns / rows
    comboBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
  }
}
