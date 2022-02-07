package com.example.unispark.guicontroller.student;


import com.example.unispark.Session;
import com.example.common.bean.BeanLesson;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.applicationcontroller.schedule.GetScheduleStudent;
import com.example.unispark.view.student.StudentScheduleView;

import java.time.OffsetDateTime;
import java.util.List;

public class ShowScheduleGuiController extends StudentBaseGuiController {

    private StudentScheduleView scheduleView;

    public ShowScheduleGuiController(Session session, StudentScheduleView scheduleView) {
        super(session, scheduleView);
        this.scheduleView = scheduleView;
    }


    public void showSchedule(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        OffsetDateTime offset = OffsetDateTime.now();

        this.scheduleView.setTxtDay(String.valueOf(offset.getDayOfWeek()));
        this.scheduleView.setTxtDate(offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth());
        GetScheduleStudent getScheduleStudentAppController = new GetScheduleStudent();

        List<BeanLesson> beanLessons = getScheduleStudentAppController.getLessons(student, String.valueOf(offset.getDayOfWeek()));
        this.scheduleView.setLessonAdapter(beanLessons);

    }
}
