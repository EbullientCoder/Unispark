package com.example.unispark.controller.applicationcontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.unispark.controller.guicontroller.LoginGUIController;
import com.example.unispark.controller.guicontroller.SplashScreenGUIController;
import com.example.unispark.provaDB.SQLiteFillSampleDB;

public class SplashScreen {
    private Context context;
    private Intent intent;


    //Constructor
    public SplashScreen(Context context){
        this.context = context;
        intent = null;
    }

    //Methods
    //Sample DB connection
    public void databaseConnection(){
        SQLiteFillSampleDB fillDB;

        //Initializing Database
        fillDB = new SQLiteFillSampleDB(context);
        fillDB.initDatabase();
    }
}
