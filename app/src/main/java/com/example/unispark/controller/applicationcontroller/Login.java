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

    //Login Methods
    public StudentModel studentLogin(BeanUser user){
        StudentModel student = StudentDAO.selectStudent(user.getEmail(), user.getPassword());

        return student;
    }

    public ProfessorModel professorLogin(BeanUser user){
        ProfessorModel professor = ProfessorDAO.selectProfessor(user.getEmail(), user.getPassword());

        return professor;
    }

    public UniversityModel universityLogin(BeanUser user){
        UniversityModel university = UniversityDAO.selectUniversity(user.getEmail(), user.getPassword());

        return university;
    }
}
