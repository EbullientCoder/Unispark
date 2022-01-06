package com.example.unispark.controller.applicationcontroller.communications;

import android.content.Context;
import android.content.Intent;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.adapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsProfCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;

import java.util.ArrayList;
import java.util.List;

public class ShowProfCommunications implements ProfCommunicationsAdapter.OnProfComClickListener{
    //Attributes
    Context context;
    //Communications
    ProfCommunicationsAdapter communicationsAdapter;
    List<ProfessorCommunicationModel> profCommunicationsItem;
    //User Model
    StudentModel student;


    //Constructor
    //Student
    public ShowProfCommunications(StudentModel student, Context context){
        this.student = student;
        this.context = context;

        //Communications
        profCommunicationsItem = null;
        communicationsAdapter = null;
    }


    //Communications Adapter
    public ProfCommunicationsAdapter setCommunicationsAdapter(){
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
        communicationsAdapter = new ProfCommunicationsAdapter(profCommunicationsItem, this);

        return communicationsAdapter;
    }



    //On ProfessorCommunicationsClick
    @Override
    public void onProfClick(int position) {
        Intent intent = new Intent(context, DetailsProfCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", profCommunicationsItem.get(position));
        //Start New Activity from Outside an Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }
}
