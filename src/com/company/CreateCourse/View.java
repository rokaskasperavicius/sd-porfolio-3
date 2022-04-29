package com.company.CreateCourse;

import com.company.Controls.CustomButton;
import com.company.Models.Course;
import com.company.Main;
import com.company.Models.Model;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class View {
  final private GridPane root;
  Text errorText = new Text("");
  TextField courseName = new TextField();
  TextField courseCapacity = new TextField();
  TextArea coursesArea = new TextArea();

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

    root.add(new Text("Existing courses (capacity)"), 0, 1);

    coursesArea.setEditable(false);

    for (Course course : model.getCourses()) {
      appendCourse(course.getName() + " (" + course.getCapacity() + ")");
    }

    root.add(coursesArea, 0, 2);

    root.add(new Text("Enter a course name"), 0, 3);
    root.add(courseName, 0, 4);

    root.add(new Text("Enter a course capacity"), 0, 5);
    root.add(courseCapacity, 0, 6);

    errorText.setFill(Color.RED);
    root.add(errorText, 0, 7);

    Button submitButton = new CustomButton("Submit").button;
    submitButton.setOnAction(e -> controller.handleButtonClick(courseName.getText(), courseCapacity.getText()));

    root.add(submitButton, 0, 8);

    Button backButton = new CustomButton("<- Go back").button;
    backButton.setOnAction(e -> Main.changeStage("courseView"));

    root.add(backButton, 0, 9);

    Text header = new Text("Create a course");
    header.setStyle("-fx-font: 16 Arial; -fx-font-weight: bold;");

    root.add(header, 0, 0);
  }

  void setErrorText(String text) {
    errorText.setText(text);
  }

  void clearFields() {
    courseName.setText("");
    courseCapacity.setText("");
  }

  public void appendCourse(String course) {
    coursesArea.appendText(course + "\n");
  }
}
