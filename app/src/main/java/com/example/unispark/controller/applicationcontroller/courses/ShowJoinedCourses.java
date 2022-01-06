package com.example.unispark.controller.applicationcontroller.courses;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.adapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;


import java.util.Collections;
import java.util.List;

public class ShowJoinedCourses implements
        CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener{
    //Attributes
    Context context;
    //Courses
    CoursesAdapter coursesAdapter;
    List<CourseModel> coursesItem;
    //User Model
    StudentModel student;


    //Constructor
    public ShowJoinedCourses(StudentModel student, Context context){
        this.student = student;
        this.context = context;

        //Courses
        coursesItem = student.getCourses();
        coursesAdapter = null;
    }


    //Communications Adapter
    public CoursesAdapter setCoursesAdapter(){
        coursesAdapter = new CoursesAdapter(coursesItem, this, this, "LEAVE");

        return coursesAdapter;
    }



    //On CoursesClick
    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(context, DetailsCourse.class);
        //Pass Items to the new Activity
        intent.putExtra("Course", coursesItem.get(position));
        //Start New Activity from Outside an Activity
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        context.startActivity(intent);
    }

    //On ButtonLeaveCourse Click
    @Override
    public void onButtonClick(int position) {
        //Remove Course Joined from DB
        boolean leaveCourse = CourseDAO.leaveCourse(student.getId(), coursesItem.get(position).getFullName());

        if(leaveCourse){
            //Remove Course from Student's joined Courses
            coursesItem.remove(position);
            student.setCourses(coursesItem);

            //Notify changed dimension to the Adapter
            coursesAdapter.notifyItemRemoved(position);
        }
        else Toast.makeText(context, "Cannot leave course: EXAM BOOKED", Toast.LENGTH_SHORT).show();
    }
}
