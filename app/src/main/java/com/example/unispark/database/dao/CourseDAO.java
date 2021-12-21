package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.model.CourseModel;

public class CourseDAO {

    private CourseDAO(){}

    public static boolean addCourse(CourseModel course){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("shortname", course.getShortName());
        cv.put("coursename", course.getFullName());
        cv.put("year", course.getCourseYear());
        cv.put("cfu", course.getCfu());
        cv.put("session", course.getSession());
        cv.put("link", course.getLink());
        cv.put("trackprofessor", course.getId());

        //Insert into Database: Homework Table
        long insert = db.insert("courses", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    public static boolean joinCourse(String studentID, String courseName){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("studentID", studentID);
        cv.put("coursename", courseName);

        long insert = db.insert("studentscourses", null, cv);

        if (insert == -1) return false;
        else return true;
    }
}
