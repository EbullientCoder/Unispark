package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryProfessor {

    //Professor table
    public static final String PROFESSORS_TABLE = "professors";
    public static final String PROF_ID = "professorID";
    public static final String TRACK_PROFESSOR = "trackprofessor";
    public static final String IMAGE = "image";
    public static final String COURSE_TABLE = "courses";
    public static final String SHORTNAME = "shortname";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";


    private QueryProfessor(){}

    //Look for professor's id tracked by courseShortName
    public static Cursor getProfessorId(SQLiteDatabase db, String courseShortName) //throws exception
    {
        String queryString = "SELECT " + TRACK_PROFESSOR + " FROM " + COURSE_TABLE + " WHERE " + SHORTNAME + " = '" + courseShortName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for professor's profile imageID marked by professorId
    public static Cursor getProfessorProfileImage(SQLiteDatabase db, int professorId) //throws exception
    {
        String queryString = "SELECT " + IMAGE + " FROM " + PROFESSORS_TABLE + " WHERE " + PROF_ID + " = " + professorId + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for professor's Name marked by professorId
    public static Cursor getProfessorName(SQLiteDatabase db, int professorId) //throws exception
    {
        String queryString = "SELECT " + FIRSTNAME + ", " + LASTNAME + " FROM " + PROFESSORS_TABLE + " WHERE " + PROF_ID + " = " + professorId + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }


}
