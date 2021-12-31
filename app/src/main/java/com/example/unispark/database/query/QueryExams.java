package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class QueryExams {

    public static final String EXAMS = "exams";
    public static final String EXAM_NAME = "examname";
    public static final String STUDENT_EXAMS = "studentexams";
    public static final String STUDENT_ID = "studentID";
    public static final String GRADE = "grade";
    public static final String EXAM_GRADES = "examgrades";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String EXAM_ID = "examID";

    private QueryExams(){}

    //Look for exams marked by courseName
    public static Cursor selectExams(SQLiteDatabase db, String courseName, boolean isProfessor) //throws exception
    {
        String queryString;
        if (isProfessor) queryString = "SELECT * FROM " + EXAMS + " WHERE " + EXAM_NAME + " = '" + courseName + "';";
        else queryString = "SELECT * FROM " + EXAMS + " WHERE " + EXAM_NAME + " = '" + courseName + "' AND " + DATE + " >= date('now') ;";

        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for exams marked by studentID
    public static Cursor selectBookedExams(SQLiteDatabase db, String studentID) //throws exception
    {
        String queryString = "SELECT " + EXAM_ID + " FROM " + STUDENT_EXAMS + " WHERE " + STUDENT_ID + " = '" + studentID + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for exam marked by examID
    public static Cursor selectExamId(SQLiteDatabase db, int examID) //throws exception
    {
        String queryString = "SELECT * FROM " + EXAMS + " WHERE " + ID + " = " + examID + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for exam grades marked by studentID
    public static Cursor selectExamGrades(SQLiteDatabase db, String studentID) //throws exception
    {
        String queryString = "SELECT " + ID + ", " + EXAM_NAME + ", " + GRADE + " FROM " + EXAM_GRADES + " WHERE " + STUDENT_ID + " = '" + studentID + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    //Look for exam's date marked by examName
    public static Cursor selectDate(SQLiteDatabase db, String examName) //throws exception
    {
        String queryString = "SELECT " + DATE + " FROM " + EXAMS + " WHERE " + EXAM_NAME + " = '" + examName + "';";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    public static Cursor selectStudents(SQLiteDatabase db, int examID) //throws exception
    {
        String queryString = "SELECT " + STUDENT_ID + " FROM " + STUDENT_EXAMS + " WHERE " + EXAM_ID + " = " + examID + ";";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }

    public static Cursor selectExamDate(SQLiteDatabase db, int examID) //throws exception
    {
        String queryString = "SELECT " + DATE + " FROM " + EXAMS + " WHERE " + ID + " = " + examID + " AND " + DATE + " >= date('now') ;";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor;
    }



}
