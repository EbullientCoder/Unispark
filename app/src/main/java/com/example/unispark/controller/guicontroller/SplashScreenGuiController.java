package com.example.unispark.controller.guicontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;

import com.example.unispark.database.others.MySqlConnect;
import com.example.unispark.view.LoginView;

import java.sql.Connection;

public class SplashScreenGuiController {


    public void showLoginView(Context context){

        new Handler().postDelayed(() -> {
            Intent i = new Intent(context, LoginView.class);
            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }, 2000);



    }


}
