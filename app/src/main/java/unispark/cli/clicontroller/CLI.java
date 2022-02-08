package unispark.cli.clicontroller;



import unispark.cli.clicontroller.generic.Login;
import unispark.cli.clicontroller.page.ProfessorPage;
import unispark.cli.clicontroller.page.StudentPage;
import unispark.cli.clicontroller.page.UniversityPage;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;

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
