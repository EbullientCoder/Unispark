package unispark.mobile.guicontroller.student;

import android.content.Intent;

import unispark.mobile.Session;
import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.applicationcontroller.communications.ShowCommunications;
import unispark.engeneeringclasses.applicationcontroller.homeworks.GetHomeworks;
import unispark.mobile.view.details.DetailsHomeworkView;
import unispark.mobile.view.details.DetailsProfCommunicationView;
import unispark.mobile.view.details.DetailsUniCommunicationView;
import unispark.mobile.view.student.StudentHomeView;


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
        GetHomeworks showHomeworksController = new GetHomeworks();
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
