package com.example.cli.clicontroller.professor;


import com.example.common.applicationcontroller.exams.VerbalizeExam;
import com.example.common.bean.BeanStudentSignedToExam;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;

import java.io.PrintStream;
import java.util.List;

public class ShowExamStudents {

    private ShowExamStudents(){}

    public static List<BeanStudentSignedToExam> shoExamStudents(List<BeanExam> assignedExamsList, int position){
        PrintStream write = System.out;

        //Get the Student of the selected exams
        List<BeanStudentSignedToExam> studentsSignedToExams;

        //Get the selected Exam
        BeanBookExam verbalizeExam = (BeanBookExam) assignedExamsList.get(position);

        //AppController: Get the Students
        VerbalizeExam bookedStudentAppController = new VerbalizeExam();
        studentsSignedToExams = bookedStudentAppController.getStudentsVerbalizeExam(verbalizeExam);

        //Show Students
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Signed Students --------------------\n\n");

        for(int i = 0; i < studentsSignedToExams.size(); i++){
            bld.append(studentsSignedToExams.get(i).getId() + "\n");
            bld.append(studentsSignedToExams.get(i).getFullName() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        write.println(bld.toString());

        return studentsSignedToExams;
    }
}
