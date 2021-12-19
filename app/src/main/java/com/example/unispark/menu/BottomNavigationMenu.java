package com.example.unispark.menu;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.controller.professor.ProfessorExams;
import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.controller.professor.ProfessorProfile;
import com.example.unispark.controller.student.Links;
import com.example.unispark.controller.student.Exams;
import com.example.unispark.controller.student.Home;
import com.example.unispark.controller.student.Profile;
import com.example.unispark.controller.student.Schedule;
import com.example.unispark.model.UserModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.unispark.R;



public class BottomNavigationMenu extends AppCompatActivity {
    public static void visualSetting(BottomNavigationView bottomNavigationView, int id){
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set Home button
        bottomNavigationView.setSelectedItemId(id);
    }

    public static Intent functionalSetting(Context context, int id, UserModel user){
        Intent intent = null;

        switch (id){
            //Student
            case R.id.home: intent = new Intent(context, Home.class);
                break;
            case R.id.profile: intent = new Intent(context, Profile.class);
            break;
            case R.id.exams: intent = new Intent(context, Exams.class);
            break;
            case R.id.links: intent =  new Intent(context, Links.class);
            break;
            case R.id.schedule: intent = new Intent(context, Schedule.class);
            break;

            //Professor
            case R.id.professor_home: intent = new Intent(context, ProfessorHome.class);
            break;
            case R.id.professor_profile: intent = new Intent(context, ProfessorProfile.class);
            break;
            case R.id.professor_exams: intent = new Intent(context, ProfessorExams.class);
            break;

            //University
            //case
        }
        intent.putExtra("UserObject", user);

        return intent;
    }
}
