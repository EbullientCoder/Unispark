package com.example.cli.clicontroller.generic;


import com.example.cli.clicontroller.CLI;
import com.example.common.applicationcontroller.LoginAppController;
import com.example.common.bean.login.BeanUser;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.bean.university.BeanLoggedUniversity;
import com.example.common.exceptions.WrongUsernameOrPasswordException;

import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Scanner;

public class Login {


    CLI console;
    BeanUser user;

    PrintStream write = System.out;

    private static final String WRONG = "\n\n\nERROR: WRONG CREDENTIALS\n\n\n";

    public Login(CLI console){
        this.console = console;
    }

    public void loginPage(){
        Scanner sc = new Scanner(System.in);
        String type;
        String email;
        String password;

        Boolean looping = true;

        //Set Title
        while(Boolean.TRUE.equals(looping)){
            write.println("LOGIN\n\n");
            write.print("User: ");

            //Get user type
            type = sc.nextLine();
            while(!type.equals("student") && !type.equals("professor") && !type.equals("university")){
                write.println("\n\n\nERROR:");
                write.println("Type: student - professor - university");
                write.print("User: ");

                type = sc.nextLine();
            }

            //Get Email
            write.print("Email: ");
            email = sc.nextLine();

            //Get Password
            write.print("Password: ");
            password = sc.nextLine();

            //Set Logged User
            user = new BeanUser();
            user.setEmail(email);
            user.setPassword(password);


            //App Controller
            LoginAppController loginAppController = new LoginAppController();
            switch(type){
                case "student":
                    BeanLoggedStudent student;

                    try{
                        student = loginAppController.studentLogin(user);
                        looping = false;

                        console.studentPage(student);
                    }
                    catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        write.println(WRONG);
                    }

                    break;


                case "professor":
                    //Get Professor Model
                    BeanLoggedProfessor professor;

                    try {
                        professor = loginAppController.professorLogin(user);
                        looping = false;

                        console.professorPage(professor);

                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        write.println(WRONG);
                    }

                    break;


                case "university":
                    //Get University Model
                    BeanLoggedUniversity university;

                    try {
                        university = loginAppController.universityLogin(user);
                        looping = false;

                        console.universityPage(university);

                    } catch (WrongUsernameOrPasswordException | SQLException e) {
                        e.printStackTrace();
                        write.println(WRONG);
                    }

                    break;

                default:
                    write.println("NO USER");

                    break;
            }
        }
    }
}
