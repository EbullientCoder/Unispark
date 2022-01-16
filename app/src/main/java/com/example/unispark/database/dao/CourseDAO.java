package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryExams;
import com.example.unispark.exceptions.CourseException;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.facade.CourseCreatorFacade;
import com.example.unispark.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private CourseDAO(){}

    public static void addCourse(CourseModel course) throws CourseException, DatabaseOperationError, SQLiteException
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        Cursor cursor = QueryCourse.selectCourseName(db, course.getFullName());

        if (cursor.moveToFirst()) throw new CourseException(0);


        ContentValues cv = new ContentValues();
        cv.put("shortname", course.getShortName());
        cv.put("coursename", course.getFullName());
        cv.put("year", course.getCourseYear());
        cv.put("cfu", course.getCfu());
        cv.put("session", course.getSession());
        cv.put("link", course.getLink());
        cv.put("trackprofessor", course.getId());
        cv.put("faculty", course.getFaculty());
        cv.put("uniyear", course.getUniYear());

        //Insert into Database: Homework Table
        long insert = db.insert("courses", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);

    }

    public static void joinCourse(String studentID, String courseName) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        ContentValues cv = new ContentValues();
        cv.put("studentID", studentID);
        cv.put("coursename", courseName);

        long insert = db.insert("studentscourses", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);

    }

    public static void leaveCourse(String studentID, String courseName) throws CourseException, SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursorExam = QueryExams.selectBookedExams(db, studentID);
        if (cursorExam.moveToFirst()){
            int examId;
            Cursor cursorExamName;
            String examName;
            do{
                examId = cursorExam.getInt(0);
                cursorExamName = QueryExams.selectExamId(db, examId);
                cursorExamName.moveToFirst();
                examName = cursorExamName.getString(1);
                if(examName.equals(courseName)){
                    throw new CourseException(1);
                }
            } while (cursorExam.moveToNext());
        }
        db.close();
        db = SQLiteConnection.getWritableDB();
        int delete = db.delete("studentscourses","studentID='" + studentID + "' and coursename='" + courseName + "'",null);
        if (!(delete > 0)) throw new DatabaseOperationError(1);

    }


    public static List<CourseModel> selectStudentCourses(String studentId) throws SQLiteException
    {
        List<CourseModel> coursesList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursorCourse = QueryCourse.selectStudentCourses(db, studentId);

        if (cursorCourse.moveToFirst()){
            String courseName;
            Cursor cursor;
            do{
                courseName = cursorCourse.getString(0);
                cursor = QueryCourse.selectCourseName(db, courseName);
                cursor.moveToFirst();

                coursesList.add(CourseCreatorFacade.getInstance().createCourse(cursor));
                cursor.close();
            } while (cursorCourse.moveToNext());
        }

        cursorCourse.close();
        db.close();

        return coursesList;
    }

    public static List<CourseModel> selectProfessorCourses(int professorId) throws SQLiteException
    {
        List<CourseModel> coursesList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryCourse.selectProfessorCourses(db, professorId);

        if (cursor.moveToFirst()){
            do {
                coursesList.add(CourseCreatorFacade.getInstance().createCourse(cursor));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return coursesList;
    }

    //Get available course names to join for a student marked by faculty and that are not already in the student's courses list
    public static List<CourseModel> selectAvailableCourses(String faculty, int uniYear, List<CourseModel> courses) throws SQLiteException
    {
        List<CourseModel> coursesList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryCourse.selectFacultyCourses(db, faculty, uniYear);

        if (cursor.moveToFirst()){
            coursesList = CourseCreatorFacade.getInstance().getAvaliableCourses(cursor, courses);
        }
        cursor.close();
        db.close();

        return coursesList;
    }

    public static List<CourseModel> selectCourses(String faculty) throws SQLiteException
    {
        List<CourseModel> coursesList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryCourse.selectFacultyCourses(db, faculty);

        if (cursor.moveToFirst()){
            do{
                coursesList.add(CourseCreatorFacade.getInstance().createCourse(cursor));
            } while(cursor.moveToNext());
        }
        cursor.close();
        db.close();

        return coursesList;
    }


}
