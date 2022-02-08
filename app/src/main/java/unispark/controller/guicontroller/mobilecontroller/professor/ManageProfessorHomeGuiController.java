package unispark.controller.guicontroller.mobilecontroller.professor;


import android.content.Intent;


import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.controller.appcontroller.communications.ShowCommunications;
import unispark.controller.appcontroller.homeworks.GetHomeworks;
import unispark.view.mobileview.details.DetailsHomeworkView;
import unispark.view.mobileview.details.DetailsUniCommunicationView;
import unispark.view.mobileview.professor.ProfessorHomeView;
import unispark.view.mobileview.professor.fragment.AddExamView;
import unispark.view.mobileview.professor.fragment.AddHomeworkView;
import unispark.view.mobileview.professor.fragment.AddProfCommunicationView;


import java.util.List;

public class ManageProfessorHomeGuiController extends ProfBaseGuiController {

    //Attributes
    private ProfessorHomeView professorHomeView;
    private List<BeanHomework> beanHomeworks;
    private List<BeanUniCommunication> beanUniCommunications;

    //Constructor
    public ManageProfessorHomeGuiController(Session session, ProfessorHomeView professorHomeView) {
        super(session, professorHomeView);

        this.professorHomeView = professorHomeView;
        setOpen(false);
    }

    //Show University Communications
    public void showUniCommunications(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) session.getUser();

        //Application Controller
        ShowCommunications uniCommunicationsAppController = new ShowCommunications();
        this.beanUniCommunications = uniCommunicationsAppController.showUniversityCommunications(professor);
        professorHomeView.setUniCommunicationsAdapter(beanUniCommunications);
    }


    //Show Homeworks
    public void showHomeworks(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) session.getUser();

        //Application Controller
        GetHomeworks homeworksAppController = new GetHomeworks();
        this.beanHomeworks = homeworksAppController.getHomework(professor);
        professorHomeView.setHomeworkAdapter(beanHomeworks);
    }

    //Click on a University Communication
    public void showDetailsCommunication(int position){
        Intent intent = new Intent(professorHomeView, DetailsUniCommunicationView.class);

        //Pass Items to the new Activity
        intent.putExtra("Communication", beanUniCommunications.get(position));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        professorHomeView.startActivity(intent);
    }

    //Click on a Homework: Called by the interface "OnBtnCLick"
    public void showHomeworkDetails(int position){
        Intent intent = new Intent(professorHomeView, DetailsHomeworkView.class);

        //Pass Items to the new Activity
        intent.putExtra("Homework", beanHomeworks.get(position));
        intent.putExtra("HomeView", "ProfessorHome");
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        professorHomeView.startActivity(intent);
    }


    //Open Button: Add Item
    public void expandButton(){
        if(!isOpen()){
            //Show Buttons
            professorHomeView.setBtnExam();
            professorHomeView.setBtnCommunication();
            professorHomeView.setBtnHomework();

            //Expand Floating Button
            professorHomeView.setTxtExam();
            professorHomeView.setTxtCommunication();
            professorHomeView.setTxtHomework();

            //Rotate
            professorHomeView.setBtnAdd();

            //Opened
            setOpen(true);
        }
        else{
            //Hide Buttons
            professorHomeView.unsSetBtnExam();
            professorHomeView.unSetBtnCommunication();
            professorHomeView.unSetBtnHomework();

            //Expand Floating Button
            professorHomeView.unSetTxtExam();
            professorHomeView.unSetTxtCommunication();
            professorHomeView.unSetTxtHomework();

            //Rotate
            professorHomeView.unSetBtnAdd();

            setOpen(false);
        }

    }


    //Open fragment: Add Exam
    public void showAddExam(){
        AddExamView fragment = new AddExamView(session);
        fragment.show(professorHomeView.getSupportFragmentManager(), "AddExamAppController");
    }

    //Open fragment: Add Homework
    public void showAddHomework(){
        AddHomeworkView fragment= new AddHomeworkView(session, beanHomeworks, professorHomeView.getHomeworkAdapter());
        fragment.show(professorHomeView.getSupportFragmentManager(), "AddHomeworkAppController");
    }

    //Open fragment: Add Communication
    public void showAddCommunication(){
        AddProfCommunicationView fragment= new AddProfCommunicationView(session);
        fragment.show(professorHomeView.getSupportFragmentManager(), "AddCommunication");
    }
}
