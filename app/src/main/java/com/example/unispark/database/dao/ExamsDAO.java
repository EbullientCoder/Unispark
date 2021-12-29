package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.unispark.bean.StudentBean;
import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryExams;
import com.example.unispark.database.query.QueryStudent;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.exams.BookingExamModel;
import com.example.unispark.model.exams.ExamGradeModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ExamsDAO {

    private ExamsDAO(){}

    public static boolean addExam(BookingExamModel exam)
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


    public static boolean addExamGrade(ExamGradeModel examGrade, String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("examname", examGrade.getName());
        cv.put("studentID", studentID);
        cv.put("grade", examGrade.getResult());
        long insert = db.insert("examgrades", null, cv);

        if (insert == -1) return false;
        else {

            return true;
        }
    }

    //Create a new Booking Exam model(not sure if in DAO)
    public static BookingExamModel bookingExam(Cursor cursor, String courseName)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        int id = cursor.getInt(0);
        String name = cursor.getString(1);
        Cursor cursorYear = QueryCourse.selectCourseName(db, courseName);
        if (!cursorYear.moveToFirst()){} //throws exception
        String year = cursorYear.getString(0);
        String dateTime = cursor.getString(2);
        Cursor cursorCFU = QueryCourse.selectCFU(db, courseName);
        if (!cursorCFU.moveToFirst()){} //throws exception
        String cfu = cursorCFU.getString(0);
        String building = cursor.getString(3);
        String classroom = cursor.getString(4);

        //Create new bookingExam model
        BookingExamModel exam = new BookingExamModel(id, name, year, dateTime, cfu, classroom, building);

        return exam;
    }

    //Select student exams marked my courseName
    public static List<BookingExamModel> getExams(String courseName, boolean isProfessor)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        List<BookingExamModel> examsList = new ArrayList<>();

        Cursor cursor = QueryExams.selectExams(db, courseName, isProfessor);

        if (!cursor.moveToFirst()){
            //throw exception
            return null;
        }

        do {
            examsList.add(bookingExam(cursor, courseName));
        } while(cursor.moveToNext());

        //Close both db and cursor
        cursor.close();
        db.close();;

        return examsList;
    }

    //Select exams marked by List of courseNames(Implement into another class, not the DAO)
    public static List<BookingExamModel> getExams(List<String> courseNames, boolean isProfessor)
    {
        List<BookingExamModel> examsList = new ArrayList<>();
        List<BookingExamModel> tempList;
        for (int i = 0; i < courseNames.size(); i++)
        {
            tempList = getExams(courseNames.get(i), isProfessor);
            if(tempList != null){
                examsList.addAll(tempList);
            }
        }
        return examsList;
    }


    public static boolean bookExam(BookingExamModel exam, String studentID){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("studentID", studentID);
        cv.put("examID", exam.getId());
        long insert = db.insert("studentexams", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Get booked exams marked by studentID
    public static List<BookingExamModel> getBookedExams(String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectBookedExams(db, studentID);
        if (!cursor.moveToFirst()) return null;

        List<BookingExamModel> bookedExamsList = new ArrayList<>();

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

        cursor.close();
        db.close();

        return bookedExamsList;
    }


    //Create a new examGrade (not sure if in the DAO)
    public static ExamGradeModel examGrade(Cursor cursorGrade, String result)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        List<ExamGradeModel> examGrades = new ArrayList<>();

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
        ExamGradeModel examGrade = new ExamGradeModel(id, name, year, date, cfu, result);

        cursorYear.close();
        cursorDate.close();
        cursorCFU.close();
        db.close();

        return examGrade;
    }

    //Get verbalized exams
    public static List<ExamGradeModel> getVerbalizedExams(String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if (!cursor.moveToFirst()) return null;
        List<ExamGradeModel> gradesList = new ArrayList<>();

        do {
            String result = cursor.getString(2);
            double numberResult = Double.valueOf(result);
            if (numberResult >= 18){
                gradesList.add(examGrade(cursor, result));
            }
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return gradesList;
    }

    //Get failed exams List
    public static List<ExamGradeModel> getFailedExams(String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if (!cursor.moveToFirst()) return null;
        List<ExamGradeModel> gradesList = new ArrayList<>();

        do {
            String result = cursor.getString(2);
            double numberResult = Double.valueOf(result);
            if (numberResult < 18){
                gradesList.add(examGrade(cursor, "Retired"));
            }
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return gradesList;
    }

    //Get students that booked an exam
    public static List<StudentBean> getStudentsBookedExam(String examName)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        Cursor cursor = QueryExams.selectStudents(db, examName);
        if (!cursor.moveToFirst()) return null;

        List<StudentBean> studentsList = new ArrayList<>();
        //StudentBean
        StudentBean student;
        String id;
        String fullName;
        Cursor cursorName;
        do{
            id = cursor.getString(0);
            cursorName = QueryStudent.getStudentName(db, id);
            if (!cursorName.moveToFirst()){}//throws exception
            fullName = cursorName.getString(0) + " " + cursorName.getString(1);

            //Create Student and add it to the list
            student = new StudentBean(id, fullName);
            studentsList.add(student);
            cursorName.close();
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return studentsList;
    }

    /*
    //Remove studentID booked exam from DB
    public static boolean removeBookedExams(BookingExamModel exam, String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("studentexams","studentID=? and coursename=?",new String[]{studentID,courseName});
        if (delete > 0)return true;
        return false;
    }*/

}
