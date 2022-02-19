package unispark.controller.guicontroller.professor;


import android.content.Intent;
import android.net.Uri;

import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.controller.appcontroller.course.ManageCourses;
import unispark.view.mobileview.details.DetailsCourseView;
import unispark.view.mobileview.professor.ProfessorProfileView;
import unispark.view.mobileview.professor.fragment.AddExamView;
import unispark.view.mobileview.professor.fragment.AddHomeworkView;
import unispark.view.mobileview.professor.fragment.AddProfCommunicationView;

import java.util.List;

public class ManageProfessorProfileGuiController extends ProfBaseGuiController {


    private ProfessorProfileView profileView;


    public ManageProfessorProfileGuiController(Session session, ProfessorProfileView profileView) {
        super(session, profileView);
        this.profileView = profileView;
    }

    public void  navigateToLink(){
        BeanLoggedProfessor bProfessor = (BeanLoggedProfessor) this.session.getUser();
        this.goToLink(bProfessor.getWebsite());
    }

    private void goToLink(String link){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.profileView.startActivity(intent);
    }

    public void showCourses(){
        BeanLoggedProfessor bProfessor = (BeanLoggedProfessor) this.session.getUser();

        //Get Parameters
        int imageID = bProfessor.getProfilePicture();
        String firstname = bProfessor.getFirstName();
        String lastname = bProfessor.getLastName();
        String website = bProfessor.getWebsite();
        this.profileView.setImgProfImage(imageID);
        this.profileView.setTxtProfName(firstname + ' ' + lastname);
        this.profileView.setTxtWebsite(website);

        List<BeanCourse> courseList;
        ManageCourses getCoursesController = new ManageCourses();
        courseList = getCoursesController.getCourses(bProfessor);
        this.profileView.setCoursesAdapter(courseList);

    }


    public void showCourseDetails(int position){
        BeanLoggedProfessor bProfessor = (BeanLoggedProfessor) this.session.getUser();
        ManageCourses getCoursesController = new ManageCourses();
        BeanCourse course = getCoursesController.getCourses(bProfessor).get(position);

        Intent intent = new Intent(this.getProfileView(), DetailsCourseView.class);
        intent.putExtra("Course", course);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.profileView.startActivity(intent);
    }



    //Open Button
    public void expandButton(){
        if(!this.isOpen()){
            //Show Buttons
            this.profileView.setBtnExam();
            this.profileView.setBtnCommunication();
            this.profileView.setBtnHomework();

            //Expand Floating Button
            this.profileView.setTxtExam();
            this.profileView.setTxtCommunication();
            this.profileView.setTxtHomework();

            //Rotate
            this.profileView.setBtnAdd();

            //Opened
            this.setOpen(true);
        }
        else{
            //Hide Buttons
            this.profileView.unsSetBtnExam();
            this.profileView.unSetBtnCommunication();
            this.profileView.unSetBtnHomework();

            //Expand Floating Button
            this.profileView.unSetTxtExam();
            this.profileView.unSetTxtCommunication();
            this.profileView.unSetTxtHomework();

            //Rotate
            this.profileView.unSetBtnAdd();

            this.setOpen(false);
        }

    }

    public void showAddExam(){
        AddExamView fragment = new AddExamView(session);
        fragment.show(this.profileView.getSupportFragmentManager(), "AddExam");
    }

    public void showAddHomework(){
        AddHomeworkView fragment= new AddHomeworkView(session, null, null);
        fragment.show(this.profileView.getSupportFragmentManager(), "AddHomework");
    }


    public void showAddCommunication(){
        AddProfCommunicationView fragment= new AddProfCommunicationView(session);
        fragment.show(this.profileView.getSupportFragmentManager(), "AddCommunication");
    }

    public ProfessorProfileView getProfileView() {
        return profileView;
    }
}
