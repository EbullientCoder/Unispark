package unispark.controller.guicontroller.clicontroller.professor;



import unispark.controller.appcontroller.exams.ManageExams;
import unispark.engeneeringclasses.bean.BeanStudentSignedToExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class AddExamGrade {

    public void verbalizeExam(BeanLoggedProfessor professor){
        Scanner sc = new Scanner(System.in);
        PrintStream write = System.out;

        //List of Assigned Exams
        List<BeanExam> assignedExamsList;

        //App Controller
        ManageExams assignedExamsAppController = new ManageExams();
        assignedExamsList = assignedExamsAppController.assignedExams(professor);

        //Show Exams
        AssignedExams assignedExams = new AssignedExams();
        assignedExams.assignedExams(professor);

        //Choose the Exam
        write.print("Exam's Position: ");

        int position = 100;
        try {
            position = Integer.parseInt(sc.nextLine());

        } catch (NumberFormatException e) {
            write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
        }


        //Check if position is in the range of available courses
        if (position >= assignedExamsList.size()){
            write.println("\n\n\nERROR: Course not found. Redirecting to menu.\n\n\n");
        }
        else{
            //Get the Student of the selected exams
            List<BeanStudentSignedToExam> studentsSignedToExams = ShowExamStudents.shoExamStudents(assignedExamsList, position);

            //Choose the Student
            write.print("Student Position: ");

            try {
                position = Integer.parseInt(sc.nextLine());

            } catch (NumberFormatException e) {
                write.println("\n\n\nERROR: not an integer. Redirecting to menu.\n\n\n");
            }


            //Check if position is in the range of signed students
            if (position >= studentsSignedToExams.size()){
                write.println("\n\n\nERROR: Student not found. Redirecting to menu.\n\n\n");
            }
            else{
                GetSelectedExam.getSelectedExam(assignedExamsList, position, studentsSignedToExams);
            }
        }
    }
}
