package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;

public class JoinCourse {
    //Join Course
    public void joinCourse(StudentModel student, CourseModel course){
        CourseDAO.joinCourse(student.getId(), course.getFullName());

    }
}
