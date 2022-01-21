package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.LessonModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetScheduleStudent {
    //Show Schedule
    public List<BeanLesson> getLessons(BeanLoggedStudent student, String day){
        List<LessonModel> lessonsItem;
        List<BeanLesson> lessons = new ArrayList<>();

        try{

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

            for (int i = 0; i < lessonsItem.size(); i++){
                LessonModel lesson = lessonsItem.get(i);
                BeanLesson beanLesson;
                beanLesson = new BeanLesson();
                beanLesson.setDay(lesson.getDay());
                beanLesson.setLessonName(lesson.getLessonName());
                beanLesson.setHour(lesson.getHour());
                lessons.add(beanLesson);
            }

            //Sort the Lessons by their Start Hour
            LessonsSort(lessons);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return lessons;
    }



    //Sort Lessons
    private void LessonsSort(List<BeanLesson> lessons){

        if(lessons !=  null){
            int hour1, hour2;

            for(int i = 0; i < lessons.size(); i++){
                //Gets the first two elements of the string and cast them into Integers

                for(int j = 1; j < (lessons.size() - i); j++){
                    hour1 = Integer.parseInt(lessons.get(j - 1).getHour().substring(0, 2));
                    hour2 = Integer.parseInt(lessons.get(j).getHour().substring(0, 2));

                    if(hour2 < hour1) Collections.swap(lessons, j - 1, j);
                }
            }
        }
    }
}
