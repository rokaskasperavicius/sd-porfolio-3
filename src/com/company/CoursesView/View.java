package com.company.CoursesView;

import com.company.Controls.CustomButton;
import com.company.Controls.CustomComboBox;
import com.company.Main;
import com.company.Models.Course;
import com.company.Models.CourseInfo;
import com.company.Models.Model;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class View {
  final private GridPane root;

  // https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJADAHAH
  final private TableView<CourseInfo> table = new TableView<>();

  Text errorText = new Text("");

  public GridPane getRoot() {
    return this.root;
  }

  public View() {
    Model model = new Model();
    Controller controller = new Controller(model, this);

    root = new GridPane();

    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setPercentWidth(25);
    root.getColumnConstraints().addAll(columnConstraints, columnConstraints, columnConstraints, columnConstraints);

    root.setVgap(10);
    root.setHgap(10);
    root.setPadding(new Insets(20, 20, 20, 20));


    ComboBox<String> courses = new CustomComboBox<>().comboBox;

    for (Course course: model.getCourses()) {
      courses.getItems().add(course.getName());
    }

    Button checkButton = new CustomButton("Show information").button;
    checkButton.setOnAction(e -> controller.handleButtonClick(courses.getValue()));

    root.add(new Text("Select a course"), 0, 1, 4, 1);
    root.add(courses, 0, 2, 4, 1);

    root.add(checkButton, 0, 3, 4, 1);

    Text header = new Text("Check existing courses");
    header.setStyle("-fx-font: 16 Arial; -fx-font-weight: bold;");

    root.add(header, 0, 0, 4, 1);

    TableColumn<CourseInfo, String> lecturerNameCol = new TableColumn<>("Lecturer Name");
    TableColumn<CourseInfo, String> slotNameCol = new TableColumn<>("Slot");
    TableColumn<CourseInfo, String> roomNameCol = new TableColumn<>("Room");
    TableColumn<CourseInfo, Integer> roomCapacityCol = new TableColumn<>("Room Capacity");

    lecturerNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseInfo, String>("lecturerName"));
    slotNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseInfo, String>("slotName"));
    roomNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseInfo, String>("roomName"));
    roomCapacityCol.setCellValueFactory(
            new PropertyValueFactory<CourseInfo, Integer>("roomCapacity"));

    table.getColumns().add(slotNameCol);
    table.getColumns().add(roomNameCol);
    table.getColumns().add(roomCapacityCol);
    table.getColumns().add(lecturerNameCol);

    // https://stackoverflow.com/questions/28428280/how-to-set-column-width-in-tableview-in-javafx
    lecturerNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.4));
    slotNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
    roomNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));
    roomCapacityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.2));

    errorText.setFill(Color.RED);

    root.add(errorText, 0, 4, 4, 1);

    root.add(table, 0, 5, 4, 1);

    Button createCourse = new CustomButton("Create a course").button;
    createCourse.setOnAction(e -> Main.changeStage("createCourse"));
    root.add(createCourse, 0, 6);

    Button createLecturer = new CustomButton("Create a lecturer").button;
    createLecturer.setOnAction(e -> Main.changeStage("createLecturer"));
    root.add(createLecturer, 1, 6);

    Button createRoom = new CustomButton("Create a room").button;
    createRoom.setOnAction(e -> Main.changeStage("createRoom"));
    root.add(createRoom, 2, 6);

    Button createCourseBooking = new CustomButton("Create a course booking").button;
    createCourseBooking.setOnAction(e -> Main.changeStage("createCourseBooking"));
    root.add(createCourseBooking, 3, 6);
  }

  void setTableData(ObservableList<CourseInfo> data) {
    table.setItems(data);
  }

  void setErrorText(String text) {
    errorText.setText(text);
  }
}
