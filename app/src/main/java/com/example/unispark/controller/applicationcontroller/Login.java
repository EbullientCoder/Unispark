package com.example.unispark.controller.applicationcontroller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.unispark.controller.guicontroller.student.StudentHomeGUIController;
import com.example.unispark.controller.university.UniversityHome;
import com.example.unispark.bean.login.BeanUser;
import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.database.dao.StudentDAO;
import com.example.unispark.database.dao.UniversityDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.UniversityModel;

public class Login {
    private Context context;
    private Intent intent;


    //Constructor
    public Login(Context context){
        this.context = context;
        intent = null;
    }

    //Login Methods
    public Intent studentLogin(BeanUser user){
        StudentModel student = StudentDAO.selectStudent(user.getEmail(), user.getPassword());

        if(student.getEmail() == null) Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        else{
            intent = new Intent(context, StudentHomeGUIController.class);
            intent.putExtra("UserObject", student);
        }

        return intent;
    }

    public Intent professorLogin(BeanUser user){
        ProfessorModel professor = ProfessorDAO.selectProfessor(user.getEmail(), user.getPassword());

        if(professor.getEmail() == null) Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        else{
            intent = new Intent(context, ProfessorHome.class);
            intent.putExtra("UserObject", professor);
        }

        return intent;
    }

    public Intent universityLogin(BeanUser user){
        UniversityModel university = UniversityDAO.selectUniversity(user.getEmail(), user.getPassword());

        if(university.getEmail() == null) Toast.makeText(context, "Wrong Credentials", Toast.LENGTH_SHORT).show();
        else{
            intent = new Intent(context, UniversityHome.class);
            intent.putExtra("UserObject", university);
        }

        return intent;
    }
}
