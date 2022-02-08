package unispark.controller.appcontroller.schedule;

import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.dao.LessonsDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.model.LessonModel;

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
