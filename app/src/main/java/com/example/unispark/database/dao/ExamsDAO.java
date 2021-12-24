package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookingExamModel;
import com.example.unispark.model.exams.ExamGradeModel;

import java.util.ArrayList;
import java.util.List;

public class ExamsDAO {

    private ExamsDAO(){}

    public boolean addExam(BookingExamModel exam)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("examname", exam.getName());
        cv.put("date", exam.getDate());
        cv.put("building", exam.getBuilding());
        cv.put("class", exam.getClassroom());
        long insert = db.insert("exams", null, cv);

        if (insert == -1) return false;
        else return true;
    }


    public boolean addExamGrade(ExamGradeModel examGrade, StudentModel student)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("examname", examGrade.getName());
        cv.put("studentID", student.getId());
        cv.put("grade", examGrade.getResult());
        long insert = db.insert("examgrades", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    /*public List<BookingExamModel> getBookingExams(List<String> courseNames)
    {
        List<BookingExamModel> bookingExamsList = new ArrayList<>();
        Cursor cursor;

        for (int i = 0; i < courseNames.size(); i++){

        }
    }*/

}
