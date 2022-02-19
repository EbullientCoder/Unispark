package unispark.controller.guicontroller;


import android.content.Intent;


import unispark.controller.appcontroller.LoginApp;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;
import unispark.engeneeringclasses.bean.login.BeanUser;
import unispark.engeneeringclasses.exceptions.WrongUsernameOrPasswordException;
import unispark.view.LoginView;
import unispark.view.professor.ProfessorHomeView;
import unispark.view.student.StudentHomeView;
import unispark.view.university.UniversityHomeView;

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
        LoginApp loginAppController = new LoginApp();
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