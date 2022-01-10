package com.example.unispark.controller.applicationcontroller.communications;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.adapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsProfCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;

import java.util.ArrayList;
import java.util.List;

public class ShowProfCommunications{

    public List<ProfessorCommunicationModel> setProfessorCommunications(StudentModel student){
        List<ProfessorCommunicationModel> profCommunicationsItem = null;
        List<String> courseShortnames = new ArrayList<>();
        List<String> courseFullNames = new ArrayList<>();

        //Getting Courses Short and Full names
        if(student.getCourses() != null){
            for(int i = 0; i < student.getCourses().size(); i++) {
                courseShortnames.add(student.getCourses().get(i).getShortName());
                courseFullNames.add(student.getCourses().get(i).getFullName());
            }

            profCommunicationsItem = CommunicationsDAO.getAllCoursesCommunications(courseShortnames, courseFullNames);
        }

        return profCommunicationsItem;
    }
}
