package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;

public class LeaveCourse {
    //Remove Course Joined from DB
    public boolean leaveCourse(StudentModel student, CourseModel course){
        boolean leaveCourse = CourseDAO.leaveCourse(student.getId(), course.getFullName());

        return leaveCourse;
    }
}
