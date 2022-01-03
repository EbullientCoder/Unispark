package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryLessons {

    public static final String LESSON = "lesson";
    public static final String LESSONS = "lessons";
    public static final String DAY = "day";
    public static final String HOUR = "hour";

    private QueryLessons(){}

    public static Cursor selectLessons(SQLiteDatabase db, String day, String courseName) // throws exception
    {
        String queryString = "SELECT " + LESSON + ", " + DAY + ", " + HOUR + " FROM " + LESSONS + " WHERE " + DAY + " = '" + day + "' AND " + LESSON + " = '" + courseName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }


}
