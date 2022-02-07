package com.example.cli.clicontroller.professor;


import com.example.common.applicationcontroller.exams.ManageExams;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.professor.BeanLoggedProfessor;

import java.util.List;

public class AssignedExams {

    public void assignedExams(BeanLoggedProfessor professor){
        //List of Assigned Exams
        List<BeanExam> assignedExams;

        //App Controller
        ManageExams assignedExamsAppController = new ManageExams();
        assignedExams = assignedExamsAppController.assignedExams(professor);


        //Show assigned Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Assigned Exams --------------------\n\n");

        for(int i = 0; i < assignedExams.size(); i++){
            BeanBookExam aExam = (BeanBookExam) assignedExams.get(i);

            bld.append(aExam.getName() + "\n");
            bld.append(aExam.getDate() + "\n");
            bld.append("Classroom: " + aExam.getClassroom() + " Building: " + aExam.getBuilding() + "\n");
            bld.append("Position: " + Integer.toString(i) + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
