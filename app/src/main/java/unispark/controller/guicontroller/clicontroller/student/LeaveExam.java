package unispark.controller.guicontroller.clicontroller.student;


import unispark.controller.appcontroller.exams.ManageExams;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class LeaveExam {

    public void leaveExam(BeanLoggedStudent student){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //Booked Exams
        List<BeanExam> bookedExamsList;

        //Application Controller
        ManageExams showBookedExamsAppController = new ManageExams();
        bookedExamsList = showBookedExamsAppController.getBookedExams(student);

        //Show Booked Exams
        BookedExams bookedExams = new BookedExams();
        bookedExams.bookedExams(student);

        //Choose the position of the exam you want to leave
        write.println("Position of the Exam you want to LEAVE: ");

        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        //Check if position is in the range of available courses
        if (position >= bookedExamsList.size())
            write.println("\n\n\nERROR: Exam not found. Redirecting to menu.\n\n\n");
        else {
            //Application Controller
            ManageExams leaveExamAppController = new ManageExams();
            try {
                leaveExamAppController.removeExam(student, position);

                write.println("\n\n\nEXAM LEFT\n\n\n");
            } catch (GenericException e) {
                e.printStackTrace();
            }
        }
    }
}
