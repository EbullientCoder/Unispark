package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.bean.BeanErrorLessonAlreadyExists;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LessonAlreadyExists;
import com.example.unispark.model.LessonModel;

public class AddLesson {
    //Add Lesson
    public void addLesson(BeanLesson bLesson) throws GenericException, LessonAlreadyExists {
        //Adding Lesson to the DB
        LessonModel lesson = new LessonModel(bLesson.getLessonName(), bLesson.getDay(), bLesson.getHour());
        try {
            LessonsDAO.addLesson(lesson);
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot add lesson, try again");
        }
    }
}
