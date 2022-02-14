package unispark.controller.guicontroller.clicontroller.student;


import unispark.controller.appcontroller.exams.BookExam;
import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.exceptions.ExamAlreadyVerbalized;
import unispark.engeneeringclasses.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class BookStudentExam {

    PrintStream write = System.out;


    public void bookExam(BeanLoggedStudent student){
        Scanner sc = new Scanner(System.in);

        //Upcoming Exams
        List<BeanExam> upcomingExamsList;

        //Application Controller
        //Generate Booking Exams
        BookExam bookExamAppController = new BookExam();
        upcomingExamsList = bookExamAppController.generateBookingExams(student);


        //Show Available Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Upcoming Exams --------------------\n\n");

        for(int i = 0; i < upcomingExamsList.size(); i++){
            BeanBookExam upExam = (BeanBookExam) upcomingExamsList.get(i);

            bld.append(upExam.getName() + "\n");
            bld.append(upExam.getDate() + "\n");
            bld.append("CFU: " + upExam.getCfu() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        System.out.println(bld.toString());



        //Choose the position of the exam you want to book
        write.println("Position of the Exam you want to BOOK: ");

        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }

        //Check if position is in the range of available courses
        if (position >= upcomingExamsList.size()){
            write.println("\n\n\nERROR: Exam not found. Redirecting to menu.\n\n\n");

        }

        else{

            try {
                bookExamAppController.bookExam(student, (BeanBookExam) upcomingExamsList.get(position));

                write.println("\n\n\nEXAM BOOKED\n\n\n");
            } catch (ExamAlreadyVerbalized | GenericException e) {
                e.printStackTrace();
                write.println("\n\n\n" + e.getMessage()+ "\n\n\n");

            }
        }
    }
}
