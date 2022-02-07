package com.example.common.applicationcontroller.schedule;

import com.example.common.bean.BeanLesson;
import com.example.common.database.dao.LessonsDAO;
import com.example.common.exceptions.GenericException;
import com.example.common.model.LessonModel;

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
