package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryStudentLinks {

    public static final String STUDENTS_LINKS = "studentslinks";
    public static final String STUDENT_ID = "studentID";

    private QueryStudentLinks(){}

    public static Cursor selectStudentLinks(SQLiteDatabase db, String studentId) //throws exception
    {
        String queryString = "SELECT * FROM " + STUDENTS_LINKS + " WHERE " + STUDENT_ID + " = '" + studentId + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }



}


