package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.LessonModel;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class ShowSchedule {
    //Show Schedule
    public List<LessonModel> setLessons(StudentModel student, String day){
        List<LessonModel> lessonsItem = new ArrayList<>();
        switch(day){
            case "TUESDAY": lessonsItem = LessonsDAO.getLessons("TUESDAY", student.getCourses());
                break;
            case "WEDNESDAY": lessonsItem = LessonsDAO.getLessons("WEDNESDAY", student.getCourses());
                break;
            case "THURSDAY": lessonsItem = LessonsDAO.getLessons("THURSDAY", student.getCourses());
                break;
            case "FRIDAY": lessonsItem = LessonsDAO.getLessons("FRIDAY", student.getCourses());
                break;
            case "SATURDAY": lessonsItem = LessonsDAO.getLessons("SATURDAY", student.getCourses());
                break;
            case "SUNDAY": lessonsItem = LessonsDAO.getLessons("SUNDAY", student.getCourses());
                break;
            default:
                lessonsItem = LessonsDAO.getLessons("MONDAY", student.getCourses());
        }

        return lessonsItem;
    }
}
