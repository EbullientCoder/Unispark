package com.example.cli.clicontroller.professor;

import com.example.common.applicationcontroller.exams.VerbalizeExam;
import com.example.common.bean.BeanStudentSignedToExam;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;
import com.example.common.exceptions.ExamNotYetOccured;
import com.example.common.exceptions.GenericException;

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
            e.printStackTrace();
            write.println(e.getMessage());
        } catch (NumberFormatException numberFormatException) {
            write.println("\n\n\nERROR\n\n\n");
        }
    }
}
