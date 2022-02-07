package com.example.unispark.guicontroller.university;


import android.content.Intent;

import com.example.unispark.Session;
import com.example.common.bean.BeanLesson;
import com.example.common.bean.communications.BeanUniCommunication;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.university.BeanLoggedUniversity;
import com.example.common.applicationcontroller.communications.ShowCommunications;
import com.example.common.applicationcontroller.course.ManageCourses;
import com.example.common.applicationcontroller.schedule.DeleteLesson;
import com.example.common.applicationcontroller.schedule.GetScheduleUniversity;
import com.example.unispark.guicontroller.UserBaseGuiController;
import com.example.unispark.view.details.DetailsUniCommunicationView;
import com.example.unispark.view.university.UniversityHomeView;
import com.example.unispark.view.university.fragment.AddScheduleView;
import com.example.unispark.view.university.fragment.AddUniCommunicationView;

import java.util.List;

public class ManageUniHomeGuiController extends UserBaseGuiController {


    private UniversityHomeView universityHomeView;

    private boolean isOpen;
    private int lessonIndex;
    private List<BeanUniCommunication> beanUniCommunications;
    private List<BeanLesson> beanLessons;


    public ManageUniHomeGuiController(Session session, UniversityHomeView universityHomeView) {
        super(session);
        this.universityHomeView = universityHomeView;
        this.lessonIndex = 0;
    }



    //Open Button
    public void expandButton(){
        if(!this.isOpen){
            //Show Buttons

            this.universityHomeView.setBtnCommunication();
            this.universityHomeView.setBtnSchedule();
            //Expand Floating Button
            this.universityHomeView.setTxtCommunication();
            this.universityHomeView.setTxtSchedule();
            //Rotate
            this.universityHomeView.setBtnAdd();
            this.isOpen = true;
        }

        else{
            //Hide Buttons
            this.universityHomeView.unSetBtnCommunication();
            this.universityHomeView.unSetBtnSchedule();

            //Hide text
            this.universityHomeView.unSetTxtCommunication();
            this.universityHomeView.unSetTxtSchedule();

            //Rotate
            this.universityHomeView.unSetBtnAdd();

            this.isOpen = false;
        }

    }


    public void showAddCommunication(){

        AddUniCommunicationView fragment = new AddUniCommunicationView(this.session, this.getBeanUniCommunications(), this.universityHomeView.getUniCommunicationsAdapter());
        fragment.show(this.universityHomeView.getSupportFragmentManager(), "AddUniCommunication");
    }




    public void showCommunications(){
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        this.beanUniCommunications = uniCommunicationsAppController.showUniversityCommunications();
        this.universityHomeView.setUniCommunicationsAdapter(this.getBeanUniCommunications());
    }




    public void showDetailsCommunication(int position){
        Intent intent = new Intent(this.getUniversityHomeView(), DetailsUniCommunicationView.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", this.beanUniCommunications.get(position));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.universityHomeView.startActivity(intent);
    }



    private List<BeanCourse> getCourses(List<String> faculties){
        List<BeanCourse> courses;

        ManageCourses getCoursesAppController = new ManageCourses();
        courses = getCoursesAppController.getFacultyCourses(faculties);


        return courses;
    }





    private List<BeanLesson> getLessons(String day, List<BeanCourse> courses){
        List<BeanLesson> lessons;

        GetScheduleUniversity getScheduleUniversityAppController = new GetScheduleUniversity();
        lessons = getScheduleUniversityAppController.getLessons(day, courses);

        return lessons;
    }





    public void deleteLesson(int position){

        //Application Controller
        DeleteLesson deleteLessonAppController = new DeleteLesson();
        try {
            deleteLessonAppController.deleteLesson(this.beanLessons.get(position));
            //Show Removed StudentSchedule
            this.beanLessons.remove(position);
            this.universityHomeView.notifyDataChanged(position);
        } catch (Exception e) {
            e.printStackTrace();
            this.universityHomeView.setMessage(e.getMessage());
        }
    }




    //Show StudentScheduleGUIController
    public void showSchedule(){
        BeanLoggedUniversity university = (BeanLoggedUniversity) this.session.getUser();
        String day = null;

        switch (this.lessonIndex){
            case 1: day = "TUESDAY";
                break;
            case 2: day = "WEDNESDAY";
                break;
            case 3: day = "THURSDAY";
                break;
            case 4: day = "FRIDAY";
                break;
            default: day = "MONDAY";
        }

        this.getUniversityHomeView().setTxtScheduleTitle("SCHEDULE: " + day);

        //Lessons
        //Get Courses
        List<BeanCourse> courses;
        courses = this.getCourses(university.getFaculties());
        //Get Lessons
        List<BeanLesson> lessons;
        lessons = this.getLessons(day, courses);
        this.beanLessons = lessons;
        this.universityHomeView.setLessonAdapter(this.getBeanLessons());

    }



    public void correctIndex(){

        if(this.lessonIndex == 4) this.lessonIndex = 0;
        else this.lessonIndex++;

    }



    public void showAddSchedule(){
        AddScheduleView fragment = new AddScheduleView(this.session, this.universityHomeView.getLessonAdapter(), this.getBeanLessons(), this.getLessonIndex());
        fragment.show(this.universityHomeView.getSupportFragmentManager(), "AddSchedule");


    }




    public List<BeanUniCommunication> getBeanUniCommunications() {
        return beanUniCommunications;
    }

    public int getLessonIndex() {
        return lessonIndex;
    }

    public List<BeanLesson> getBeanLessons() {
        return beanLessons;
    }

    public UniversityHomeView getUniversityHomeView() {
        return universityHomeView;
    }



}
