package com.company.CoursesView;

import com.company.Models.CourseInfo;
import com.company.Models.Model;
import javafx.collections.ObservableList;

public class Controller {
  final private Model model;
  final private View view;

  public Controller(Model model, View view){
    this.model = model;
    this.view = view;
  }

  void handleButtonClick(String course) {
    // Clear error text
    view.setErrorText("");

    if (course == null) {
      view.setErrorText("Error: Please select a course");
    } else {
      ObservableList<CourseInfo> courseInfo = model.getCourseInfo(course);

      view.setTableData(courseInfo);
    }
  }
}
