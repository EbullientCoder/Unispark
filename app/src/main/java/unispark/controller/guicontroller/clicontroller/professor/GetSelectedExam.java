package unispark.controller.guicontroller.clicontroller.professor;

import unispark.controller.appcontroller.exams.VerbalizeExam;
import unispark.engeneeringclasses.bean.BeanStudentSignedToExam;
import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.exceptions.ExamNotYetOccured;
import unispark.engeneeringclasses.exceptions.GenericException;

import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class GetSelectedExam {

    private GetSelectedExam(){}

    public static void getSelectedExam(List<BeanExam> assignedExamsList, int position, List<BeanStudentSignedToExam> studentsSignedToExams){
        PrintStream write = System.out;
        Scanner sc = new Scanner(System.in);

        //Get the selected Exam
        BeanBookExam verbalizeExam = (BeanBookExam) assignedExamsList.get(position);

        write.print("Result: ");
        String examResult = sc.nextLine();

        //Application Controller: Verbalize Exam
        VerbalizeExam verbalizeExamAppController = new VerbalizeExam();
        try {
            double doubleResult = Double.parseDouble(examResult);
            if (doubleResult < 0 || doubleResult > 30) {
                write.println("\n\n\nERROR: result must be from 0 to 30\n\n\n");
            } else {
                verbalizeExamAppController.verbalizeExam(verbalizeExam, studentsSignedToExams.get(position), examResult);

                write.println("\n\n\nEXAM VERBALIZED\n\n\n");
            }
        } catch (ExamNotYetOccured | GenericException e) {
            write.println(e.getMessage());
        } catch (NumberFormatException numberFormatException) {
            write.println("\n\n\nERROR\n\n\n");
        }
    }
}
