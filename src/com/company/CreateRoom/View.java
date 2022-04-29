package com.company.CreateRoom;

import com.company.Controls.CustomButton;
import com.company.Main;
import com.company.Models.Model;
import com.company.Models.Room;
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
  TextField roomName = new TextField();
  TextField roomCapacity = new TextField();
  TextArea roomsArea = new TextArea();

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

    root.add(new Text("Existing rooms (capacity)"), 0, 1);

    roomsArea.setEditable(false);

    for (Room room: model.getRooms()) {
      appendRoom(room.getName() + " (" + room.getCapacity() + ")");
    }

    root.add(roomsArea, 0, 2);

    root.add(new Text("Enter room name"), 0, 3);
    root.add(roomName, 0, 4);

    root.add(new Text("Enter room capacity"), 0, 5);
    root.add(roomCapacity, 0, 6);

    errorText.setFill(Color.RED);
    root.add(errorText, 0, 7);

    Button submitButton = new CustomButton("Submit").button;
    submitButton.setOnAction(e -> controller.handleButtonClick(roomName.getText(), roomCapacity.getText()));

    root.add(submitButton, 0, 8);

    Button backButton = new CustomButton("<- Go back").button;
    backButton.setOnAction(e -> Main.changeStage("courseView"));

    root.add(backButton, 0, 9);

    Text header = new Text("Create a room");
    header.setStyle("-fx-font: 16 Arial; -fx-font-weight: bold;");

    root.add(header, 0, 0);
  }

  void setErrorText(String text) {
    errorText.setText(text);
  }

  void clearFields() {
    roomName.setText("");
    roomCapacity.setText("");
  }

  public void appendRoom(String room) {
    roomsArea.appendText(room + "\n");
  }
}
