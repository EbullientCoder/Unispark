package unispark.controller.guicontroller.professor;



import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.controller.appcontroller.communications.AddCommunication;
import unispark.engeneeringclasses.exceptions.CourseDoesNotExist;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.view.mobileview.professor.fragment.AddProfCommunicationView;


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
