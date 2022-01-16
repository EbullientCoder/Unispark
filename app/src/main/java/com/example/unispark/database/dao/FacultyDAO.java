package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryFaculties;
import com.example.unispark.exceptions.DatabaseOperationError;

import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {

    public static final String FACULTIES = "faculties";
    public static final String FACULTY = "faculty";

    private FacultyDAO(){}

    public static void addFaculty(String faculty) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        ContentValues cv = new ContentValues();
        cv.put(FACULTY, faculty);
        long insert = db.insert(FACULTIES, null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);
    }

    public static List<String> getUniversityFaculties() throws SQLiteException
    {

        List<String> faculties = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryFaculties.selectFaculties(db);

        if (cursor.moveToFirst()) {
            do{
                faculties.add(cursor.getString(0));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return faculties;
    }
}
