package com.example.unispark.controller.guicontroller.student;

import android.content.Intent;

import com.example.unispark.Session;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.communications.ShowCommunications;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.view.details.DetailsHomeworkView;
import com.example.unispark.view.details.DetailsProfCommunicationView;
import com.example.unispark.view.details.DetailsUniCommunicationView;
import com.example.unispark.view.student.StudentHomeView;


import java.util.List;

public class ManageStudentHomeGuiController extends StudentBaseGuiController {

    private StudentHomeView studentHomeView;

    private List<BeanUniCommunication> beanUniCommunications;
    private List<BeanProfessorCommunication> beanProfessorCommunications;
    private List<BeanHomework> beanHomeworks;

    public ManageStudentHomeGuiController(Session session, StudentHomeView studentHomeView) {
        super(session, studentHomeView);
        this.studentHomeView = studentHomeView;
    }


    public void showUniCommunications(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Applicative Controller
        ShowCommunications communicationsController = new ShowCommunications();
        this.beanUniCommunications = communicationsController.showUniversityCommunications(student);
        this.studentHomeView.setUniCommunicationsAdapter(this.getBeanUniCommunications());

    }



    public void showProfessorCommunications(){

        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Applicative Controller
        ShowCommunications communicationsController = new ShowCommunications();
        this.beanProfessorCommunications = communicationsController.showProfessorCommunications(student);
        this.studentHomeView.setProfCommunicationsAdapter(this.getBeanProfessorCommunications());

    }


    public void showHomeworks(){

        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Applicative Controller
        ShowHomeworks showHomeworksController = new ShowHomeworks();
        this.beanHomeworks = showHomeworksController.getHomework(student);
        this.studentHomeView.setHomeworksAdapter(this.getBeanHomeworks());

    }

    public void showDetailsUniCommunication(int position){
        Intent intent = new Intent(this.getStudentHomeView(), DetailsUniCommunicationView.class);
        //Pass Items to the new Activity
        BeanUniCommunication beanUniCommunication = this.beanUniCommunications.get(position);
        intent.putExtra("Communication", beanUniCommunication);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.studentHomeView.startActivity(intent);
    }



    public void showDetailsProfCommunication(int position){
        Intent intent = new Intent(this.getStudentHomeView(), DetailsProfCommunicationView.class);
        //Pass Items to the new Activity
        BeanProfessorCommunication beanProfessorCommunication = this.beanProfessorCommunications.get(position);
        intent.putExtra("Communication", beanProfessorCommunication);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.studentHomeView.startActivity(intent);
    }

    public void showHomeworkDetails(int position){

        Intent intent = new Intent(this.getStudentHomeView(), DetailsHomeworkView.class);
        //Pass Items to the new Activity
        BeanHomework beanHomework = this.beanHomeworks.get(position);
        intent.putExtra("Homework", beanHomework);
        intent.putExtra("HomeView", "StudentHome");
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.studentHomeView.startActivity(intent);

    }

    public List<BeanUniCommunication> getBeanUniCommunications() {
        return beanUniCommunications;
    }

    public List<BeanProfessorCommunication> getBeanProfessorCommunications() {
        return beanProfessorCommunications;
    }

    public List<BeanHomework> getBeanHomeworks() {
        return beanHomeworks;
    }

    public StudentHomeView getStudentHomeView() {
        return studentHomeView;
    }


}
