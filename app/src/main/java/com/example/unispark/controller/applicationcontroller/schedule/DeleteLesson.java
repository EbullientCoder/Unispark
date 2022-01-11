package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.LessonModel;

public class DeleteLesson {
    //Remove Lesson
    public void deleteLesson(LessonModel lesson){
        //Remove StudentScheduleGUIController from DB
        LessonsDAO.removeLesson(lesson);
    }
}
