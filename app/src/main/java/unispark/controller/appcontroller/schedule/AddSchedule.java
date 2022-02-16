package unispark.controller.appcontroller.schedule;

import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.dao.LessonsDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.LessonAlreadyExists;
import unispark.model.LessonModel;

import java.sql.SQLException;

public class AddSchedule {
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
