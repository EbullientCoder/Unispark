package com.example.unispark.controller.applicationcontroller.schedule;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LessonModel;

import java.util.ArrayList;
import java.util.List;

public class GetLessons {
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

            List<LessonModel> lessons = LessonsDAO.getLessons(day, courses);

            for (int j = 0; j < lessons.size(); j++){
                bLessons.add(new BeanLesson(lessons.get(j).getLessonName(), lessons.get(j).getDay(), lessons.get(j).getHour()));
            }
        }

        return bLessons;
    }
}
