package com.example.cli.clicontroller.student;



import com.example.common.applicationcontroller.exams.ManageExams;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.exams.BeanVerbalizeExam;
import com.example.common.bean.student.BeanLoggedStudent;

import java.util.List;

public class FailedExams {

    public void failedExams(BeanLoggedStudent student){
        //Failed Exams List
        List<BeanExam> failedExams;

        //Application Controller
        ManageExams failedExamsAppController = new ManageExams();
        failedExams = failedExamsAppController.failedExams(student);

        //Show Failed Exams
        StringBuilder bld = new StringBuilder();
        bld.append("-------------------- Failed Exams --------------------\n\n");

        for(int i = 0; i < failedExams.size(); i++){
            BeanVerbalizeExam fExam = (BeanVerbalizeExam) failedExams.get(i);

            bld.append(fExam.getName() + "\n");
            bld.append(fExam.getDate() + "\n");
            bld.append("CFU: " + fExam.getCfu() + "\n");
            bld.append("Result: " + fExam.getResult() + "\n\n");
        }
        System.out.println(bld.toString());
    }
}
