package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.facade.StudentCreatorFacade;
import com.example.unispark.model.StudentModel;

public class StudentDAO {

    private StudentDAO(){}

    //Get a Student using the email and password
    public static StudentModel selectStudent(String email, String password) //throws exception
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryLogin.loginStudent(db, email, password);

        if (!cursor.moveToFirst()){
            // throw exception

            //Momentaneo
            StudentModel student1 = new StudentModel(null, null, null, 0, null, null, null, null, null, null, null);
            return student1;
        }

        String firstName = cursor.getString(1);
        String lastName =  cursor.getString(2);
        String studentEmail = cursor.getString(3);
        int profilePicture = cursor.getInt(5);
        String faculty = cursor.getString(6);
        String academicYear = cursor.getString(7);
        String studentId = cursor.getString(8);
        //Compose the student entity
        StudentModel student = StudentCreatorFacade.getInstance().getStudent(firstName, lastName, studentEmail, profilePicture, studentId, faculty, academicYear);

        cursor.close();
        db.close();

        return student;
    }
}
