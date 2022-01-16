package com.example.unispark.controller.guicontroller.menu;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.bean.login.BeanLoggedUser;
import com.example.unispark.controller.guicontroller.professor.ProfessorExamsGUIController;
import com.example.unispark.controller.guicontroller.professor.ProfessorHomeGUIController;
import com.example.unispark.controller.guicontroller.professor.ProfessorProfileGUIController;
import com.example.unispark.controller.guicontroller.student.StudentLinksGUIController;
import com.example.unispark.controller.guicontroller.student.StudentExamsGUIController;
import com.example.unispark.controller.guicontroller.student.StudentHomeGUIController;
import com.example.unispark.controller.guicontroller.student.StudentProfileGUIController;
import com.example.unispark.controller.guicontroller.student.StudentScheduleGUIController;
import com.example.unispark.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class BottomNavigationMenuGuiController extends AppCompatActivity {

    public Intent nextActivity(BeanLoggedUser user, Context context, int selectedID){
        Intent intent = null;

        switch (selectedID){
            //Student
            case R.id.home: intent = new Intent(context, StudentHomeGUIController.class);
            break;
            case R.id.profile: intent = new Intent(context, StudentProfileGUIController.class);
            break;
            case R.id.exams: intent = new Intent(context, StudentExamsGUIController.class);
            break;
            case R.id.links: intent =  new Intent(context, StudentLinksGUIController.class);
            break;
            case R.id.schedule: intent = new Intent(context, StudentScheduleGUIController.class);
            break;

            //Professor
            case R.id.professor_home: intent = new Intent(context, ProfessorHomeGUIController.class);
            break;
            case R.id.professor_profile: intent = new Intent(context, ProfessorProfileGUIController.class);
            break;
            case R.id.professor_exams: intent = new Intent(context, ProfessorExamsGUIController.class);
            break;
        }
        intent.putExtra("UserObject", user);

        return intent;
    }



}
