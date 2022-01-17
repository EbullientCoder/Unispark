package com.example.unispark.controller.applicationcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.unispark.controller.guicontroller.LoginGUIController;
import com.example.unispark.controller.guicontroller.SplashScreenGUIController;
import com.example.unispark.provaDB.SQLiteFillSampleDB;

public class SplashScreen {

    //Sample DB connection
    public void databaseConnection(Context context){
        SQLiteFillSampleDB fillDB;

        //Initializing Database
        fillDB = new SQLiteFillSampleDB(context);
        fillDB.initDatabase();
    }
}
