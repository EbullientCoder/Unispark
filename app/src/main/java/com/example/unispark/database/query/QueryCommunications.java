package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryCommunications {

    //Communications tables (University and Professors)
    public static final String UNI_COMMUNICATIONS = "universitycommunications";
    public static final String FACULTY = "faculty";
    public static final String PROF_COMMUNICATIONS = "professorcommunications";
    public static final String SHORTNAME = "shortname";


    private QueryCommunications(){}

    //Look for University communications marked by facultyName
    public static Cursor selectFacultyCommunications(SQLiteDatabase db, String facultyName) //throws exception
    {
        String queryString = "SELECT * FROM " + UNI_COMMUNICATIONS + " WHERE " + FACULTY + " = '" + facultyName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for Professor communications marked by courseShortName
    public static Cursor selectCourseCommunications(SQLiteDatabase db, String courseShortName) //throws exception
    {
        String queryString = "SELECT * FROM " + PROF_COMMUNICATIONS + " WHERE " + SHORTNAME + " = '" + courseShortName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
}
