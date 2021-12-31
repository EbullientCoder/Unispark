package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.model.UniversityModel;

import java.util.List;

public class UniversityDAO {

    private UniversityDAO(){}

    public static UniversityModel selectUniversity(String email, String password){

        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryLogin.loginUniversity(db, email, password);
        if(!cursor.moveToFirst()){
            //throws exception

            //Momentaneo
            UniversityModel universityError = new UniversityModel(null, null, 0, null, null);
            return universityError;
        }

        String name = cursor.getString(1);
        String streetAddress = cursor.getString(2);
        int profilePicture = cursor.getInt(3);
        String universityEmail = cursor.getString(4);
        List<String> faculties = FacultyDAO.getUniversityFaculties();

        return new UniversityModel(name, universityEmail, profilePicture, streetAddress, faculties);

    }
}
