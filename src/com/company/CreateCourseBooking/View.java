package com.company.CreateCourseBooking;

import com.company.Models.Room;
import com.company.Controls.CustomButton;
import com.company.Controls.CustomComboBox;
import com.company.Models.Course;
import com.company.Main;
import com.company.Models.CourseBooking;
import com.company.Models.Model;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class View {
  final private GridPane root;

  // https://docs.oracle.com/javafx/2/ui_controls/table-view.htm#CJADAHAH
  final private TableView<CourseBooking> table = new TableView<>();

  Text errorText = new Text("");
  Text warningText = new Text("");
  ComboBox<String> courseBox = new CustomComboBox().comboBox;
  ComboBox<String> lecturerBox = new CustomComboBox().comboBox;
  ComboBox<String> slotBox = new CustomComboBox().comboBox;
  ComboBox<String> roomBox = new CustomComboBox().comboBox;

  public GridPane getRoot() {
    return this.root;
  }

  public View() {
    Model model = new Model();
    Controller controller = new Controller(model, this);

    root = new GridPane();

    ColumnConstraints columnConstraints = new ColumnConstraints();
    columnConstraints.setPercentWidth(100);
    root.getColumnConstraints().addAll(columnConstraints);

    root.setVgap(10);
    root.setPadding(new Insets(20, 20, 20, 20));

    Text header = new Text("Create a course booking");
    header.setStyle("-fx-font: 16 Arial; -fx-font-weight: bold;");

    root.add(header, 0, 0);

    root.add(new Text("Existing course bookings"), 0, 1);

    TableColumn<CourseBooking, String> lecturerNameCol = new TableColumn<>("Lecturer Name");
    TableColumn<CourseBooking, String> courseNameCol = new TableColumn<>("Course Name");
    TableColumn<CourseBooking, Integer> courseCapacityCol = new TableColumn<>("Course Capacity");
    TableColumn<CourseBooking, String> slotNameCol = new TableColumn<>("Slot");
    TableColumn<CourseBooking, String> roomNameCol = new TableColumn<>("Room");
    TableColumn<CourseBooking, Integer> roomCapacityCol = new TableColumn<>("Room Capacity");

    lecturerNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseBooking, String>("lecturerName"));
    courseNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseBooking, String>("courseName"));
    courseCapacityCol.setCellValueFactory(
            new PropertyValueFactory<CourseBooking, Integer>("courseCapacity"));
    slotNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseBooking, String>("slotName"));
    roomNameCol.setCellValueFactory(
            new PropertyValueFactory<CourseBooking, String>("roomName"));
    roomCapacityCol.setCellValueFactory(
            new PropertyValueFactory<CourseBooking, Integer>("roomCapacity"));

    table.getColumns().add(courseNameCol);
    table.getColumns().add(courseCapacityCol);
    table.getColumns().add(slotNameCol);
    table.getColumns().add(roomNameCol);
    table.getColumns().add(roomCapacityCol);
    table.getColumns().add(lecturerNameCol);

    // https://stackoverflow.com/questions/28428280/how-to-set-column-width-in-tableview-in-javafx
    courseNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
    courseCapacityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    lecturerNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.3));
    slotNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    roomNameCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));
    roomCapacityCol.prefWidthProperty().bind(table.widthProperty().multiply(0.1));

    table.setItems(model.getCourseBookings());

    root.add(table, 0, 2);

    for (Course course: model.getCourses()) {
      courseBox.getItems().add(course.getName());
    }

    root.add(new Text("Select a course"), 0, 3);
    root.add(courseBox, 0, 4);

    for (String lecturer: model.getLecturers()) {
      lecturerBox.getItems().add(lecturer);
    }

    root.add(new Text("Select a lecturer"), 0, 5);
    root.add(lecturerBox, 0, 6);


    slotBox.getItems().addAll(model.getSlots());
    root.add(new Text("Select a slot"), 0, 7);
    root.add(slotBox, 0, 8);

    // REFACTOR TILL HERE
    for (Room room: model.getRooms()) {
      roomBox.getItems().add(room.getName());
    }

    root.add(new Text("Select a room"), 0, 9);
    root.add(roomBox, 0, 10);

    warningText.setFill(Color.ORANGE);

    root.add(warningText, 0, 11);

    errorText.setFill(Color.RED);

    root.add(errorText, 0, 12);

    Button submitButton = new CustomButton("Submit").button;
    submitButton.setOnAction(e -> controller.handleButtonClick(courseBox.getValue(), lecturerBox.getValue(), slotBox.getValue(), roomBox.getValue()));

    root.add(submitButton, 0, 13);

    Button backButton = new CustomButton("<- Go back").button;
    backButton.setOnAction(e -> Main.changeStage("courseView"));

    root.add(backButton, 0, 14);
  }

  void setTableData(CourseBooking data) {
    table.getItems().add(data);
  }

  // https://stackoverflow.com/questions/12142518/combobox-clearing-value-issue
  void clearFields() {
    courseBox.getSelectionModel().clearSelection();
    lecturerBox.getSelectionModel().clearSelection();
    slotBox.getSelectionModel().clearSelection();
    roomBox.getSelectionModel().clearSelection();
  }


  void setErrorText(String text) {
    errorText.setText(text);
  }
  void setWarningText(String text) {
    warningText.setText(text);
  }
}
