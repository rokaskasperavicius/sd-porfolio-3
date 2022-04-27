package com.company.Models;

import com.company.SQL;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Model {
  private final SQL sql = new SQL();

  public void insertCourse(String courseName, int courseCapacity) {
    String query = "INSERT INTO courses(name, capacity) VALUES(?, ?);";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, courseName);
      stmt.setInt(2, courseCapacity);

      sql.cmd(stmt);
    } catch (Exception e) {
      throw new RuntimeException();
    } finally {
      sql.close(conn);
    }
  }

  public void insertLecturer(String lecturerName) {
    String query = "INSERT INTO lecturers(name) VALUES(?);";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, lecturerName);

      sql.cmd(stmt);
    } catch (Exception e) {
      throw new RuntimeException();
    } finally {
      sql.close(conn);
    }
  }

  public ArrayList<String> getLecturers() {
    ArrayList<String> lecturers = new ArrayList<>();
    String query = "SELECT name FROM lecturers ORDER BY name ASC";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);

      ResultSet result = sql.query(stmt);

      while (result.next()) {
        String name = result.getString("name");

        lecturers.add(name);
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getLecturers: " + e);
    } finally {
      sql.close(conn);
    }

    return lecturers;
  }

  public ArrayList<Course> getCourses() {
    ArrayList<Course> courses = new ArrayList<>();
    String query = "SELECT name, capacity FROM courses ORDER BY name ASC;";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);

      ResultSet result = sql.query(stmt);

      while (result.next()) {
        String name = result.getString("name");
        int capacity = result.getInt("capacity");

        courses.add(new Course(name, capacity));
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getCourses: " + e);
    } finally {
      sql.close(conn);
    }

    return courses;
  }

  public ArrayList<String> getSlots() {
    ArrayList<String> slots = new ArrayList<>();
    String query = "SELECT name FROM slots ORDER BY name ASC;";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);

      ResultSet result = sql.query(stmt);

      while (result.next()) {
        String name = result.getString("name");

        slots.add(name);
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getSlots: " + e);
    } finally {
      sql.close(conn);
    }

    return slots;
  }

  public void insertRoom(String roomName, int roomCapacity) {
    String query = "INSERT INTO rooms(name, capacity) VALUES(?, ?);";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, roomName);
      stmt.setInt(2, roomCapacity);

      sql.cmd(stmt);
    } catch (Exception e) {
      throw new RuntimeException();
    } finally {
      sql.close(conn);
    }
  }

  public ArrayList<Room> getRooms() {
    ArrayList<Room> rooms = new ArrayList<>();
    String query = "SELECT name, capacity FROM rooms ORDER BY name ASC";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);

      ResultSet result = sql.query(stmt);

      while (result.next()) {
        String name = result.getString("name");
        int capacity = result.getInt("capacity");

        rooms.add(new Room(name, capacity));
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getRooms: " + e);
    } finally {
      sql.close(conn);
    }

    return rooms;
  }

  public ObservableList<CourseInfo> getCourseInfo(String searchCourseName) {
    ObservableList<CourseInfo> course = FXCollections.observableArrayList();

    String query =
      "SELECT RB.slot_name as slotName, RB.room_name as roomName, L.name as lecturerName, R.capacity as roomCapacity FROM " +
        "room_bookings RB " +
        "JOIN assigned_lecturers AL ON RB.slot_name = AL.slot_name AND RB.room_name = AL.room_name " +
        "JOIN lecturers L ON L.name = AL.lecturer_name " +
        "JOIN rooms R ON R.name = RB.room_name " +
        "WHERE RB.course_name = ? " +
        "ORDER BY RB.slot_name ASC;";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, searchCourseName);

      ResultSet result = sql.query(stmt);

      while (result.next()) {
        String lecturerName = result.getString("lecturerName");
        String slotName = result.getString("slotName");
        String roomName = result.getString("roomName");
        int roomCapacity = result.getInt("roomCapacity");

        course.add(new CourseInfo(lecturerName, slotName, roomName, roomCapacity));
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getCourseInfo: " + e);
    } finally {
      sql.close(conn);
    }

    return course;
  }

  public Integer getCourseCapacity(String courseName) {
    Integer capacity = null;
    String query = "SELECT capacity FROM courses WHERE name = ?;";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, courseName);

      ResultSet result = sql.query(stmt);

      if (result.next()) {
        capacity = result.getInt("capacity");
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getCourseCapacity: " + e);
    } finally {
      sql.close(conn);
    }

    return capacity;
  }

  public Integer getRoomCapacity(String roomName) {
    Integer capacity = null;
    String query = "SELECT capacity FROM rooms WHERE name = ?;";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);
      stmt.setString(1, roomName);

      ResultSet result = sql.query(stmt);
      if (result.next()) {
        capacity = result.getInt("capacity");
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getRoomCapacity: " + e);
    } finally {
      sql.close(conn);
    }

    return capacity;
  }

  public ObservableList<CourseBooking> getCourseBookings() {
    ObservableList<CourseBooking> course = FXCollections.observableArrayList();

    String query =
      "SELECT RB.course_name as courseName, RB.slot_name as slotName, RB.room_name as roomName, L.name as lecturerName, R.capacity as roomCapacity, C.capacity as courseCapacity " +
        "FROM room_bookings RB " +
        "JOIN assigned_lecturers AL ON RB.slot_name = AL.slot_name AND RB.room_name = AL.room_name " +
        "JOIN lecturers L ON L.name = AL.lecturer_name " +
        "JOIN rooms R ON R.name = RB.room_name " +
        "JOIN courses C ON C.name = RB.course_name " +
        "ORDER BY RB.course_name, RB.slot_name, RB.room_name, L.name ASC;";

    Connection conn = sql.open();

    try {
      PreparedStatement stmt = conn.prepareStatement(query);

      ResultSet result = sql.query(stmt);

      while (result.next()) {
        String lecturerName = result.getString("lecturerName");
        String courseName = result.getString("courseName");
        int courseCapacity = result.getInt("courseCapacity");
        String roomName = result.getString("roomName");
        int roomCapacity = result.getInt("roomCapacity");
        String slotName = result.getString("slotName");

        course.add(new CourseBooking(lecturerName, courseName, courseCapacity, roomName, roomCapacity, slotName));
      }
    } catch (Exception e) {
      System.out.println("Error while parsing getTimeInfo: " + e);
    } finally {
      sql.close(conn);
    }

    return course;
  }

  public void insertCourseBooking(String courseName, String lecturerName, String slotName, String roomName) {
    String assignLecturerQuery = "INSERT INTO assigned_lecturers(lecturer_name, slot_name, room_name) VALUES(?, ?, ?);";

    // https://www.codeproject.com/Questions/162627/how-to-insert-new-record-in-my-table-if-not-exists
    // Insert a new course room schedule only if there isn't one already
    // I could check only for time and room match, but I check for time, room and name because if somebody tries to create a different course at the same time and spot,
    // sqlite will try to insert it but will throw unique constraint error. This way I will be able to catch this error and display a meaningful error text for the user
    String bookRoomQuery =
      "INSERT INTO room_bookings(course_name, slot_name, room_name) " +
        "SELECT ?, ?, ? WHERE NOT EXISTS (SELECT * FROM room_bookings WHERE course_name = ? AND slot_name = ? AND room_name = ?);";

    Connection conn = sql.open();

    // I use SQL transactions to make sure that both queries pass without no constraint errors
    // setAutoCommit(false) allows to do rollbacks
    try {
      conn.setAutoCommit(false);
    } catch (SQLException e) {
      System.out.println("Error with setting auto commit: " + e);
      sql.close(conn);
      return;
    }

    try {
      PreparedStatement stmt = conn.prepareStatement(bookRoomQuery);
      stmt.setString(1, courseName);
      stmt.setString(2, slotName);
      stmt.setString(3, roomName);
      stmt.setString(4, courseName);
      stmt.setString(5, slotName);
      stmt.setString(6, roomName);

      sql.cmd(stmt);
    } catch (Exception e) {
      sql.close(conn);
      throw new RuntimeException("Error: the room is already booked or the course is already booked at that slot at another room");
    }

    try {
      PreparedStatement stmt = conn.prepareStatement(assignLecturerQuery);
      stmt.setString(1, lecturerName);
      stmt.setString(2, slotName);
      stmt.setString(3, roomName);

      sql.cmd(stmt);
    } catch (Exception e) {
      try {
        conn.rollback();
      } catch (SQLException sqlE) {
        System.out.println("Error while executing rollback: " + sqlE);
      }
      sql.close(conn);

      throw new RuntimeException("Error: the lecturer is already booked");
    }

    try {
      conn.commit();
    } catch (SQLException e) {
      System.out.println("Error with committing: " + e);
    } finally {
      sql.close(conn);
    }
  }
}
