package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryFaculties {

    public static final String FACULTIES = "faculties";
    public static final String FACULTY = "faculty";

    private QueryFaculties(){}

    //Look for all university faculties
    public static Cursor selectFaculties(SQLiteDatabase db) //throws exception
    {
        String queryString = "SELECT " + FACULTY + " FROM " + FACULTIES + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }
}
