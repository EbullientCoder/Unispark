package com.example.unispark.database.query;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryStudentLinks {

    private static final String STUDENTS_LINKS = "studentslinks";
    private static final String STUDENT_ID = "studentID";

    private QueryStudentLinks(){}



    public static ResultSet selectStudentLinks(Statement statement, String studentId) throws SQLException
    {
        String queryString = "SELECT * FROM " + STUDENTS_LINKS + " WHERE " + STUDENT_ID + " = '" + studentId + "';";
        return statement.executeQuery(queryString);
    }



    public static void insertLink(Statement stmt, String studentId, String name, String link) throws SQLException {
        stmt.executeUpdate("INSERT INTO studentslinks(studentID, name, link) VALUES('" +studentId+ "', '" +name+ "', '" +link+ "')");
    }


    public static void deleteLink(Statement stmt, String link) throws SQLException {
        stmt.executeUpdate("DELETE FROM studentslinks WHERE link = '" +link+ "'");
    }

}
