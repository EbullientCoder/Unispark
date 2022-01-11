package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LessonModel;

import java.util.List;

public class GetLessons {
    //Get Lessons
    public List<LessonModel> getLessons(String day, List<CourseModel> courses){
        List<LessonModel> schedulesItem = LessonsDAO.getLessons(day, courses);

        return schedulesItem;
    }
}
