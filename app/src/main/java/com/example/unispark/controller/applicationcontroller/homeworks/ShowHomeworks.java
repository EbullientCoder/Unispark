package com.example.unispark.controller.applicationcontroller.homeworks;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.controller.details.DetailsHomework;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class ShowHomeworks{
    //Student
    public List<HomeworkModel> setStudentHomeworks(StudentModel student){
        List<HomeworkModel> homeworksItem;
        homeworksItem = HomeworkDAO.getStudentHomework(student.getId());

        if(homeworksItem != null) return homeworksItem;
        else return new ArrayList<>();
    }

    //Professor
    public List<HomeworkModel> setProfessorHomeworks(ProfessorModel professor){
        List<HomeworkModel> homeworksItem;
        homeworksItem = HomeworkDAO.getAssignedHomework(professor.getId());

        if(homeworksItem != null) return homeworksItem;
        else return new ArrayList<>();
    }
}
