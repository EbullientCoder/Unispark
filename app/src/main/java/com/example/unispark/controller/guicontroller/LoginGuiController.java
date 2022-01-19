package com.example.unispark.controller.guicontroller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.bean.login.BeanLoggedUniversity;
import com.example.unispark.bean.login.BeanUser;
import com.example.unispark.controller.applicationcontroller.Login;
import com.example.unispark.exceptions.WrongUsernameOrPasswordException;
import com.example.unispark.view.professor.ProfessorHomeView;
import com.example.unispark.view.student.StudentHomeView;
import com.example.unispark.view.university.UniversityHomeView;

public class LoginGuiController {

    public void login(Context context, String userSelection, String email, String password){

        BeanUser user;
        Login loginAppController = new Login();
        Intent intent;

        //Checking User Credentials
        if (!userSelection.equals("") && !email.equals("") && !password.equals("")) {
            user = new BeanUser(email, password);

            switch (userSelection) {
                case "STUDENT":
                    //Get Student Model
                    BeanLoggedStudent student = null;
                    try {
                        student = loginAppController.studentLogin(user);
                        intent = new Intent(context, StudentHomeView.class);
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("UserObject", student);
                        context.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException e) {
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
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("UserObject", professor);
                        context.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException e) {
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
                        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra("UserObject", university);
                        context.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException e) {
                        e.printStackTrace();
                        getErrorMessage(context, e.getMessage());
                    }
                    break;
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