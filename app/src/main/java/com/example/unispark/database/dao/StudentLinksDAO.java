package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryStudentLinks;
import com.example.unispark.model.LinkModel;
import com.example.unispark.provaDB.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class StudentLinksDAO {

    private StudentLinksDAO(){}


    public static boolean addStudentLink(LinkModel studentLink, String studentId){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();

        Cursor cursor = QueryStudentLinks.selectStudentLinks(db, studentId);
        if (cursor.moveToFirst()) {
            String linkAddress;
            do {
                linkAddress = cursor.getString(3);
                if (studentLink.getLinkAddress().equals(linkAddress)) return false;
            } while(cursor.moveToNext());
        }

        ContentValues cv = new ContentValues();

        cv.put("studentID", studentId);
        cv.put("name", studentLink.getLinkName());
        cv.put("link", studentLink.getLinkAddress());
        long insert = db.insert("studentslinks", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    public static List<LinkModel> getStudentLinks(String studentID){
        SQLiteDatabase db = SQLiteConnection.getReadableDB();
        Cursor cursor = QueryStudentLinks.selectStudentLinks(db, studentID);
        if (!cursor.moveToFirst()) return null; //throws exception

        List<LinkModel> studentLinks = new ArrayList<>();
        LinkModel link;
        String linkName;
        String linkAddress;

        do {
            linkName = cursor.getString(2);
            linkAddress = cursor.getString(3);
            studentLinks.add(new LinkModel(linkName, linkAddress));
        } while(cursor.moveToNext());

        cursor.close();
        db.close();

        return studentLinks;
    }

    public static boolean removeLink(String name)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("studentslinks","name='" + name + "'",null);
        if (delete > 0) return true;
        return false;

    }
}
