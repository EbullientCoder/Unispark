package com.example.unispark.database.query;

import static com.example.unispark.database.others.Password.getHash;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryLogin {
    // Students, professors and University tables
    public static final String STUDENTS_TABLE = "students";
    public static final String PROFESSORS_TABLE = "professors";
    public static final String UNI_TABLE = "university";

    private QueryLogin(){}

    //Look for a student account in the database, marked by email and password
    public static Cursor loginStudent(SQLiteDatabase db, String email, String password) //throws exception
    {

        String queryString = "SELECT * FROM " + STUDENTS_TABLE + " where email = '" + email
                + "' AND password = '" + getHash(password) + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for a student account in the database, marked by email
    public static Cursor loginStudent(SQLiteDatabase db, String email) //throws exception
    {

        String queryString = "SELECT * FROM " + STUDENTS_TABLE + " where email = '" + email + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for a professor account in the database, marked by email and password
    public static Cursor loginProfessor(SQLiteDatabase db, String email, String password) //throws exception
    {

        String queryString = "SELECT * FROM " + PROFESSORS_TABLE + " where email = '" + email
                + "' AND password = '" + getHash(password) + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for a professor account in the database, marked by email
    public static Cursor loginProfessor(SQLiteDatabase db, String email) //throws exception
    {

        String queryString = "SELECT * FROM " + PROFESSORS_TABLE + " where email = '" + email + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for a University account in the database, marked by email and password
    public static Cursor loginUniversity(SQLiteDatabase db, String email, String password) //throws exception
    {

        String queryString = "SELECT * FROM " + UNI_TABLE + " where email = '" + email
                + "' AND password = '" + getHash(password) + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for a University account in the database, marked by email
    public static Cursor loginUniversity(SQLiteDatabase db, String email) //throws exception
    {

        String queryString = "SELECT * FROM " + UNI_TABLE + " where email = '" + email + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }








}
