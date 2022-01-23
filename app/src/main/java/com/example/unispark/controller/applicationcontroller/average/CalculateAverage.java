package com.example.unispark.controller.applicationcontroller.average;

import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.List;

public class CalculateAverage {

    //Arithmetic Average
    public float arithmeticAverage(BeanLoggedStudent student){
        float average = 0;
        try {
            student.setVerbalizedExams(ExamsDAO.getVerbalizedExams(student.getId()));

            //Calculating the Average if the Student has Verbalized StudentExamsGUIController
            if(student.getVerbalizedExams() != null){
                for(int i = 0; i < student.getVerbalizedExams().size(); i++) average += Double.parseDouble(student.getVerbalizedExams().get(i).getResult());

                average = average / student.getVerbalizedExams().size();
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return average;
    }

    //Circular Arithmetic Average
    public int graphicArithmeticAverage(float average){

        float circularAverage = 0;

        //Calculating the Average if the Student has Verbalized StudentExamsGUIController
        if(average != 0) circularAverage = (average * 100 / 35);

        return (int) circularAverage;
    }



    //Weighted Average
    public float weightedAverage(BeanLoggedStudent student){
        float average = 0;
        float cfu = 0;

        try {
            student.setVerbalizedExams(ExamsDAO.getVerbalizedExams(student.getId()));
            List<VerbalizedExamModel> exams =  student.getVerbalizedExams();
            //Calculating the Weighted Average if the Student has Verbalized StudentExamsGUIController
            if(exams != null){
                for(int i = 0; i < exams.size(); i++){
                    average += (Double.parseDouble(exams.get(i).getResult()) * Double.parseDouble(exams.get(i).getCfu()));
                    cfu += Double.parseDouble(exams.get(i).getCfu());
                }

                average = average / cfu;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return average;
    }

    //Circular Weighted Average
    public int graphicWeightedAverage(float average){
        float circularAverage = 0;

        //Calculating the Average if the Student has Verbalized StudentExamsGUIController
        if(average != 0) circularAverage = (average * 100 / 36);

        return (int) circularAverage;
    }
}
