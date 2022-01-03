package com.example.unispark.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.LoginGUIController;
import com.example.unispark.provaDB.SQLiteFillSampleDB;

public class SplashScreen extends AppCompatActivity {
    //Database
    //SQLiteFillSampleDB fillDB;
    //Timeout
    int timeout = 2000; //2000

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_splash_screen);

        //Make Status Bar Translucent
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        //Remove Button Bottom Bar
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);


        //Initializing Database
        //fillDB = new SQLiteFillSampleDB(getApplicationContext());
        //fillDB.initDatabase();


        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreen.this, LoginGUIController.class);
            startActivity(i);
            finish();
        }, timeout);
    }
}