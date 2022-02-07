package com.example.common.database.query;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryCourse {

    private QueryCourse(){}

    //Look for course marked by course name
    public static ResultSet selectCourseName(Statement statement, String courseName) throws SQLException {
        String queryString = "SELECT * FROM courses WHERE coursename = '" + courseName + "';";
        return statement.executeQuery(queryString);
    }

    //Look for courses marked by studentID
    public static ResultSet selectStudentCourses(Statement statement, String studentID) throws SQLException
    {
        String queryString = "SELECT * FROM courses INNER JOIN studentscourses ON courses.coursename = studentscourses.coursename WHERE " +
                "studentscourses.studentID = '" + studentID + "';";

        return statement.executeQuery(queryString);
    }

    //Look for courses marked by professorID
    public static ResultSet selectProfessorCourses(Statement statement, int professorID) throws SQLException
    {
        String queryString = "SELECT * FROM courses WHERE trackprofessor = " + professorID + ";";
        return statement.executeQuery(queryString);
    }

    //Look for courses marked by student faculty
    public static ResultSet selectFacultyCourses(Statement statement, String faculty, int uniYear) throws SQLException {
        String queryString = null;
        if (uniYear == 1){
            queryString = "SELECT * FROM courses WHERE uniyear = " + uniYear
                     + " AND faculty = '" + faculty + "';";
        }
        else if (uniYear == 2){
            queryString = "SELECT * FROM courses WHERE (uniyear = 1 OR uniyear = 2) AND faculty = '" + faculty + "';";
        }
        else if (uniYear == 3){
            queryString = "SELECT * FROM courses WHERE faculty = '" + faculty + "';";
        }

        return statement.executeQuery(queryString);
    }




    public static ResultSet selectFacultyCourses(Statement statement, String faculty) throws SQLException
    {
        String queryString = "SELECT * FROM courses WHERE faculty = '" + faculty + "';";

        return statement.executeQuery(queryString);
    }


    public static void addStudentCourse(Statement stmt, String studentId, String courseName) throws SQLException {
        stmt.executeUpdate("INSERT INTO studentscourses(studentID, coursename) VALUES('" +studentId+ "', '" +courseName+ "')");
    }



    public static void removeStudentCourse(Statement stmt, String studentId, String courseName) throws SQLException {
        stmt.executeUpdate("DELETE FROM studentscourses WHERE studentID = '" +studentId+ "' AND coursename = '" + courseName + "'");
    }
}



