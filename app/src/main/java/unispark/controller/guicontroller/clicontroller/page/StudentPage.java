package unispark.controller.guicontroller.clicontroller.page;


import unispark.controller.guicontroller.clicontroller.generic.Homeworks;
import unispark.controller.guicontroller.clicontroller.generic.Schedule;
import unispark.controller.guicontroller.clicontroller.generic.UniversityCommunications;
import unispark.controller.guicontroller.clicontroller.student.AvailableCourses;
import unispark.controller.guicontroller.clicontroller.student.Averages;
import unispark.controller.guicontroller.clicontroller.student.BookStudentExam;
import unispark.controller.guicontroller.clicontroller.student.BookedExams;
import unispark.controller.guicontroller.clicontroller.student.FailedExams;
import unispark.controller.guicontroller.clicontroller.student.JoinCourse;
import unispark.controller.guicontroller.clicontroller.student.JoinedCourses;
import unispark.controller.guicontroller.clicontroller.student.LeaveCourse;
import unispark.controller.guicontroller.clicontroller.student.LeaveExam;
import unispark.controller.guicontroller.clicontroller.student.ProfessorCommunications;
import unispark.controller.guicontroller.clicontroller.student.VerbalizedExams;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;


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
