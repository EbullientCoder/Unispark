package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryHomework;
import com.example.unispark.model.HomeworkModel;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeworkDAO {

    private HomeworkDAO(){}

    //Get homeworks of courses marked by studentID
    public static List<HomeworkModel> getStudentHomework(String studentID) //throws exception
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        List<HomeworkModel> homeworkList = new ArrayList<>();
        //Get courses tracked by studentId
        Cursor cursorCourse = QueryCourse.selectStudentCourses(db, studentID);

        if (!cursorCourse.moveToFirst()){
            //throw exception
            return null;
        }
        String courseName;
        Cursor cursorHomework;

        do{
            courseName = cursorCourse.getString(0);
            //Look for homeworks marked by courseName
            cursorHomework = QueryHomework.selectHomework(db, courseName);
            if (!cursorHomework.moveToFirst()){
                continue;
                //throw exception
            }
            //Course attributes
            String shortName;
            String fullName;
            String title;
            String expiration;
            String instructions;
            String points;
            int trackProfessor;

            do {
                shortName = cursorHomework.getString(1);
                fullName = cursorHomework.getString(2);
                title = cursorHomework.getString(3);
                expiration = cursorHomework.getString(4);
                instructions = cursorHomework.getString(5);
                points = cursorHomework.getString(6);
                trackProfessor = cursorHomework.getInt(7);
                //Create a new homework and add it to the homework list
                HomeworkModel newHomework = new HomeworkModel(shortName, fullName, title, expiration, instructions, points, trackProfessor);
                homeworkList.add(newHomework);
            } while (cursorHomework.moveToNext());
            cursorHomework.close();

        } while (cursorCourse.moveToNext());

        //close both the cursor and the db when done.
        cursorCourse.close();
        db.close();
        return homeworkList;
    }

    //Get homeworks marked by professorID
    public static List<HomeworkModel> getAssignedHomework(int professorID) //throw exception
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        List<HomeworkModel> homeworkList = new ArrayList<>();
        //Look for homeworks marked by professorID
        Cursor cursor = QueryHomework.selectProfessorHomework(db, professorID);

        if (!cursor.moveToFirst()){
            //throw exception
            return null;
        }
        //Homework attributes
        String shortName;
        String fullName;
        String title;
        String expiration;
        String instructions;
        String points;
        int trackProfessor;
        do{
            shortName = cursor.getString(1);
            fullName = cursor.getString(2);
            title = cursor.getString(3);
            expiration = cursor.getString(4);
            instructions = cursor.getString(5);
            points = cursor.getString(6);
            trackProfessor = cursor.getInt(7);
            //Create a new homework and add it to the homework list
            HomeworkModel newHomework = new HomeworkModel(shortName, fullName, title, expiration, instructions, points, trackProfessor);
            homeworkList.add(newHomework);
        } while (cursor.moveToNext());

        //Reverse Homeworks List
        Collections.reverse(homeworkList);

        //close both the cursor and the db when done.
        cursor.close();
        db.close();
        return homeworkList;
    }

    public static boolean addHomework(HomeworkModel homework){

        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("shortname", homework.getShortName());
        cv.put("coursename", homework.getFullname());
        cv.put("title", homework.getTitle());
        cv.put("expiration", homework.getExpiration());
        cv.put("instructions", homework.getInstructions());
        cv.put("points", homework.getPoints());
        cv.put("trackprofessor", homework.getTrackProfessor());

        //Insert into Database: Homework Table
        long insert = db.insert("homework", null, cv);

        if (insert == -1) return false;
        else return true;
    }
}
