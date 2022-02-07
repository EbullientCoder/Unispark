package com.example.unispark.guicontroller.professor;



import com.example.unispark.Session;
import com.example.common.bean.communications.BeanProfessorCommunication;
import com.example.common.bean.courses.BeanCourse;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.applicationcontroller.communications.AddCommunication;
import com.example.common.exceptions.CourseDoesNotExist;
import com.example.common.exceptions.GenericException;
import com.example.unispark.view.professor.fragment.AddProfCommunicationView;


public class AddCommunicationGuiController extends AddItemGuiController{

    private AddProfCommunicationView addProfCommunicationView;

    public AddCommunicationGuiController(Session session, AddProfCommunicationView addProfCommunicationView) {
        super(session, null, null, addProfCommunicationView);
        this.addProfCommunicationView = addProfCommunicationView;
    }

    public void addCommunication(String courseSelection, String type, String text, String date){

        if(courseSelection.equals("") || type.equals("") || text.equals("")) {
            this.addProfCommunicationView.setMessage("All fields are required");
        }

        else{

            BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();
            BeanCourse beanCourse = this.getCourses(professor).get(this.getCoursePosition());
            //Bean
            BeanProfessorCommunication bCommunication;
            bCommunication = new BeanProfessorCommunication();
            bCommunication.setProfilePhoto(professor.getProfilePicture());
            bCommunication.setShortCourseName(beanCourse.getShortName());
            bCommunication.setFullName(beanCourse.getFullName());
            bCommunication.setProfessorName(professor.getFirstName() + " " + professor.getLastName());
            bCommunication.setDate(date);
            bCommunication.setType(type);
            bCommunication.setCommunication(text);

            //Application Controller
            AddCommunication addCommunicationAppController = new AddCommunication();
            try {
                addCommunicationAppController.addProfCommunication(bCommunication);
                this.addProfCommunicationView.setMessage("Communication added");
                this.addProfCommunicationView.dismiss();
            } catch (GenericException | CourseDoesNotExist e) {
                e.printStackTrace();
                this.addProfCommunicationView.setMessage(e.getMessage());
            }

        }
    }

}
