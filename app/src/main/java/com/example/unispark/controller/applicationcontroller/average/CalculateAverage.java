package com.example.unispark.controller.applicationcontroller.average;

import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.List;

public class CalculateAverage {

    //Arithmetic Average
    public float arithmeticAverage(BeanLoggedStudent student){
        List<VerbalizedExamModel> exams = null;
        try {
            exams = ExamsDAO.getVerbalizedExams(student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        float average = 0;

        //Calculating the Average if the Student has Verbalized StudentExamsGUIController
        if(exams != null){
            for(int i = 0; i < exams.size(); i++) average += Double.parseDouble(exams.get(i).getResult());

            average = average / exams.size();
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

        List<VerbalizedExamModel> exams = null;
        try {
            exams = ExamsDAO.getVerbalizedExams(student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        float average = 0;
        float CFU = 0;

        //Calculating the Weighted Average if the Student has Verbalized StudentExamsGUIController
        if(exams != null){
            for(int i = 0; i < exams.size(); i++){
                average += (Double.parseDouble(exams.get(i).getResult()) * Double.parseDouble(exams.get(i).getCFU()));
                CFU += Double.parseDouble(exams.get(i).getCFU());
            }

            average = average / CFU;
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
