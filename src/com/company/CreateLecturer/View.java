package com.company.CreateLecturer;

import com.company.Controls.CustomButton;
import com.company.Main;
import com.company.Models.Model;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class View {
  final private GridPane root;
  Text errorText = new Text("");
  TextArea lecturersArea = new TextArea();
  TextField lecturerName = new TextField();

  public GridPane getRoot() {
    return this.root;
  }

  public View() {
    Model model = new Model();
    Controller controller = new Controller(model, this);

    root = new GridPane();

    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setPercentWidth(100);
    root.getColumnConstraints().add(columnConstraints);

    root.setVgap(10);
    root.setPadding(new Insets(20, 20, 20, 20));

    root.add(new Text("Existing lecturers"), 0, 1);

    lecturersArea.setEditable(false);

    for (String lecturer : model.getLecturers()) {
      appendLecturer(lecturer);
    }

    root.add(lecturersArea, 0, 2);

    root.add(new Text("Enter a lecturer name"), 0, 3);
    root.add(lecturerName, 0, 4);

    errorText.setFill(Color.RED);
    root.add(errorText, 0, 5);

    Button submitButton = new CustomButton("Submit").button;
    submitButton.setOnAction(e -> controller.handleButtonClick(lecturerName.getText()));

    root.add(submitButton, 0, 6);

    Button backButton = new CustomButton("<- Go back").button;
    backButton.setOnAction(e -> Main.changeStage("courseView"));

    root.add(backButton, 0, 7);

    Text header = new Text("Create a lecturer");
    header.setStyle("-fx-font: 16 Arial; -fx-font-weight: bold;");

    root.add(header, 0, 0);
  }

  void setErrorText(String text) {
    errorText.setText(text);
  }

  void clearField() {
    lecturerName.setText("");
  }

  public void appendLecturer(String lecturer) {
    lecturersArea.appendText(lecturer + "\n");
  }
}
