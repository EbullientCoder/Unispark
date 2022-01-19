package com.example.unispark.controller.applicationcontroller;

import android.content.Context;

import com.example.unispark.database.others.provaDB.SQLiteFillSampleDB;

public class SplashScreen {

    //Sample DB connection
    public void databaseConnection(Context context){
        SQLiteFillSampleDB fillDB;

        //Initializing Database
        fillDB = new SQLiteFillSampleDB(context);
        fillDB.initDatabase();
    }
}
