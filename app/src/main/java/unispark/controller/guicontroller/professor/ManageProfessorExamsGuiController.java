package unispark.controller.guicontroller.professor;

import android.content.Intent;

import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.controller.appcontroller.exams.ManageExams;
import unispark.view.mobileview.professor.VerbalizeExamsView;
import unispark.view.mobileview.professor.ProfessorExamsView;
import unispark.view.mobileview.professor.fragment.AddExamView;
import unispark.view.mobileview.professor.fragment.AddHomeworkView;
import unispark.view.mobileview.professor.fragment.AddProfCommunicationView;

import java.util.List;

public class ManageProfessorExamsGuiController extends ProfBaseGuiController {

    private ProfessorExamsView examsView;
    private List<BeanExam> beanExams;

    public ManageProfessorExamsGuiController(Session session, ProfessorExamsView examsView) {
        super(session, examsView);
        this.examsView = examsView;
    }

    public void showExams(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();

        ManageExams getExamsAppController = new ManageExams();
        this.beanExams = getExamsAppController.assignedExams(professor);
        this.examsView.setExamAdapter(this.beanExams);
        this.examsView.setExamType(3);

    }

    public void showVerbalizeExam(int position){
        BeanExam beanExam = this.beanExams.get(position);

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




    public ProfessorExamsView getExamsView() {
        return examsView;
    }
}
