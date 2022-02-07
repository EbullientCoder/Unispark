package com.example.common.applicationcontroller.schedule;

import com.example.common.bean.BeanLesson;
import com.example.common.database.dao.LessonsDAO;
import com.example.common.exceptions.GenericException;
import com.example.common.exceptions.LessonAlreadyExists;
import com.example.common.model.LessonModel;

import java.sql.SQLException;

public class AddLesson {
    //Add Lesson
    public void addLesson(BeanLesson bLesson) throws GenericException, LessonAlreadyExists {
        //Adding Lesson to the DB
        LessonModel lesson = new LessonModel(bLesson.getLessonName(), bLesson.getDay(), bLesson.getHour());
        try {
            LessonsDAO.addLesson(lesson);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }
}
