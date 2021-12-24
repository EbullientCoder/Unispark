package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryCourse {
    //Courses table
    public static final String COURSE_TABLE = "courses";
    public static final String COURSE_NAME = "coursename";
    public static final String STUDENTS_COURSES = "studentscourses";
    public static final String STUDENT_ID = "studentID";

    private QueryCourse(){}

    //Look for course marked by course name
    public static Cursor selectCourseName(SQLiteDatabase db, String courseName) //throws exception
    {
        String queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE " + COURSE_NAME + " = '" + courseName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for courses marked by studentID
    public static Cursor selectStudentCourses(SQLiteDatabase db, String studentID) //throws exception
    {
        String queryString = "SELECT " + COURSE_NAME + " FROM " + STUDENTS_COURSES + " WHERE "
                + STUDENT_ID + " = '" + studentID + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for courses marked by professorID
    public static Cursor selectProfessorCourses(SQLiteDatabase db, int professorID) //throws exception
    {
        String queryString = "SELECT * FROM " + COURSE_TABLE + " WHERE trackprofessor = " + professorID + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for course name marked by courseShortName



}
