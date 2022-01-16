package com.example.unispark.database.others;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class SQLiteConnection {

    //Get a readable SQLiteDatabase ("unispark.db")
    public static SQLiteDatabase getReadableDB() throws SQLiteException
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/user/0/com.example.uniSpark/databases/unispark.db",null,  SQLiteDatabase.OPEN_READONLY);
        return db;

    }

    //Get a writable SQLiteDatabase ("unispark.db")
    public static SQLiteDatabase getWritableDB() throws SQLiteException
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/user/0/com.example.uniSpark/databases/unispark.db",null,  SQLiteDatabase.OPEN_READWRITE);
        return db;
    }


}
