package com.example.unispark.controller.applicationcontroller.average;

import android.content.Context;

import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.List;

public class CalculateAverage {
    //Attributes
    Context context;
    //StudentExamsGUIController
    List<VerbalizedExamModel> exams;
    //User Model
    StudentModel student;


    //Constructor
    public CalculateAverage(StudentModel student, Context context){
        this.student = student;
        this.context = context;

        //Verbalized StudentExamsGUIController
        exams = student.getVerbalizedExams();
    }


    //Arithmetic Average
    public float arithmeticAverage(){
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

        return (int)circularAverage;
    }


    //Weighted Average
    public float weightedAverage(){
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
