package unispark.controller.appcontroller.schedule;

import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.LessonsDAO;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.model.CourseModel;
import unispark.model.LessonModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ManageSchedule {
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

    public List<BeanLesson> getLessons(BeanLoggedStudent student, String day){
        List<LessonModel> lessonsItem;
        List<BeanLesson> lessons = new ArrayList<>();

        try{

            lessonsItem = LessonsDAO.getLessons(day, student.getCourses());

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
            this.lessonsSort(lessons);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return lessons;
    }



    //Sort Lessons
    public void lessonsSort(List<BeanLesson> lessons){

        if(lessons !=  null){
            int hour1;
            int hour2;

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

    //Get Lessons
    public List<BeanLesson> getLessons(String day, List<BeanCourse> bCourses){
        List<BeanLesson> bLessons = new ArrayList<>();

        if (!bCourses.isEmpty()){

            List<CourseModel> courses = new ArrayList<>();
            for (int i = 0; i < bCourses.size(); i++){
                CourseModel courseModel = new CourseModel(Integer.parseInt(bCourses.get(i).getId()),
                        bCourses.get(i).getCourseYear(),
                        bCourses.get(i).getCfu(),
                        bCourses.get(i).getSession(),
                        bCourses.get(i).getLink(),
                        bCourses.get(i).getFaculty(),
                        bCourses.get(i).getUniYear());

                courseModel.setShortName(bCourses.get(i).getShortName());
                courseModel.setFullName(bCourses.get(i).getFullName());

                courses.add(courseModel);
            }

            List<LessonModel> lessons = null;
            try {
                lessons = LessonsDAO.getLessons(day, courses);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            for (int j = 0; j < lessons.size(); j++){
                BeanLesson beanLesson;
                beanLesson = new BeanLesson();
                beanLesson.setHour(lessons.get(j).getHour());
                beanLesson.setLessonName(lessons.get(j).getLessonName());
                beanLesson.setDay(lessons.get(j).getDay());
                bLessons.add(beanLesson);
            }
        }

        //Sort the Lessons by their Start Hour
        this.lessonsSort(bLessons);

        return bLessons;
    }



}
