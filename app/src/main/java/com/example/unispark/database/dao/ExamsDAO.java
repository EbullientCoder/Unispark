package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryExams;
import com.example.unispark.database.query.QueryStudent;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.ArrayList;
import java.util.List;

public class ExamsDAO {

    private ExamsDAO(){}

    public static void addExam(BookExamModel exam) throws ExamException, DatabaseOperationError, SQLiteException
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        Cursor cursor = QueryExams.selectExam(db, exam.getName(), exam.getDate());

        if (cursor.moveToFirst()) throw new ExamException(0);

        ContentValues cv = new ContentValues();
        cv.put("examname", exam.getName());
        cv.put("date", exam.getDate());
        cv.put("building", exam.getBuilding());
        cv.put("class", exam.getClassroom());
        long insert = db.insert("exams", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);

    }


    public static void addExamGrade(VerbalizedExamModel examGrade, String studentID) throws ExamException, DatabaseOperationError, SQLiteException
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        Cursor cursorDate = QueryExams.selectExamDate(db, examGrade.getId());
        if (cursorDate.moveToFirst()) throw new ExamException(1);

        ContentValues cv = new ContentValues();
        cv.put("id", examGrade.getId());
        cv.put("examname", examGrade.getName());
        cv.put("studentID", studentID);
        cv.put("grade", examGrade.getResult());
        long insert = db.insert("examgrades", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);
        else removeBookedExam(examGrade.getId(), studentID);

    }

    //Create a new Booking Exam model
    private static BookExamModel bookingExam(Cursor cursor, String courseName) throws SQLiteException
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        Cursor cursorYear = QueryCourse.selectYear(db, courseName);
        if (!cursorYear.moveToFirst()){} //throws exception
        String year = cursorYear.getString(0);
        String dateTime = cursor.getString(2);
        Cursor cursorCFU = QueryCourse.selectCFU(db, courseName);
        if (!cursorCFU.moveToFirst()){} //throws exception
        String cfu = cursorCFU.getString(0);
        String building = cursor.getString(3);
        String classroom = cursor.getString(4);

        //Create new bookExam model
        BookExamModel exam = new BookExamModel(id, name, year, dateTime, cfu, classroom, building);

        return exam;
    }

    //Select exams marked my courseName
    public static List<BookExamModel> getCourseExams(CourseModel course, boolean isProfessor) throws SQLiteException
    {
        List<BookExamModel> examsList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExams(db, course.getFullName(), isProfessor);

        if (cursor.moveToFirst()){
            do {
                examsList.add(bookingExam(cursor, course.getFullName()));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return examsList;
    }


    public static void bookExam(BookExamModel exam, String studentID) throws ExamException, SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        Cursor cursor = QueryExams.selectExamGrades(db, studentID);

        if(cursor.moveToFirst()){
            String takenExam;
            String result;
            do {
                takenExam = cursor.getString(1);
                result = cursor.getString(2);
                if (exam.getName().equals(takenExam) && Double.valueOf(result) >= 18) throw new ExamException(2);
            } while (cursor.moveToNext());
        }

        ContentValues cv = new ContentValues();
        cv.put("studentID", studentID);
        cv.put("examID", exam.getId());
        long insert = db.insert("studentexams", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);

    }

    //Get booked exams marked by studentID
    public static List<BookExamModel> getBookedExams(String studentID) throws SQLiteException
    {
        List<BookExamModel> bookedExamsList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        Cursor cursor = QueryExams.selectBookedExams(db, studentID);
        if (cursor.moveToFirst()) {
            int examID;
            Cursor cursorExam;
            String examName;

            do {
                examID = cursor.getInt(0);
                cursorExam = QueryExams.selectExamId(db, examID);
                if (!cursorExam.moveToFirst()){} //throw exception
                examName = cursorExam.getString(1);

                bookedExamsList.add(bookingExam(cursorExam, examName));
                cursorExam.close();
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return bookedExamsList;
    }


    //Create a new examGrade
    public static VerbalizedExamModel examGrade(Cursor cursorGrade, String result) throws SQLiteException
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        List<VerbalizedExamModel> examGrades = new ArrayList<>();

        int id = cursorGrade.getInt(0);
        String name = cursorGrade.getString(1);
        Cursor cursorYear = QueryCourse.selectYear(db, name);
        if(!cursorYear.moveToFirst()) {}//throw exception
        String year = cursorYear.getString(0);
        Cursor cursorDate = QueryExams.selectDate(db, name);
        if(!cursorDate.moveToFirst()) {}//throw exception
        String date = cursorDate.getString(0);
        Cursor cursorCFU = QueryCourse.selectCFU(db, name);
        if(!cursorCFU.moveToFirst()) {}//throw exception
        String cfu = cursorCFU.getString(0);

        //Create new ExamGrade
        VerbalizedExamModel examGrade = new VerbalizedExamModel(id, name, year, date, cfu, result);

        cursorYear.close();
        cursorDate.close();
        cursorCFU.close();
        db.close();

        return examGrade;
    }

    //Get verbalized exams
    public static List<VerbalizedExamModel> getVerbalizedExams(String studentID) throws SQLiteException
    {
        List<VerbalizedExamModel> gradesList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if (cursor.moveToFirst()) {
            do {
                String result = cursor.getString(2);
                double numberResult = Double.valueOf(result);
                if (numberResult >= 18){
                    gradesList.add(examGrade(cursor, result));
                }
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return gradesList;
    }

    //Get failed exams List
    public static List<VerbalizedExamModel> getFailedExams(String studentID) throws SQLiteException
    {
        List<VerbalizedExamModel> gradesList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if (cursor.moveToFirst()) {
            do {
                String result = cursor.getString(2);
                double numberResult = Double.valueOf(result);
                if (numberResult < 18){
                    gradesList.add(examGrade(cursor, result));
                }
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return gradesList;
    }

    //Get students that booked an exam
    public static List<BeanStudentSignedToExam> getStudentsBookedExam(int examID) throws SQLiteException
    {
        List<BeanStudentSignedToExam> studentsList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectStudents(db, examID);
        if (cursor.moveToFirst()) {

            //BeanStudentSignedToExam
            BeanStudentSignedToExam student;
            String id;
            String fullName;
            Cursor cursorName;
            do{
                id = cursor.getString(0);
                cursorName = QueryStudent.getStudentName(db, id);
                cursorName.moveToFirst();
                fullName = cursorName.getString(0) + " " + cursorName.getString(1);

                //Create Student and add it to the list
                student = new BeanStudentSignedToExam(id, fullName);
                studentsList.add(student);
                cursorName.close();
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentsList;
    }

    //Remove studentID booked exam from DB
    public static void removeBookedExam(int examID, String studentID) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        int delete = db.delete("studentexams","studentID='" + studentID + "' and examID=" + examID,null);
        if (!(delete > 0)) throw new DatabaseOperationError(1);

    }

    //Remove bookingExam from DB
    public static void removeExam(int examID) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("exams","id=" + examID,null);
        if (!(delete > 0)) throw new DatabaseOperationError(1);

    }


}
