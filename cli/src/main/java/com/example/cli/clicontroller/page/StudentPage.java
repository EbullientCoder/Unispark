package com.example.cli.clicontroller.page;


import com.example.cli.clicontroller.generic.Homeworks;
import com.example.cli.clicontroller.generic.Schedule;
import com.example.cli.clicontroller.generic.UniversityCommunications;
import com.example.cli.clicontroller.student.AvailableCourses;
import com.example.cli.clicontroller.student.Averages;
import com.example.cli.clicontroller.student.BookStudentExam;
import com.example.cli.clicontroller.student.BookedExams;
import com.example.cli.clicontroller.student.FailedExams;
import com.example.cli.clicontroller.student.JoinCourse;
import com.example.cli.clicontroller.student.JoinedCourses;
import com.example.cli.clicontroller.student.LeaveCourse;
import com.example.cli.clicontroller.student.LeaveExam;
import com.example.cli.clicontroller.student.ProfessorCommunications;
import com.example.cli.clicontroller.student.VerbalizedExams;
import com.example.common.bean.student.BeanLoggedStudent;


import java.io.PrintStream;
import java.util.Scanner;

public class StudentPage {

    BeanLoggedStudent student;

    PrintStream write = System.out;

    //Constructor
    public StudentPage(BeanLoggedStudent student){
        this.student = student;
    }


    //Menu
    public void studentPage(){
        Scanner sc = new Scanner(System.in);
        String input = "";

        while(!input.equals("EXIT")){
            //Initialize Text
            presentUser();

            //Operations
            write.println("STUDENT OPERATIONS:\n");

            write.println("- University Communications");
            write.println("- Professor Communications");
            write.println("- Homeworks");
            write.println("- Averages");
            write.println("- Joined Courses");
            write.println("- Available Courses");
            write.println("- Join Course");
            write.println("- Leave Course");
            write.println("- Verbalized Exams");
            write.println("- Failed Exams");
            write.println("- Booked Exams");
            write.println("- Book Exam");
            write.println("- Leave Exam");
            write.println("- Schedule");

            //Select Operation
            input = sc.nextLine();
            studentMenu(input);

            //Continue
            write.println("\n\nContinue (Type anything): ");
            sc.nextLine();
        }
    }

    //Present the User
    private void presentUser(){
        write.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        write.println(student.getId());
        write.println(student.getFirstName() + " " + student.getLastName());
        write.println(student.getFaculty() + "\n\n");
    }

    //Student Menu
    private void studentMenu(String input){

        //Menu
        switch (input){
            case "University Communications":
                UniversityCommunications universityCommunications = new UniversityCommunications();
                universityCommunications.universityCommunications(student);
                break;

            case "Professor Communications":
                ProfessorCommunications professorCommunications = new ProfessorCommunications();
                professorCommunications.professorCommunications(student);
                break;

            case "Homeworks":
                Homeworks homeworks = new Homeworks();
                homeworks.homeworks(student);
                break;

            case "Averages":
                Averages averages = new Averages();
                averages.averages(student);
                break;

            case "Joined Courses":
                JoinedCourses joinedCourses = new JoinedCourses();
                joinedCourses.joinedCourses(student);
                break;

            case "Available Courses":
                AvailableCourses availableCourses = new AvailableCourses();
                availableCourses.availableCourses(student);
                break;

            case "Join Course":
                JoinCourse joinCourse = new JoinCourse();
                joinCourse.joinCourse(student);
                break;

            case "Leave Course":
                LeaveCourse leaveCourse = new LeaveCourse();
                leaveCourse.leaveCourse(student);
                break;

            case "Verbalized Exams":
                VerbalizedExams verbalizedExams = new VerbalizedExams();
                verbalizedExams.verbalizedExams(student);
                break;

            case "Failed Exams":
                FailedExams failedExams = new FailedExams();
                failedExams.failedExams(student);
                break;


            case "Booked Exams":
                BookedExams bookedExams = new BookedExams();
                bookedExams.bookedExams(student);
                break;

            case "Book Exam":
                BookStudentExam bookExam = new BookStudentExam();
                bookExam.bookExam(student);
                break;

            case "Leave Exam":
                LeaveExam leaveExam = new LeaveExam();
                leaveExam.leaveExam(student);
                break;

            case "Schedule":
                Schedule schedule = new Schedule();
                schedule.schedule(student);
                break;

            default:
                write.println("\n\n\nERROR: Operation not found. Redirecting to the menu.\n\n\n");

                break;
        }
    }
}
