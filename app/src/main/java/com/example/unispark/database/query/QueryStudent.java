package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryStudent {

    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String STUDENTS_TABLE = "students";
    public static final String STUDENT_ID = "studentID";

    private QueryStudent(){}

    public static Cursor getStudentName(SQLiteDatabase db, String studentID) //throws exception
    {
        String queryString = "SELECT " + FIRSTNAME + ", " + LASTNAME + " FROM " + STUDENTS_TABLE + " WHERE " + STUDENT_ID + " = '" + studentID + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

}