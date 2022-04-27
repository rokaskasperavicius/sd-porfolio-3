package com.company.Models;

public class CourseInfo {
  final private String lecturerName;
  final private String slotName;
  final private String roomName;
  final private int roomCapacity;

  public CourseInfo(String lecturerName, String slotName, String roomName, int roomCapacity) {
    this.lecturerName = lecturerName;
    this.slotName = slotName;
    this.roomName = roomName;
    this.roomCapacity = roomCapacity;
  }

  public String getLecturerName() {
    return this.lecturerName;
  }

  public String getSlotName() {
    return this.slotName;
  }

  public String getRoomName() {
    return this.roomName;
  }

  public int getRoomCapacity() {
    return this.roomCapacity;
  }
}
