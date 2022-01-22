package com.example.unispark.controller.guicontroller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.bean.university.BeanLoggedUniversity;
import com.example.unispark.bean.login.BeanUser;
import com.example.unispark.controller.applicationcontroller.Login;
import com.example.unispark.exceptions.WrongUsernameOrPasswordException;
import com.example.unispark.view.professor.ProfessorHomeView;
import com.example.unispark.view.student.StudentHomeView;
import com.example.unispark.view.university.UniversityHomeView;

import java.sql.SQLException;

public class LoginGuiController {

    public void login(Context context, String userSelection, String email, String password){
        String key = "UserObject";
        BeanUser user;
        Login loginAppController = new Login();
        Intent intent;

        //Checking User Credentials
        if (!email.equals("") && !password.equals("")) {
            user = new BeanUser();
            user.setEmail(email);
            user.setPassword(password);

            switch (userSelection) {
                case "STUDENT":
                    //Get Student Model
                    BeanLoggedStudent student = null;
                    try {
                        student = loginAppController.studentLogin(user);
                        intent = new Intent(context, StudentHomeView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(key, student);
                        context.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        getErrorMessage(context, e.getMessage());
                    }
                    break;

                case "PROFESSOR":
                    //Get Professor Model
                    BeanLoggedProfessor professor = null;
                    try {
                        professor = loginAppController.professorLogin(user);
                        intent = new Intent(context, ProfessorHomeView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(key, professor);
                        context.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        getErrorMessage(context, e.getMessage());
                    }
                    break;

                case "UNIVERSITY":
                    //Get University Model
                    BeanLoggedUniversity university = null;
                    try {
                        university = loginAppController.universityLogin(user);
                        intent = new Intent(context, UniversityHomeView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(key, university);
                        context.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        getErrorMessage(context, e.getMessage());
                    }
                    break;
                default:
                    Toast.makeText(context, "Select a user", Toast.LENGTH_SHORT).show();
            }
        }
        else getEmptyFieldsMessage(context);

    }

    private void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private void getEmptyFieldsMessage(Context context){
        Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show();
    }
}