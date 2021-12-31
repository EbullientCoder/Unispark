package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

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

    //Look for courses marked by student faculty
    public static Cursor selectFacultyCourses(SQLiteDatabase db, String faculty, int uniYear) //throws exception
    {
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

        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for course's academic year marked by courseName
    public static Cursor selectYear(SQLiteDatabase db, String courseName) //throws exception
    {
        String queryString = "SELECT " + YEAR + " FROM " + COURSE_TABLE + " WHERE "
                + COURSE_NAME + " = '" + courseName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for course's CFU marked by courseName
    public static Cursor selectCFU(SQLiteDatabase db, String courseName) //throws exception
    {
        String queryString = "SELECT " + CFU + " FROM " + COURSE_TABLE + " WHERE " + COURSE_NAME + " = '" + courseName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
}
