package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;


import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    private StudentDAO(){}

    //Get a Student using the email and password
    public static StudentModel selectStudent(String email, String password) //throws exception
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        StudentModel student;
        int imageID;
        String firstName;
        String lastName;
        String studentEmail;
        String studentPassword;
        String faculty;
        String academicYear;
        String id;


        Cursor cursor = QueryLogin.loginStudent(db, email, password);

        if (!cursor.moveToFirst()){
            // throw exception

            //Momentaneo
            StudentModel student1 = new StudentModel(0,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null);
            return student1;
        }
        firstName = cursor.getString(1);
        lastName = cursor.getString(2);
        studentEmail = cursor.getString(3);
        studentPassword = cursor.getString(4);
        imageID = cursor.getInt(5);
        faculty = cursor.getString(6);
        academicYear = cursor.getString(7);
        id = cursor.getString(8);

        //List of courses followed by student and their attributes
        List<CourseModel> coursesList = new ArrayList<>();

        CourseModel course;
        String courseId;
        String shortName;
        String fullName;
        String courseYear;
        String cfu;
        String session;
        String link;
        String facultyCourse;

        List<String> courseNames = new ArrayList<>();

        //Select all courses followed by studentId
        Cursor cursorCourse = QueryCourse.selectStudentCourses(db, id);

        if (!cursorCourse.moveToFirst()){
            //throw exception
            coursesList = null;
        }
        else{

            String courseName;
            do{
                courseName = cursorCourse.getString(0);
                courseNames.add(courseName);
                cursor = QueryCourse.selectCourseName(db, courseName);

                if (!cursor.moveToFirst()){
                    //throw exception
                }
                courseId = String.valueOf(cursor.getInt(7));
                shortName = cursor.getString(1);
                fullName = cursor.getString(2);
                courseYear = cursor.getString(3);
                cfu = cursor.getString(4);
                session = cursor.getString(5);
                link = cursor.getString(6);
                facultyCourse = cursor.getString(8);

                //Create a new course and add it to the student's course list
                course = new CourseModel(courseId, shortName, fullName, courseYear, cfu, session, link, facultyCourse);
                coursesList.add(course);
            } while (cursorCourse.moveToNext());
        }

        //Get booking exams List
        List<BookExamModel> bookingExams = ExamsDAO.getExams(courseNames, false);

        //Get booked exams List
        List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(id);

        List<BookExamModel> removeList = new ArrayList<>();

        BookExamModel bookingExam;
        BookExamModel bookedExam;
        for (int i = 0; bookedExams != null && i < bookedExams.size(); i++){
            bookedExam = bookedExams.get(i);
            for(int j = 0; bookingExams != null && j < bookingExams.size(); j++){
                bookingExam = bookingExams.get(j);
                if (bookingExam.getId() == bookedExam.getId()) removeList.add(bookingExams.get(j));
            }
        }
        if(bookingExams != null){
            bookingExams.removeAll(removeList);
        }

        //Get Verbalized exams
        List<VerbalizedExamModel> verbalizedExams = ExamsDAO.getVerbalizedExams(id);

        //Get Failed Exams
        List<VerbalizedExamModel> failedExams = ExamsDAO.getFailedExams(id);

        //Create the student instance
        student = new StudentModel(imageID,
                firstName,
                lastName,
                email,
                studentPassword,
                faculty,
                academicYear,
                id,
                coursesList,
                bookingExams,
                bookedExams,
                verbalizedExams,
                failedExams);

        //close both the cursor and the db when done.
        cursor.close();
        cursorCourse.close();
        db.close();
        return student;
    }
}
