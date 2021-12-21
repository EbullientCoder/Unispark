package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryHomework {
    //Homework table
    public static final String HOMEWORK_TABLE = "homework";
    public static final String COURSE_NAME = "coursename";
    public static final String TRACK_PROFESSOR = "trackprofessor";

    private QueryHomework(){}

    //Look for homeworks marked by courseName
    public static Cursor selectHomework(SQLiteDatabase db, String courseName) //throws exception
    {
        String queryString = "SELECT * FROM " + HOMEWORK_TABLE + " WHERE " + COURSE_NAME + " = '" + courseName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for homeworks tracked by professorID
    public static Cursor selectProfessorHomework(SQLiteDatabase db, int professorID) //throws exception
    {
        String queryString = "SELECT * FROM " + HOMEWORK_TABLE + " WHERE " + TRACK_PROFESSOR + " = " + professorID + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }


}
