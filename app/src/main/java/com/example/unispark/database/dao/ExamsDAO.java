package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryExams;
import com.example.unispark.database.query.QueryStudent;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.ArrayList;
import java.util.List;

public class ExamsDAO {

    private ExamsDAO(){}

    public static boolean addExam(BookExamModel exam)
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


    public static boolean addExamGrade(VerbalizedExamModel examGrade, String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        Cursor cursor = QueryExams.selectExamDate(db, examGrade.getId());

        if (!cursor.moveToFirst()){}//throws exception
        Cursor isValid = QueryExams.selectExamDate(db, examGrade.getId());
        //if (isValid.moveToFirst()) return false;

        ContentValues cv = new ContentValues();
        cv.put("id", examGrade.getId());
        cv.put("examname", examGrade.getName());
        cv.put("studentID", studentID);
        cv.put("grade", examGrade.getResult());
        long insert = db.insert("examgrades", null, cv);

        if (insert == -1) return false;
        else {
            boolean isRemoved = removeBookedExam(examGrade.getId(), studentID);
            if(isRemoved) return true;
            return false;
        }
    }

    //Create a new Booking Exam model
    private static BookExamModel bookingExam(Cursor cursor, String courseName)
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

        //Create new bookingExam model
        BookExamModel exam = new BookExamModel(id, name, year, dateTime, cfu, classroom, building);

        return exam;
    }

    //Select exams marked by studentID/professorId depending on boolean isProfessor
    public static List<BookExamModel> getExams(String id, boolean isProfessor)
    {
        List<BookExamModel> exams;
        if (isProfessor) {
            exams = ExamsFacade.getInstance().getProfessorExams(id);
        }
        else{
            exams = ExamsFacade.getInstance().getStudentExams(id);
        }
        return exams;
    }

    //Select exams marked my courseName
    public static List<BookExamModel> getCourseExams(CourseModel course, boolean isProfessor)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        List<BookExamModel> examsList = new ArrayList<>();

        Cursor cursor = QueryExams.selectExams(db, course.getFullName(), isProfessor);
        if (!cursor.moveToFirst()){
            //throw exception
            return null;
        }

        do {
            examsList.add(bookingExam(cursor, course.getFullName()));
        } while(cursor.moveToNext());
        //Close both db and cursor
        cursor.close();
        db.close();

        return examsList;
    }


    public static boolean bookExam(BookExamModel exam, String studentID){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if(cursor.moveToFirst()){
            String takenExam;
            String result;
            do {
                takenExam = cursor.getString(1);
                result = cursor.getString(2);
                if (exam.getName().equals(takenExam) && Double.valueOf(result) >= 18) return false;

            } while (cursor.moveToNext());
        }
        ContentValues cv = new ContentValues();

        cv.put("studentID", studentID);
        cv.put("examID", exam.getId());
        long insert = db.insert("studentexams", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    //Get booked exams marked by studentID
    public static List<BookExamModel> getBookedExams(String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectBookedExams(db, studentID);
        if (!cursor.moveToFirst()) return null;

        List<BookExamModel> bookedExamsList = new ArrayList<>();

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
    public static VerbalizedExamModel examGrade(Cursor cursorGrade, String result)
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
    public static List<VerbalizedExamModel> getVerbalizedExams(String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if (!cursor.moveToFirst()) return null;
        List<VerbalizedExamModel> gradesList = new ArrayList<>();

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
    public static List<VerbalizedExamModel> getFailedExams(String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryExams.selectExamGrades(db, studentID);
        if (!cursor.moveToFirst()) return null;
        List<VerbalizedExamModel> gradesList = new ArrayList<>();

        do {
            String result = cursor.getString(2);
            double numberResult = Double.valueOf(result);
            if (numberResult < 18){
                gradesList.add(examGrade(cursor, result));
            }
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return gradesList;
    }

    //Get students that booked an exam
    public static List<BeanStudentSignedToExam> getStudentsBookedExam(int examID)
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        Cursor cursor = QueryExams.selectStudents(db, examID);
        if (!cursor.moveToFirst()) return null;

        List<BeanStudentSignedToExam> studentsList = new ArrayList<>();
        //BeanStudentSignedToExam
        BeanStudentSignedToExam student;
        String id;
        String fullName;
        Cursor cursorName;
        do{
            id = cursor.getString(0);
            cursorName = QueryStudent.getStudentName(db, id);
            if (!cursorName.moveToFirst()){}//throws exception
            fullName = cursorName.getString(0) + " " + cursorName.getString(1);

            //Create Student and add it to the list
            student = new BeanStudentSignedToExam(id, fullName);
            studentsList.add(student);
            cursorName.close();
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return studentsList;
    }

    //Remove studentID booked exam from DB
    public static boolean removeBookedExam(int examID, String studentID)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("studentexams","studentID='" + studentID + "' and examID=" + examID,null);
        if (delete > 0) return true;
        return false;
    }

    //Remove bookingExam from DB
    public static boolean removeExam(int examID)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("exams","id=" + examID,null);
        if (delete > 0) return true;
        return false;

    }


}
