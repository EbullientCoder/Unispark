package com.example.unispark.database.query;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.exceptions.GenericException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryCourse {
    //Courses table
    public static final String COURSE_TABLE = "courses";
    public static final String COURSE_NAME = "coursename";
    public static final String STUDENTS_COURSES = "studentscourses";
    public static final String STUDENT_ID = "studentID";
    public static final String FACULTY = "faculty";
    public static final String YEAR = "year";
    public static final String CFU = "cfu";
    public static final String UNIVERSITY_YEAR = "uniyear";

    private QueryCourse(){}

    //Look for course marked by course name
    public static ResultSet selectCourseName(Statement statement, String courseName) throws SQLException {
        String queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE " + COURSE_NAME + " = '" + courseName + "';";
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
        String queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE trackprofessor = " + professorID + ";";
        return statement.executeQuery(queryString);
    }

    //Look for courses marked by student faculty
    public static ResultSet selectFacultyCourses(Statement statement, String faculty, int uniYear) throws SQLException {
        String queryString = null;
        if (uniYear == 1){
            queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE " + UNIVERSITY_YEAR + " = " + uniYear
                     + " AND " + FACULTY + " = '" + faculty + "';";
        }
        else if (uniYear == 2){
            queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE (" + UNIVERSITY_YEAR + " = 1 OR "
                    + UNIVERSITY_YEAR + " = 2)" + " AND " + FACULTY + " = '" + faculty + "';";
        }
        else if (uniYear == 3){
            queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE " + FACULTY + " = '" + faculty + "';";
        }

        return statement.executeQuery(queryString);
    }




    public static ResultSet selectFacultyCourses(Statement statement, String faculty) throws SQLException
    {
        String queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE " + FACULTY + " = '" + faculty + "';";

        return statement.executeQuery(queryString);
    }


    public static void addStudentCourse(Statement stmt, String studentId, String courseName) throws SQLException {
        stmt.executeUpdate("INSERT INTO studentscourses(studentID, coursename) VALUES('" +studentId+ "', '" +courseName+ "')");
    }



    public static void removeStudentCourse(Statement stmt, String studentId, String courseName) throws SQLException {
        stmt.executeUpdate("DELETE FROM studentscourses WHERE studentID = '" +studentId+ "' AND coursename = '" + courseName + "'");
    }
}



