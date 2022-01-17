package com.example.unispark.controller.guicontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.unispark.R;
import com.example.unispark.controller.applicationcontroller.SplashScreen;
import com.example.unispark.provaDB.SQLiteFillSampleDB;

public class SplashScreenGUIController extends AppCompatActivity {

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

        //Initializing Sample DB
        SplashScreen splashScreenAppController = new SplashScreen();
        //splashScreenAppController.databaseConnection(getApplicationContext());



        //Delayed Launch of Login (2 seconds)
        new Handler().postDelayed(() -> {
            Intent i = new Intent(SplashScreenGUIController.this, LoginGUIController.class);
            startActivity(i);
            finish();
        }, 2000);
    }
}