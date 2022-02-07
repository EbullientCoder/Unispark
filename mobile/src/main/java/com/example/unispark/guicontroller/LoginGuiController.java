package com.example.unispark.guicontroller;


import android.content.Intent;


import com.example.unispark.Session;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.bean.university.BeanLoggedUniversity;
import com.example.common.bean.login.BeanUser;
import com.example.common.applicationcontroller.LoginAppController;
import com.example.common.exceptions.WrongUsernameOrPasswordException;
import com.example.unispark.view.LoginView;
import com.example.unispark.view.professor.ProfessorHomeView;
import com.example.unispark.view.student.StudentHomeView;
import com.example.unispark.view.university.UniversityHomeView;

import java.sql.SQLException;

public class LoginGuiController extends UserBaseGuiController{

    private String key = "session";
    private LoginView loginView;

    public LoginGuiController(Session session, LoginView loginView) {
        super(session);
        this.loginView = loginView;
    }

    public void login(String userSelection, String email, String password){
        BeanUser user;
        LoginAppController loginAppController = new LoginAppController();
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
                        session.setUser(student);
                        intent = new Intent(getLoginView(), StudentHomeView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(getKey(), session);
                        loginView.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        loginView.setErrorMessage(e.getMessage());
                    }
                    break;

                case "PROFESSOR":
                    //Get Professor Model
                    BeanLoggedProfessor professor = null;
                    try {
                        professor = loginAppController.professorLogin(user);
                        session.setUser(professor);
                        intent = new Intent(getLoginView(), ProfessorHomeView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(getKey(), session);
                        loginView.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        loginView.setErrorMessage(e.getMessage());
                    }
                    break;

                case "UNIVERSITY":
                    //Get University Model
                    BeanLoggedUniversity university = null;
                    try {
                        university = loginAppController.universityLogin(user);
                        session.setUser(university);
                        intent = new Intent(getLoginView(), UniversityHomeView.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.putExtra(getKey(), session);
                        loginView.startActivity(intent);
                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        loginView.setErrorMessage(e.getMessage());
                    }
                    break;
                default:
                    loginView.setErrorMessage("Select user");
            }
        }
        else loginView.setErrorMessage("All fields are required");

    }


    public void showUserSelector(){
        String[] users = {"STUDENT","PROFESSOR","UNIVERSITY"};
        this.loginView.setAdapterItems(users);
    }

    public LoginView getLoginView() {
        return loginView;
    }

    public String getKey() {
        return key;
    }
}