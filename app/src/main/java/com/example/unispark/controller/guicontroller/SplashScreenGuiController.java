package com.example.unispark.controller.guicontroller;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.StrictMode;

import com.example.unispark.view.LoginView;

public class SplashScreenGuiController {


    public void showLoginView(Context context){
        //SQLiteFillSampleDB db = new SQLiteFillSampleDB(context);
        //db.initDatabase();
        setPolicy();
        new Handler().postDelayed(() -> {
            Intent i = new Intent(context, LoginView.class);
            i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }, 2000);



    }

    private void setPolicy(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }
}
