package com.example.unispark.controller.guicontroller.professor;

import android.content.Context;
import android.content.Intent;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanExam;
import com.example.unispark.bean.BeanExamType;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.exams.ShowExams;
import com.example.unispark.view.details.DetailsVerbalizeExamsView;

import java.util.List;

public class ManageExamsGuiController extends BaseProfessorGuiController{



    public List<BeanExamType> showExams(BeanLoggedProfessor professor){
        List<BeanExamType> exams;

        ShowExams showExamsAppController = new ShowExams();
        exams = showExamsAppController.assignedExams(professor);

        return exams;

    }

    public void showVerbalizeExam(Context context, BeanExam exam){

        Intent intent = new Intent(context, DetailsVerbalizeExamsView.class);
        intent.putExtra("Exam", (BeanBookExam) exam);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
