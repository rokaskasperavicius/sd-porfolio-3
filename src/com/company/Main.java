package com.company;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

// Better error handling
// SQL case insensitive string check
// With splitting class in two different places I cant check if everybody is gonna fit

public class Main extends Application {
  // Stage has to be static for me to be able to change stages from other classes
  // I couldn't find a more simple solution
  private static Stage stage;

  public static void changeStage(String view) {
    com.company.CoursesView.View courseView = new com.company.CoursesView.View();
    com.company.CreateLecturer.View addLecturerView = new com.company.CreateLecturer.View();
    com.company.CreateCourse.View addCourseView = new com.company.CreateCourse.View();
    com.company.CreateRoom.View addRoomView = new com.company.CreateRoom.View();
    com.company.CreateCourseBooking.View addTimeView = new com.company.CreateCourseBooking.View();

    GridPane root;
    String title;

    switch (view) {
      case "createLecturer":
        root = addLecturerView.getRoot();
        title = "Create a Lecturer";
        break;
      case "createCourse":
        root = addCourseView.getRoot();
        title = "Create a Course";
        break;
      case "createRoom":
        root = addRoomView.getRoot();
        title = "Create a Room";
        break;
      case "createCourseBooking":
        root = addTimeView.getRoot();
        title = "Create a Course Booking";
        break;
      default:
        root = courseView.getRoot();
        title = "Home";
        break;
    }

    Scene scene = new Scene(root, 1100, 800, Color.WHITE); // width, height

    stage.setTitle("Roskilde University System | " + title);

    stage.setScene(scene);
    stage.show();
  }

  @Override
  public void start(Stage stage) {
    Main.stage = stage;

    changeStage("coursesView");
  }

  public static void main(String[] args) {
    launch(args);
  }
}
