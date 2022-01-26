package com.example.unispark.controller.guicontroller.professor;


import android.content.Intent;


import com.example.unispark.Session;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.communications.ShowCommunications;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.view.details.DetailsHomeworkView;
import com.example.unispark.view.details.DetailsUniCommunicationView;
import com.example.unispark.view.professor.ProfessorHomeView;
import com.example.unispark.view.professor.fragment.AddExamView;
import com.example.unispark.view.professor.fragment.AddHomeworkView;
import com.example.unispark.view.professor.fragment.AddProfCommunicationView;


import java.util.List;

public class ManageProfessorHomeGuiController extends ProfBaseGuiController {


    private ProfessorHomeView professorHomeView;
    private List<BeanHomework> beanHomeworks;
    private List<BeanUniCommunication> beanUniCommunications;

    public ManageProfessorHomeGuiController(Session session, ProfessorHomeView professorHomeView) {
        super(session, professorHomeView);
        this.professorHomeView= professorHomeView;
        this.setOpen(false);
    }


    public void showUniCommunications(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();
        //Application Controller
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        this.beanUniCommunications = uniCommunicationsAppController.showUniversityCommunications(professor);
        this.professorHomeView.setUniCommunicationsAdapter(this.getBeanUniCommunications());


    }

    public void showHomeworks(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();
        ShowHomeworks homeworksAppController = new ShowHomeworks();
        this.beanHomeworks = homeworksAppController.getHomework(professor);
        this.professorHomeView.setHomeworkAdapter(this.getBeanHomeworks());

    }

    public void showDetailsCommunication(int position){
        Intent intent = new Intent(this.getProfessorHomeView(), DetailsUniCommunicationView.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", this.getBeanUniCommunications().get(position));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.getProfessorHomeView().startActivity(intent);
    }


    public void showHomeworkDetails(int position){

        Intent intent = new Intent(this.getProfessorHomeView(), DetailsHomeworkView.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", this.getBeanHomeworks().get(position));
        intent.putExtra("HomeView", "ProfessorHome");
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.getProfessorHomeView().startActivity(intent);
    }


    //Open Button
    public void expandButton(){
        if(!this.isOpen()){
            //Show Buttons
            this.professorHomeView.setBtnExam();
            this.professorHomeView.setBtnCommunication();
            this.professorHomeView.setBtnHomework();

            //Expand Floating Button
            this.professorHomeView.setTxtExam();
            this.professorHomeView.setTxtCommunication();
            this.professorHomeView.setTxtHomework();

            //Rotate
            this.professorHomeView.setBtnAdd();

            //Opened
            this.setOpen(true);
        }
        else{
            //Hide Buttons
            this.professorHomeView.unsSetBtnExam();
            this.professorHomeView.unSetBtnCommunication();
            this.professorHomeView.unSetBtnHomework();

            //Expand Floating Button
            this.professorHomeView.unSetTxtExam();
            this.professorHomeView.unSetTxtCommunication();
            this.professorHomeView.unSetTxtHomework();

            //Rotate
            this.professorHomeView.unSetBtnAdd();

            this.setOpen(false);
        }

    }


    public void showAddExam(){
        AddExamView fragment = new AddExamView(this.session);
        fragment.show(this.professorHomeView.getSupportFragmentManager(), "AddExam");
    }

    public void showAddHomework(){

        AddHomeworkView fragment= new AddHomeworkView(this.getSession(), this.getBeanHomeworks(), this.professorHomeView.getHomeworkAdapter());
        fragment.show(this.professorHomeView.getSupportFragmentManager(), "AddHomework");
    }


    public void showAddCommunication(){
        AddProfCommunicationView fragment= new AddProfCommunicationView(this.getSession());
        fragment.show(this.professorHomeView.getSupportFragmentManager(), "AddCommunication");
    }

    public ProfessorHomeView getProfessorHomeView() {
        return professorHomeView;
    }

    public List<BeanHomework> getBeanHomeworks() {
        return beanHomeworks;
    }

    public List<BeanUniCommunication> getBeanUniCommunications() {
        return beanUniCommunications;
    }
}
