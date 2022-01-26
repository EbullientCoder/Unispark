package com.example.unispark.controller.guicontroller.university;

import com.example.unispark.Session;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.courses.BeanCoursesNames;
import com.example.unispark.bean.university.BeanLoggedUniversity;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.controller.applicationcontroller.schedule.AddLesson;
import com.example.unispark.controller.applicationcontroller.schedule.GetScheduleUniversity;
import com.example.unispark.controller.guicontroller.UserBaseGuiController;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LessonAlreadyExists;
import com.example.unispark.view.university.fragment.AddScheduleView;

import java.util.List;

public class AddScheduleGuiController extends UserBaseGuiController {


    private AddScheduleView addScheduleView;
    private List<BeanLesson> beanLessons;
    private int indexLesson;

    private String[] daysOfLesson = {"MONDAY", "TUESDAY", "WEDNESDAY", "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"};
    private String[] hoursOfLesson = {"08:30 - 09:15", "09:30 - 10:15", "10:30 - 11:15", "11:30 - 12:15", "12:30 - 13:15", "14:00 - 14:45", "15:00 - 15:45", "16:00 - 16:45", "17:00 - 17:45"};

    public AddScheduleGuiController(Session session, List<BeanLesson> beanLessons, int indexLesson, AddScheduleView addScheduleView) {
        super(session);
        this.beanLessons = beanLessons;
        this.indexLesson = indexLesson;
        this.addScheduleView= addScheduleView;
    }


    public void showCoursesNames(){
        BeanLoggedUniversity university = (BeanLoggedUniversity) this.session.getUser();
        BeanCoursesNames bCoursesNames;
        ManageCourses getCoursesAppController = new ManageCourses();
        bCoursesNames = getCoursesAppController.getCoursesNamesByFaculty(university.getFaculties());
        this.addScheduleView.setAdapterItemsCourse(bCoursesNames.getCourses());
    }

    public void showDays(){
        this.addScheduleView.setAdapterItemsDay(this.getDaysOfLesson());
    }

    public void showHours(){
        this.addScheduleView.setAdapterItemsHour(this.getHoursOfLesson());
    }



    public void addSchedule(String courseSelection, String daySelection, String hourSelection){

        if (courseSelection.equals("") || daySelection.equals("") || hourSelection.equals("")){
            this.addScheduleView.setMessage("All fields required");
        }

        else{
            //Creating new Lesson
            BeanLesson bLesson;
            bLesson = new BeanLesson();
            bLesson.setLessonName(courseSelection);
            bLesson.setDay(daySelection);
            bLesson.setHour(hourSelection);

            //Application Controller: Add Lesson
            AddLesson addLessonAppController = new AddLesson();

            try {
                addLessonAppController.addLesson(bLesson);
                this.addScheduleView.setMessage("Schedule updated");

                if (this.daysOfLesson[this.indexLesson].equals(daySelection)){
                    this.beanLessons.add(0, bLesson);
                }
                //Notifying the Lessons Adapter
                this.addScheduleView.notifyDataChanged();

                //App Controller: GetScheduleUniversity
                //Sorting the Lessons
                GetScheduleUniversity getScheduleUniversity =  new GetScheduleUniversity();
                getScheduleUniversity.lessonsSort(this.getBeanLessons());

                this.addScheduleView.dismiss();
            } catch (GenericException genericException) {

                genericException.printStackTrace();
                this.addScheduleView.setMessage(genericException.getMessage());

            } catch (LessonAlreadyExists lessonAlreadyExists) {
                lessonAlreadyExists.printStackTrace();
                this.addScheduleView.setMessage(lessonAlreadyExists.getMess().getMessage());

            }
        }
    }


    public List<BeanLesson> getBeanLessons() {
        return beanLessons;
    }

    public String[] getDaysOfLesson() {
        return daysOfLesson;
    }

    public String[] getHoursOfLesson() {
        return hoursOfLesson;
    }
}
