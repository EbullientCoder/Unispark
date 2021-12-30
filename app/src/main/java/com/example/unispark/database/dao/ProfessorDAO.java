package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.facade.ProfessorCreatorFacade;
import com.example.unispark.model.ProfessorModel;

public class ProfessorDAO {

    private ProfessorDAO(){}

    //Get a ProfessorModel using the email and password
    public static ProfessorModel selectProfessor(String email, String password) //throws exception
    {
        //Get a readable SQLiteDatabase
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryLogin.loginProfessor(db, email, password);

        if (!cursor.moveToFirst()){
            //throw exception

            //Momentaneo
            ProfessorModel professorerror = new ProfessorModel(null, null, null, 0, 0, null, null, null, null);
            return professorerror;
        }

        int professorId = cursor.getInt(0);
        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String emailProfessor = cursor.getString(3);
        String website = cursor.getString(5);
        int profilePicture = cursor.getInt(6);
        String faculty = cursor.getString(7);

        ProfessorModel professor = ProfessorCreatorFacade.getInstance().getProfessor(firstName, lastName, emailProfessor, profilePicture, professorId, faculty, website);

        cursor.close();
        db.close();

        return professor;
    }
}
