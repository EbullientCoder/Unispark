package com.example.unispark.controller.guicontroller.professor;

import android.content.Intent;

import com.example.unispark.Session;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.exams.ManageExams;
import com.example.unispark.view.professor.VerbalizeExamsView;
import com.example.unispark.view.professor.ProfessorExamsView;
import com.example.unispark.view.professor.fragment.AddExamView;
import com.example.unispark.view.professor.fragment.AddHomeworkView;
import com.example.unispark.view.professor.fragment.AddProfCommunicationView;

import java.util.List;

public class ManageProfessorExamsGuiController extends ProfBaseGuiController {

    private ProfessorExamsView examsView;
    private List<BeanExamType> beanExams;

    public ManageProfessorExamsGuiController(Session session, ProfessorExamsView examsView) {
        super(session, examsView);
        this.examsView = examsView;
    }

    public void showExams(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();

        ManageExams getExamsAppController = new ManageExams();
        this.beanExams = getExamsAppController.assignedExams(professor);
        this.examsView.setExamAdapter(this.getBeanExams());

    }

    public void showVerbalizeExam(int position){
        BeanExam beanExam = this.getBeanExams().get(position).getExamType();

        Intent intent = new Intent(this.getExamsView(), VerbalizeExamsView.class);
        intent.putExtra("Exam", (BeanBookExam) beanExam);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.examsView.startActivity(intent);
    }



    //Open Button
    public void expandButton(){
        if(!this.isOpen()){
            //Show Buttons
            this.examsView.setBtnExam();
            this.examsView.setBtnCommunication();
            this.examsView.setBtnHomework();

            //Expand Floating Button
            this.examsView.setTxtExam();
            this.examsView.setTxtCommunication();
            this.examsView.setTxtHomework();

            //Rotate
            this.examsView.setBtnAdd();

            //Opened
            this.setOpen(true);
        }
        else{
            //Hide Buttons
            this.examsView.unsSetBtnExam();
            this.examsView.unSetBtnCommunication();
            this.examsView.unSetBtnHomework();

            //Expand Floating Button
            this.examsView.unSetTxtExam();
            this.examsView.unSetTxtCommunication();
            this.examsView.unSetTxtHomework();

            //Rotate
            this.examsView.unSetBtnAdd();

            this.setOpen(false);
        }

    }

    public void showAddExam(){
        AddExamView fragment = new AddExamView(session);
        fragment.show(this.examsView.getSupportFragmentManager(), "AddExam");
    }

    public void showAddHomework(){
        AddHomeworkView fragment= new AddHomeworkView(session, null, null);
        fragment.show(this.examsView.getSupportFragmentManager(), "AddHomework");
    }


    public void showAddCommunication(){
        AddProfCommunicationView fragment = new AddProfCommunicationView(session);
        fragment.show(this.examsView.getSupportFragmentManager(), "AddCommunication");
    }


    public List<BeanExamType> getBeanExams() {
        return beanExams;
    }

    public ProfessorExamsView getExamsView() {
        return examsView;
    }
}
