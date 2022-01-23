package com.example.unispark.database.query;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryLessons {

    private static final String LESSON = "lesson";
    private static final String LESSONS = "lessons";
    private static final String DAY = "day";
    private static final String HOUR = "hour";
    private static final String AND = "' AND ";

    private QueryLessons(){}

    public static ResultSet selectLessons(Statement statement, String day, String courseName) throws SQLException {
        String queryString = "SELECT " + LESSON + ", " + DAY + ", " + HOUR + " FROM " + LESSONS + " WHERE " + DAY + " = '" + day + AND + LESSON + " = '" + courseName + "';";
        return statement.executeQuery(queryString);
    }



    public static ResultSet selectLessons(Statement statement, String day, String courseName, String hour) throws SQLException {
        String queryString = "SELECT " + LESSON + ", " + DAY + ", " + HOUR + " FROM " + LESSONS + " WHERE " + DAY + " = '" + day + AND + LESSON + " = '" + courseName + AND + HOUR + " = '" + hour + "';";
        return statement.executeQuery(queryString);
    }




    public static void insertLesson(Statement stmt, String lessonName, String day, String hour) throws SQLException {
        stmt.executeUpdate("INSERT INTO lessons(lesson, day, hour) VALUES('" +lessonName+ "', '" +day+ "', '" +hour+ "')");
    }




    public static void deleteLesson(Statement stmt, String lessonName, String day, String hour) throws SQLException {
        stmt.executeUpdate("DELETE FROM lessons WHERE lesson = '" +lessonName+ "' AND day = '" + day + "' AND hour ='" + hour + "'");
    }


}
