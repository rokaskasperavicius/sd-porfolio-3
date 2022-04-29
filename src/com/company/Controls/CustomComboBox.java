package com.company.Controls;

import javafx.scene.control.ComboBox;

// All combo boxes I use are Strings, therefore, I did not make it a generic class
public class CustomComboBox {
  public ComboBox<String> comboBox;

  public CustomComboBox() {
    this.comboBox = new ComboBox<>();

    // Allow button to span multiple columns / rows
    comboBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
  }
}
