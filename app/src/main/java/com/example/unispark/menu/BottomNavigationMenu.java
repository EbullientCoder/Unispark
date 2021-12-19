package com.example.unispark.menu;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.controller.student.Links;
import com.example.unispark.controller.student.Exams;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.unispark.controller.student.Home;
import com.example.unispark.controller.student.Profile;
import com.example.unispark.R;
import com.example.unispark.controller.student.Schedule;


public class BottomNavigationMenu extends AppCompatActivity {
    public static void visualSetting(BottomNavigationView bottomNavigationView, int id){
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set Home button
        bottomNavigationView.setSelectedItemId(id);
    }

    public static Intent functionalSetting(Context context, int id){
        switch (id){
            case R.id.profile: return new Intent(context, Profile.class);
            case R.id.exams: return new Intent(context, Exams.class);
            case R.id.links: return new Intent(context, Links.class);
            case R.id.schedule: return new Intent(context, Schedule.class);
            default: return new Intent(context, Home.class);
        }
    }
}
