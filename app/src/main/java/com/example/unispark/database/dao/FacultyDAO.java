package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryFaculties;

import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {

    public static final String FACULTIES = "faculties";
    public static final String FACULTY = "faculty";

    private FacultyDAO(){}

    public static boolean addFaculty(String faculty){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put(FACULTY, faculty);
        long insert = db.insert(FACULTIES, null, cv);

        if (insert == -1) return false;
        else return true;
    }

    public static List<String> getUniversityFaculties(){
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryFaculties.selectFaculties(db);
        if (!cursor.moveToFirst()) return null; //throw exception

        List<String> faculties = new ArrayList<>();

        do{
            faculties.add(cursor.getString(0));
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return faculties;
    }
}
