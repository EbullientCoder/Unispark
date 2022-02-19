package unispark.controller.guicontroller.student;

import android.content.Intent;

import unispark.controller.appcontroller.communications.ManageCommunications;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.controller.appcontroller.homeworks.ManageHomeworks;
import unispark.view.details.DetailsHomeworkView;
import unispark.view.details.DetailsProfCommunicationView;
import unispark.view.details.DetailsUniCommunicationView;
import unispark.view.student.StudentHomeView;


import java.util.Collections;
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
        ManageCommunications communicationsController = new ManageCommunications();
        this.beanUniCommunications = communicationsController.showUniversityCommunications(student);
        this.studentHomeView.setUniCommunicationsAdapter(this.getBeanUniCommunications());

    }



    public void showProfessorCommunications(){

        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Applicative Controller
        ManageCommunications communicationsController = new ManageCommunications();
        this.beanProfessorCommunications = communicationsController.showProfessorCommunications(student);
        this.studentHomeView.setProfCommunicationsAdapter(this.getBeanProfessorCommunications());

    }


    public void showHomeworks(){

        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Applicative Controller
        ManageHomeworks showHomeworksController = new ManageHomeworks();
        this.beanHomeworks = showHomeworksController.getHomework(student);
        Collections.reverse(this.beanHomeworks);

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
