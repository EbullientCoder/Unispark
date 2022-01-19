package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryFaculties {

    public static final String FACULTIES = "faculties";
    public static final String FACULTY = "faculty";

    private QueryFaculties(){}


    //Look for all university faculties
    public static ResultSet selectFaculties(Statement statement) throws SQLException
    {
        String queryString = "SELECT " + FACULTY + " FROM " + FACULTIES + ";";
        return statement.executeQuery(queryString);
    }
}
