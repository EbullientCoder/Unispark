package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCommunications;
import com.example.unispark.database.query.QueryProfessor;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommunicationsDAO {

    private CommunicationsDAO(){}

    public static void addUniversityCommunication(UniversityCommunicationModel communication) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        ContentValues cv = new ContentValues();
        cv.put("image", communication.getBackground());
        cv.put("title", communication.getTitle());
        cv.put("date", communication.getDate());
        cv.put("communication", communication.getCommunication());
        cv.put("faculty", communication.getFaculty());

        long insert = db.insert("universitycommunications", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);

    }

    public static void addProfessorCommunication(ProfessorCommunicationModel communication) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        ContentValues cv = new ContentValues();
        cv.put("shortname", communication.getShortCourseName());
        cv.put("date", communication.getDate());
        cv.put("title", communication.getType());
        cv.put("communication", communication.getCommunication());

        long insert = db.insert("professorcommunications", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);
    }

    //Look for University communications marked by faculty
    public static List<UniversityCommunicationModel> getUniversityCommunications(String facultyName) throws SQLiteException
    {
        List<UniversityCommunicationModel> communicationsList = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryCommunications.selectFacultyCommunications(db, facultyName);

        if (cursor.moveToFirst()) {
            //Communication Model
            UniversityCommunicationModel communicationModel;
            //Attributes
            int background;
            String title;
            String date;
            String communication;
            String faculty;

            do {
                background = cursor.getInt(1);
                title = cursor.getString(2);
                date = cursor.getString(3);
                communication = cursor.getString(4);
                faculty = cursor.getString(5);
                //Add CommunicationModel to List
                communicationModel = new UniversityCommunicationModel(background, title, date, communication, faculty);
                communicationsList.add(communicationModel);
            } while (cursor.moveToNext());
            //Reverse communications order
            Collections.reverse(communicationsList);
        }

        cursor.close();
        db.close();

        return communicationsList;
    }


    public static List<ProfessorCommunicationModel> getCourseCommunications(String courseShortName, String courseFullName) throws SQLiteException
    {
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        List<ProfessorCommunicationModel> communicationsList = new ArrayList<>();

        Cursor cursor = QueryCommunications.selectCourseCommunications(db, courseShortName);

        if (cursor.moveToFirst()){
            //Communication Model
            ProfessorCommunicationModel communicationModel;
            //Attributes
            int profilePhoto;
            String shortName;
            String professorName;
            String date;
            String type;
            String communication;
            //Look for Professor name and profile image marked by professorId
            Cursor professorId;
            Cursor professorImage;
            Cursor name;

            do {
                shortName = cursor.getString(1);
                date = cursor.getString(2);
                type = cursor.getString(3);
                communication = cursor.getString(4);

                professorId = QueryProfessor.getProfessorId(db, shortName);
                professorId.moveToFirst();
                professorImage = QueryProfessor.getProfessorProfileImage(db, professorId.getInt(0));
                name = QueryProfessor.getProfessorName(db, professorId.getInt(0));
                professorImage.moveToFirst();
                name.moveToFirst();

                professorName = name.getString(0) + " " + name.getString(1);
                profilePhoto = professorImage.getInt(0);

                //Add CommunicationModel to List
                communicationModel = new ProfessorCommunicationModel(profilePhoto, shortName, courseFullName, professorName, date, type, communication);
                communicationsList.add(communicationModel);

            } while(cursor.moveToNext());
            //Reverse communications order
            Collections.reverse(communicationsList);
        }

        //close both the cursor and the db when done.
        cursor.close();
        db.close();

        return communicationsList;
    }

}
