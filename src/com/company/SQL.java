package com.company;

import java.sql.*;

public class SQL {
  public Connection open() {
    Connection conn = null;

    try {
      String url = "jdbc:sqlite:db/database.db";
      conn = DriverManager.getConnection(url);

      // sqlite by default does not check for foreign keys. This command has to be run every time when opening the database.
      // I am not sure why intellij does not recognize PRAGMA keyword
      PreparedStatement stmt = conn.prepareStatement("PRAGMA foreign_keys = ON;");
      cmd(stmt);
    } catch (SQLException e) {
      System.out.println("Error while opening sqlite database: " + e);
    };

    return conn;
  }

  public void close(Connection conn) {
    try {
      conn.close();
    } catch (SQLException e ) {
      System.out.println("Error while closing sqlite database: " + e);
    }
  }

  public ResultSet query(PreparedStatement stmt) {
    ResultSet result = null;

    try {
      result = stmt.executeQuery();

    } catch (SQLException e) {
      System.out.println("Error while executing query: " + e);
    }

    return result;
  }

  public void cmd(PreparedStatement stmt) {
    try {
      stmt.executeUpdate();

    } catch (SQLException e) {
      // Pass the error only if the sqlite constraint check failed so that the user could be informed
      if (e.getErrorCode() == 19) {
        throw new RuntimeException();
      }

      System.out.println("Error while executing cmd: " + e);
    }
  }
}
