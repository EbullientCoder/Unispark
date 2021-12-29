package com.example.unispark.controller.applicationcontroller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.controller.student.Home;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.database.dao.StudentDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.UniversityModel;
import com.example.unispark.model.UserModel;

public class Login {
    private Context context;
    private Intent intent;


    //Constructor
    public Login(Context context){
        this.context = context;
        intent = null;
    }

    //Login Methods
    public Intent studendLogin(UserModel user){
        StudentModel student = StudentDAO.selectStudent(user.getEmail(), user.getPassword());

        if(student.getEmail() == null) Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        else{
            intent = new Intent(context, Home.class);
            intent.putExtra("UserObject", student);
        }

        return intent;
    }

    public Intent professorLogin(UserModel user){
        ProfessorModel professor = ProfessorDAO.selectProfessor(user.getEmail(), user.getEmail());

        if(professor.getEmail() == null) Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        else{
            intent = new Intent(context, ProfessorHome.class);
            intent.putExtra("UserObject", professor);
        }

        return intent;
    }

    public Intent universityLogin(UserModel user){
        /*UniversityModel university = UniversityDAO.selectUniversity(user.getEmail(), user.getPassword());

        if(university.getEmail() == null) Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        else{
            intent = new Intent(context, UniversityHome.class);
            intent.putExtra("UserObject", university);
        }*/

        return intent;
    }
}
