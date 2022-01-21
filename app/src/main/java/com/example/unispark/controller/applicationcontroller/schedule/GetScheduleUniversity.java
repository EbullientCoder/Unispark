package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.bean.course.BeanCourse;
import com.example.unispark.bean.lesson.BeanLesson;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LessonModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetScheduleUniversity {
    //Get Lessons
    public List<BeanLesson> getLessons(String day, List<BeanCourse> bCourses){
        List<BeanLesson> bLessons = new ArrayList<>();

        if (!bCourses.isEmpty()){

            List<CourseModel> courses = new ArrayList<>();
            for (int i = 0; i < bCourses.size(); i++){
                courses.add(new CourseModel(bCourses.get(i).getId(),
                        bCourses.get(i).getShortName(),
                        bCourses.get(i).getFullName(),
                        bCourses.get(i).getCourseYear(),
                        bCourses.get(i).getCfu(),
                        bCourses.get(i).getSession(),
                        bCourses.get(i).getLink(),
                        bCourses.get(i).getFaculty(),
                        bCourses.get(i).getUniYear()));
            }

            List<LessonModel> lessons = null;
            try {
                lessons = LessonsDAO.getLessons(day, courses);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            for (int j = 0; j < lessons.size(); j++){
                bLessons.add(new BeanLesson(lessons.get(j).getLessonName(), lessons.get(j).getDay(), lessons.get(j).getHour()));
            }
        }

        //Sort the Lessons by their Start Hour
        LessonsSort(bLessons);

        return bLessons;
    }



    //Sort Lessons
    public void LessonsSort(List<BeanLesson> lessons){

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
