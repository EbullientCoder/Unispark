package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class SearchCourse {
    //Search Course
    public List<CourseModel> setCourses(StudentModel student){
        List<CourseModel> coursesItem;
        List<CourseModel> courses = student.getCourses();
        List<String> courseNames = null;
        if(courses != null){
            courseNames = new ArrayList<>(courses.size());
            for (int i = 0; i < courses.size(); i++) courseNames.add(courses.get(i).getFullName());
        }
        coursesItem = CourseDAO.selectAvailableCourses(student.getFaculty(), student.getUniYear(), courseNames);

        return coursesItem;
    }
}
