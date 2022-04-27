package com.company.Models;

public class CourseBooking {
  final private String lecturerName;
  final private String courseName;
  final private int courseCapacity;
  final private String roomName;
  final private int roomCapacity;
  final private String slotName;

  public CourseBooking(String lecturerName, String courseName, int courseCapacity, String roomName, int roomCapacity, String slotName) {
    this.lecturerName = lecturerName;
    this.courseName = courseName;
    this.courseCapacity = courseCapacity;
    this.roomName = roomName;
    this.roomCapacity = roomCapacity;
    this.slotName = slotName;
  }

  public String getLecturerName() {
    return lecturerName;
  }

  public String getCourseName() {
    return courseName;
  }
  public int getCourseCapacity() {
    return courseCapacity;
  }

  public String getRoomName() { return roomName; }
  public int getRoomCapacity() {
    return roomCapacity;
  }

  public String getSlotName() {
    return slotName;
  }
}
