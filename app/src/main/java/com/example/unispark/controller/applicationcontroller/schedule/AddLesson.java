package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.LessonModel;

public class AddLesson {
    //Add Lesson
    public void addLesson(LessonModel lesson){
        //Adding Lesson to the DB
        LessonsDAO.addLesson(lesson);
    }
}
