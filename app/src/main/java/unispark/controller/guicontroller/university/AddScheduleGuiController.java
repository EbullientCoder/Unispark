package unispark.controller.guicontroller.university;

import unispark.controller.appcontroller.schedule.ManageSchedule;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.courses.BeanCoursesNames;
import unispark.engeneeringclasses.bean.university.BeanLoggedUniversity;
import unispark.controller.appcontroller.course.ManageCourses;
import unispark.controller.appcontroller.schedule.AddSchedule;
import unispark.controller.guicontroller.UserBaseGuiController;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.LessonAlreadyExists;
import unispark.view.university.fragment.AddScheduleView;

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
            AddSchedule addLessonAppController = new AddSchedule();

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
                ManageSchedule getScheduleUniversity =  new ManageSchedule();
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
