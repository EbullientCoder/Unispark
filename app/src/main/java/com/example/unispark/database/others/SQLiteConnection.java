package com.example.unispark.database.others;


import android.database.sqlite.SQLiteDatabase;

public class SQLiteConnection {

    //Get a readable SQLiteDatabase ("unispark.db")
    public static SQLiteDatabase getReadableDB()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/user/0/com.example.uniSpark/databases/unispark.db",null,  SQLiteDatabase.OPEN_READONLY);
        return db;

    }

    //Get a writable SQLiteDatabase ("unispark.db")
    public static SQLiteDatabase getWritableDB()
    {
        SQLiteDatabase db = SQLiteDatabase.openDatabase("/data/user/0/com.example.uniSpark/databases/unispark.db",null,  SQLiteDatabase.OPEN_READWRITE);
        return db;
    }


}
