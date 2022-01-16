package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryStudentLinks;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.LinkAlreadyExists;
import com.example.unispark.model.LinkModel;

import java.util.ArrayList;
import java.util.List;

public class StudentLinksDAO {

    private StudentLinksDAO(){}


    public static void addStudentLink(LinkModel studentLink, String studentId) throws LinkAlreadyExists, SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        Cursor cursor = QueryStudentLinks.selectStudentLinks(db, studentId);
        if (cursor.moveToFirst()) {
            String linkAddress;
            do {
                linkAddress = cursor.getString(3);
                if (studentLink.getLinkAddress().equals(linkAddress)) throw new LinkAlreadyExists("Link already exists");
            } while(cursor.moveToNext());
        }

        ContentValues cv = new ContentValues();

        cv.put("studentID", studentId);
        cv.put("name", studentLink.getLinkName());
        cv.put("link", studentLink.getLinkAddress());
        long insert = db.insert("studentslinks", null, cv);

        if (insert == -1) throw new DatabaseOperationError(0);
    }

    public static List<LinkModel> getStudentLinks(String studentID) throws SQLiteException
    {
        List<LinkModel> studentLinks = new ArrayList<>();
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryStudentLinks.selectStudentLinks(db, studentID);

        if (cursor.moveToFirst()) {
            LinkModel link;
            String linkName;
            String linkAddress;
            do {
                linkName = cursor.getString(2);
                linkAddress = cursor.getString(3);
                studentLinks.add(new LinkModel(linkName, linkAddress));
            } while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return studentLinks;
    }

    public static void removeLink(String linkName) throws SQLiteException, DatabaseOperationError
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("studentslinks","link='" + linkName + "'",null);
        if (!(delete > 0)) throw new DatabaseOperationError(1);
    }
}
