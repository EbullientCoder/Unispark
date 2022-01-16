package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.database.query.QueryProfessor;
import com.example.unispark.facade.ProfessorCreatorFacade;
import com.example.unispark.model.ProfessorModel;

import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ProfessorDAO {

    private ProfessorDAO(){}

    //Get a ProfessorModel using the email and password
    public static ProfessorModel selectProfessor(String email, String password) throws SQLiteException, LoginException
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        ProfessorModel professor;

        Cursor cursor = QueryLogin.loginProfessor(db, email, password);

        if (!cursor.moveToFirst()) {

            throw new LoginException();

        }

        int professorId = cursor.getInt(0);
        String firstName = cursor.getString(1);
        String lastName = cursor.getString(2);
        String emailProfessor = cursor.getString(3);
        String website = cursor.getString(5);
        int profilePicture = cursor.getInt(6);
        String faculty = cursor.getString(7);

        professor = ProfessorCreatorFacade.getInstance().getProfessor(firstName, lastName, emailProfessor, profilePicture, professorId, faculty, website);

        cursor.close();
        db.close();
        return professor;
    }

    //Get professor marked by faculty of studentId
    public static List<ProfessorModel> getFacultyProfessors(String faculty) throws SQLiteException
    {
        List<ProfessorModel> professors = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryProfessor.selectProfessorFaculty(db, faculty);

        if (cursor.moveToFirst()) {
            ProfessorModel professor;
            int professorId;
            String firstName;
            String lastName;
            String emailProfessor;
            String website;
            int profilePicture;
            String professorFaculty;

            do {
                professorId = cursor.getInt(0);
                firstName = cursor.getString(1);
                lastName = cursor.getString(2);
                emailProfessor = cursor.getString(3);
                website = cursor.getString(5);
                profilePicture = cursor.getInt(6);
                professorFaculty = cursor.getString(7);

                professor = ProfessorCreatorFacade.getInstance().getProfessor(firstName, lastName, emailProfessor, profilePicture, professorId, professorFaculty, website);
                professors.add(professor);
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return professors;
    }

}
