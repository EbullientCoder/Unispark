package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.facade.StudentCreatorFacade;
import com.example.unispark.model.StudentModel;

import javax.security.auth.login.LoginException;

public class StudentDAO {

    private StudentDAO(){}

    //Get a Student using the email and password
    public static StudentModel selectStudent(String email, String password) throws LoginException
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        StudentModel student;

        Cursor cursor = QueryLogin.loginStudent(db, email, password);

        if (!cursor.moveToFirst()) {

            throw new LoginException();

        }

        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String studentEmail = cursor.getString(3);
        int profilePicture = cursor.getInt(5);
        String faculty = cursor.getString(6);
        String academicYear = cursor.getString(7);
        String studentId = cursor.getString(8);
        int uniYear = cursor.getInt(9);
        //Compose the student entity
        student = StudentCreatorFacade.getInstance().getStudent(firstName, lastName, studentEmail, profilePicture, studentId, faculty, academicYear, uniYear);

        cursor.close();
        db.close();

        return student;
    }
}
