package com.example.cli.clicontroller.student;


import com.example.common.applicationcontroller.exams.ManageExams;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.exams.BeanVerbalizeExam;
import com.example.common.bean.student.BeanLoggedStudent;

import java.util.List;

public class VerbalizedExams {

    public void verbalizedExams(BeanLoggedStudent student){
        //Verbalized Exams List
        List<BeanExam> verbalizedExams;

        //Application Controller
        ManageExams verbalizedExamsAppController = new ManageExams();
        verbalizedExams = verbalizedExamsAppController.verbalizedExams(student);

        //Show Verbalized Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Verbalized Exams --------------------\n\n");

        for(int i = 0; i < verbalizedExams.size(); i++){
            BeanVerbalizeExam vExam = (BeanVerbalizeExam) verbalizedExams.get(i);

            bld.append(vExam.getName() + "\n");
            bld.append(vExam.getDate() + "\n");
            bld.append("CFU: " + vExam.getCfu() + "\n");
            bld.append("Result: " + vExam.getResult() + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
