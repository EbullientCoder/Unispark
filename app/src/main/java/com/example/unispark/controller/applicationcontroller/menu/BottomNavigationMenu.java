package com.example.unispark.controller.applicationcontroller.menu;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.controller.professor.ProfessorExams;
import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.controller.professor.ProfessorProfile;
import com.example.unispark.controller.guicontroller.student.StudentLinksGUIController;
import com.example.unispark.controller.guicontroller.student.StudentExamsGUIController;
import com.example.unispark.controller.guicontroller.student.StudentHomeGUIController;
import com.example.unispark.controller.guicontroller.student.StudentProfileGUIController;
import com.example.unispark.controller.guicontroller.student.StudentScheduleGUIController;
import com.example.unispark.model.UserModel;
import com.example.unispark.R;



public class BottomNavigationMenu extends AppCompatActivity {

    public Intent nextActivity(UserModel user, Context context, int selectedID){
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
            case R.id.professor_home: intent = new Intent(context, ProfessorHome.class);
            break;
            case R.id.professor_profile: intent = new Intent(context, ProfessorProfile.class);
            break;
            case R.id.professor_exams: intent = new Intent(context, ProfessorExams.class);
            break;
        }
        intent.putExtra("UserObject", user);

        return intent;
    }
}
