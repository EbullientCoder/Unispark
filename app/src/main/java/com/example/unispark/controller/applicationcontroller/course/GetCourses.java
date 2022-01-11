package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;

import java.util.List;

public class GetCourses {
    //Get Courses
    public List<CourseModel> getCourses(String faculty){
        List<CourseModel> courses = CourseDAO.selectAvailableCourses(faculty, 3, null);

        return courses;
    }
}
