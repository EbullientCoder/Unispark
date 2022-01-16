package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.bean.BeanLesson;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.model.LessonModel;

public class DeleteLesson {
    //Remove Lesson
    public void deleteLesson(BeanLesson lesson) throws Exception
    {
        //Remove StudentScheduleGUIController from DB
        LessonModel lessonModel = new LessonModel(lesson.getLessonName(), lesson.getDay(), lesson.getHour());
        try {
            LessonsDAO.removeLesson(lessonModel);
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new Exception("Cannot delete lesson, try again");

        }
    }
}
