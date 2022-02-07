package com.example.cli.clicontroller;



import com.example.cli.clicontroller.generic.Login;
import com.example.cli.clicontroller.page.ProfessorPage;
import com.example.cli.clicontroller.page.StudentPage;
import com.example.cli.clicontroller.page.UniversityPage;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.bean.university.BeanLoggedUniversity;

import java.io.IOException;
import java.io.PrintStream;

public class CLI {

    PrintStream write = System.out;

    //Console View
    public void start() throws IOException {
        write.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");

        //LoginAppController
        Login login = new Login(this);
        login.loginPage();
    }



    //User:
    //Student
    public void studentPage(BeanLoggedStudent student){
        write.println("\n\n\nSTUDENT\n\n\n");

        StudentPage studentPage = new StudentPage(student);
        studentPage.studentPage();
    }

    //Professor
    public void professorPage(BeanLoggedProfessor professor){
        write.println("\n\n\nPROFESSOR\n\n\n");

        ProfessorPage professorPage = new ProfessorPage(this, professor);
        professorPage.professorPage();
    }

    //University
    public void universityPage(BeanLoggedUniversity university){
        write.println("\n\n\nUNIVERSITY\n\n\n");

        UniversityPage universityPage = new UniversityPage(this, university);
        universityPage.universityPage();
    }
}
