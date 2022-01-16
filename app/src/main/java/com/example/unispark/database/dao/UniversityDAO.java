package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.model.UniversityModel;

import java.util.List;

import javax.security.auth.login.LoginException;

public class UniversityDAO {

    private UniversityDAO(){}

    public static UniversityModel selectUniversity(String email, String password) throws LoginException
    {

        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        UniversityModel university;

        Cursor cursor = QueryLogin.loginUniversity(db, email, password);

        if (!cursor.moveToFirst()) {

            throw new LoginException();

        }

        String name = cursor.getString(1);
        String streetAddress = cursor.getString(2);
        int profilePicture = cursor.getInt(3);
        String universityEmail = cursor.getString(4);
        List<String> faculties = FacultyDAO.getUniversityFaculties();

        university = new UniversityModel(name, universityEmail, profilePicture, streetAddress, faculties);

        cursor.close();
        db.close();

        return university;
    }
}
