package com.example.unispark.controller.applicationcontroller.exams;

import android.content.Context;

import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class ShowExams {
    //Attributes
    Context context;
    //Exams
    ExamAdapter examAdapter;
    List<ExamItem> examsItem;
    //User Model
    StudentModel student;


    //Constructor
    public ShowExams(StudentModel student, Context context){
        this.student = student;
        this.context = context;

        //Exams
        examAdapter = null;
        examsItem = new ArrayList<>();
    }


    //Exams Adapter

}
