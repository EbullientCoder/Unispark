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

import java.util.List;

public class ShowHomeworks implements HomeworksAdapter.OnHomeworkBtnClickListener{
    //Attributes
    Context context;
    //Homeworks
    HomeworksAdapter homeworksAdapter;
    List<HomeworkModel> homeworksItem;
    //User Model
    StudentModel student;
    ProfessorModel professor;


    //Constructor
    //Student
    public ShowHomeworks(StudentModel student, Context context){
        this.student = student;
        this.context = context;

        //Communications
        homeworksItem = HomeworkDAO.getStudentHomework(student.getId());
        homeworksAdapter = null;
    }

    //Professor
    public ShowHomeworks(ProfessorModel professor, Context context){
        this.professor = professor;
        this.context = context;

        //Communications
        homeworksItem = HomeworkDAO.getAssignedHomework(professor.getId());
        homeworksAdapter = null;
    }


    //Student Homeworks Adapter
    public HomeworksAdapter setStudentHomeworksAdapter() {
        homeworksAdapter = new HomeworksAdapter(homeworksItem, this, "STUDENT");

        return homeworksAdapter;
    }

    //Professor Homeworks Adapter
    public HomeworksAdapter setProfessorHomeworksAdapter() {
        homeworksAdapter = new HomeworksAdapter(homeworksItem, this, "PROFESSOR");

        return homeworksAdapter;
    }



    //On HomeworksClick
    @Override
    public void onBtnClick(int position) {
        Intent intent = new Intent(context, DetailsHomework.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", homeworksItem.get(position));
        intent.putExtra("StudentHomeGUIController", "StudentHome");
        //Start New Activity from Outside an Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}
