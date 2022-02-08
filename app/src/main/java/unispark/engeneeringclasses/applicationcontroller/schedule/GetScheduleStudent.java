package unispark.engeneeringclasses.applicationcontroller.schedule;

import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.dao.LessonsDAO;
import unispark.engeneeringclasses.model.LessonModel;

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
    private void lessonsSort(List<BeanLesson> lessons){

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
}
