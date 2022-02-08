package unispark.mobile.guicontroller.student;


import unispark.mobile.Session;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.applicationcontroller.schedule.GetScheduleStudent;
import unispark.mobile.view.student.StudentScheduleView;

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
