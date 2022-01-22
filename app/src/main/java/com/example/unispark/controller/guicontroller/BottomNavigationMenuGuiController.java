package com.example.unispark.controller.guicontroller;

import android.content.Context;
import android.content.Intent;

import com.example.unispark.R;
import com.example.unispark.bean.login.BeanLoggedUser;
import com.example.unispark.view.professor.ProfessorExamsView;
import com.example.unispark.view.professor.ProfessorHomeView;
import com.example.unispark.view.professor.ProfessorProfileView;
import com.example.unispark.view.student.StudentExamsView;
import com.example.unispark.view.student.StudentHomeView;
import com.example.unispark.view.student.StudentLinksView;
import com.example.unispark.view.student.StudentProfileView;
import com.example.unispark.view.student.StudentScheduleView;


public class BottomNavigationMenuGuiController {

    public void selectNextView(BeanLoggedUser user, Context context, int selectedID){
        Intent intent = null;

        switch (selectedID){
            //Student
            case R.id.home: intent = new Intent(context, StudentHomeView.class);
            break;
            case R.id.profile: intent = new Intent(context, StudentProfileView.class);
            break;
            case R.id.exams: intent = new Intent(context, StudentExamsView.class);
            break;
            case R.id.links: intent =  new Intent(context, StudentLinksView.class);
            break;
            case R.id.schedule: intent = new Intent(context, StudentScheduleView.class);
            break;

            //Professor
            case R.id.professor_home: intent = new Intent(context, ProfessorHomeView.class);
            break;
            case R.id.professor_profile: intent = new Intent(context, ProfessorProfileView.class);
            break;
            case R.id.professor_exams: intent = new Intent(context, ProfessorExamsView.class);
            break;
            default: //Do nothing
        }
        intent.putExtra("UserObject", user);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



}
