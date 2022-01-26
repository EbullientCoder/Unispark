package com.example.unispark.controller.guicontroller.student;


import android.content.Intent;


import com.example.unispark.Session;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.average.CalculateAverage;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.exceptions.CourseNeverJoined;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.view.details.DetailsCourseView;
import com.example.unispark.view.student.StudentProfileView;
import com.example.unispark.view.student.fragment.JoinCourseView;


import java.util.List;


public class ManageStudentProfileGuiController extends StudentBaseGuiController {

    private StudentProfileView profileView;
    private List<BeanCourse> beanCourses;


    public ManageStudentProfileGuiController(Session session, StudentProfileView profileView) {
        super(session, profileView);
        this.profileView = profileView;
    }



    public void showProfile(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        this.profileView.setTxtFullName(student.getFirstName() + " " + student.getLastName());
        this.profileView.setImgProfile(student.getProfilePicture());
    }



    public void showAverages(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        float aAverage = this.calculateArithmeticAverage(student);
        int cAverage = this.calculateGraphicArithmeticAverage(aAverage);
        float wAverage = this.calculateWeightedAverage(student);
        int wCaverage = this.calculateGraphicWeightedAverage(wAverage);
        this.profileView.setAverage(String.format("%.02f", aAverage), cAverage, String.format("%.02f", wAverage), wCaverage);

    }



    private float calculateArithmeticAverage(BeanLoggedStudent student){
        float average;
        CalculateAverage calculateAverageController = new CalculateAverage();
        average = calculateAverageController.arithmeticAverage(student);

        return average;
    }



    private int calculateGraphicArithmeticAverage(float average){
        int cAverage;
        CalculateAverage calculateAverageController = new CalculateAverage();
        cAverage = calculateAverageController.graphicArithmeticAverage(average);

        return cAverage;

    }



    private float calculateWeightedAverage(BeanLoggedStudent student){

        float average;
        CalculateAverage calculateAverageController = new CalculateAverage();
        average = calculateAverageController.weightedAverage(student);

        return average;
    }



    //Circular Weighted Average
    private int calculateGraphicWeightedAverage(float average){
        int circularAverage;
        CalculateAverage calculateAverageController = new CalculateAverage();
        circularAverage = calculateAverageController.graphicWeightedAverage(average);

        return circularAverage;
    }



    public void showJoinCourses(){
        JoinCourseView joinCourseFragment = new JoinCourseView(this.getSession(), this.getBeanCourses(), this.profileView.getCoursesAdapter());
        joinCourseFragment.show(this.profileView.getSupportFragmentManager(), "Search Course");
    }



    public void showCourses(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        ManageCourses getCoursesController = new ManageCourses();
        this.beanCourses = getCoursesController.getCourses(student);
        this.profileView.setCoursesAdapter(this.getBeanCourses());

    }



    public void showCourseDetails(int position){
        Intent intent = new Intent(this.getProfileView(), DetailsCourseView.class);
        intent.putExtra("Course", this.beanCourses.get(position));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.profileView.startActivity(intent);
    }



    public void leaveCourse(int position){

        ManageCourses leaveCourseAppController = new ManageCourses();
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
         try {
            leaveCourseAppController.leaveCourse(student, this.beanCourses.get(position), position);
            this.beanCourses.remove(position);
            this.profileView.notifyDataChanged(position);
        } catch (GenericException | ExamBookedException | CourseNeverJoined e) {
            e.printStackTrace();
            this.profileView.setErrorMessage(e.getMessage());
        }
    }


    public void setBeanCourses(List<BeanCourse> beanCourses) {
        this.beanCourses = beanCourses;
    }

    public List<BeanCourse> getBeanCourses() {
        return beanCourses;
    }

    public StudentProfileView getProfileView() {
        return profileView;
    }
}
