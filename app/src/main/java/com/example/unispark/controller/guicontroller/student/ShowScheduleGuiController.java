package com.example.unispark.controller.guicontroller.student;

import com.example.unispark.bean.lesson.BeanLesson;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.schedule.GetScheduleStudent;
import com.example.unispark.controller.guicontroller.BottomNavigationMenuGuiController;

import java.util.List;

public class ShowScheduleGuiController extends BottomNavigationMenuGuiController {

    public List<BeanLesson> showSchedule(BeanLoggedStudent student, String day){
        List<BeanLesson> lessons;
        GetScheduleStudent getScheduleStudentAppController = new GetScheduleStudent();

        lessons = getScheduleStudentAppController.getLessons(student,day);

        return lessons;
    }
}
