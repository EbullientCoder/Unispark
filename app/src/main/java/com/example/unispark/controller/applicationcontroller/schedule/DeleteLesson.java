package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.bean.lesson.BeanLesson;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.LessonModel;

import java.sql.SQLException;

public class DeleteLesson {
    //Remove Lesson
    public void deleteLesson(BeanLesson lesson) throws GenericException {
        //Remove StudentScheduleGUIController from DB
        LessonModel lessonModel = new LessonModel(lesson.getLessonName(), lesson.getDay(), lesson.getHour());

        try {
            LessonsDAO.removeLesson(lessonModel);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }

    }
}
