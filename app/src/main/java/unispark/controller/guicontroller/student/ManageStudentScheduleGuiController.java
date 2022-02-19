package unispark.controller.guicontroller.student;


import unispark.controller.appcontroller.schedule.ManageSchedule;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.view.student.StudentScheduleView;

import java.time.OffsetDateTime;
import java.util.List;

public class ManageStudentScheduleGuiController extends StudentBaseGuiController {

    private StudentScheduleView scheduleView;

    public ManageStudentScheduleGuiController(Session session, StudentScheduleView scheduleView) {
        super(session, scheduleView);
        this.scheduleView = scheduleView;
    }


    public void showSchedule(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        OffsetDateTime offset = OffsetDateTime.now();

        this.scheduleView.setTxtDay(String.valueOf(offset.getDayOfWeek()));
        this.scheduleView.setTxtDate(offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth());
        ManageSchedule getScheduleStudentAppController = new ManageSchedule();

        List<BeanLesson> beanLessons = getScheduleStudentAppController.getLessons(student, String.valueOf(offset.getDayOfWeek()));
        this.scheduleView.setLessonAdapter(beanLessons);

    }
}
