package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

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

    public static boolean leaveCourse(String studentID, String courseName){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("studentscourses","studentID='" + studentID + "' and coursename='" + courseName + "'",null);
        if (delete > 0) return true;
        return false;
    }

    //Get available course names to join for a student marked by faculty
    public static List<CourseModel> selectAvailableCourses(String faculty, List<String> courseNames)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        List<CourseModel> coursesList = new ArrayList<>();

        Cursor cursor = QueryCourse.selectFacultyCourses(db, faculty);
        if (!cursor.moveToFirst()){
            //throw exception
            return null;
        }
        //Course attributes
        CourseModel course;
        String courseId;
        String shortName;
        String fullName;
        String courseYear;
        String cfu;
        String session;
        String link;
        String facultyCourse;

        boolean equals = false;
        do{
            courseId = String.valueOf(cursor.getInt(7));
            shortName = cursor.getString(1);
            fullName = cursor.getString(2);
            for (int i = 0; i < courseNames.size(); i++) {
                if (fullName.equals(courseNames.get(i))) {
                    equals = true;
                    break;
                }
            }
            if(!equals){
                courseYear = cursor.getString(3);
                cfu = cursor.getString(4);
                session = cursor.getString(5);
                link = cursor.getString(6);
                facultyCourse = cursor.getString(8);

                //Create a new course and add it to the course list
                course = new CourseModel(courseId, shortName, fullName, courseYear, cfu, session, link, facultyCourse);
                coursesList.add(course);
            }
            equals = false;
        } while (cursor.moveToNext());

        cursor.close();
        db.close();

        return coursesList;
    }

}
