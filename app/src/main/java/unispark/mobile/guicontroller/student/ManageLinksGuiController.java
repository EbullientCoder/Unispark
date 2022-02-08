package unispark.mobile.guicontroller.student;


import android.content.Intent;
import android.net.Uri;


import unispark.mobile.Session;
import unispark.engeneeringclasses.bean.BeanLink;
import unispark.engeneeringclasses.bean.professor.BeanProfessorDetails;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.applicationcontroller.links.AddLink;
import unispark.engeneeringclasses.applicationcontroller.links.DeleteLink;
import unispark.engeneeringclasses.applicationcontroller.links.ShowLinks;
import unispark.engeneeringclasses.applicationcontroller.professor.ShowFacultyProfessors;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.LinkAlreadyExists;
import unispark.mobile.view.details.DetailsProfessorView;
import unispark.mobile.view.student.StudentLinksView;


import java.util.List;

public class ManageLinksGuiController extends StudentBaseGuiController {


    private StudentLinksView linksView;
    private List<BeanLink> beanLinks;
    private List<BeanProfessorDetails> beanProfessorDetails;

    public ManageLinksGuiController(Session session, StudentLinksView linksView) {
        super(session, linksView);
        this.linksView = linksView;
    }

    public void showProfessorDetails(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        //Application Controller
        ShowFacultyProfessors facultyProfessorsAppController = new ShowFacultyProfessors();
        this.beanProfessorDetails = facultyProfessorsAppController.setFacultyProfessors(student);
        this.linksView.setProfessorsAdapter(this.getBeanProfessorDetails());
    }

    public void showLinks(){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();

        ShowLinks linksAppController = new ShowLinks();
        this.beanLinks = linksAppController.showLinks(student);
        this.linksView.setLinkAdapter(this.getBeanLinks());
    }

    public void addLink(String linkName, String link){
        BeanLoggedStudent student = (BeanLoggedStudent) this.session.getUser();
        if(linkName.length() != 0 && link.length() != 0){

            BeanLink newLink = new BeanLink();
            newLink.setLinkName(linkName);
            newLink.setLinkAddress(link);

            //Application Controller
            AddLink addLinksAppController = new AddLink();
            try {
                addLinksAppController.addLink(student, newLink);
                this.beanLinks.add(newLink);
                this.linksView.notifyDataChanged(this.beanLinks.size(), false);
            } catch (LinkAlreadyExists | GenericException e) {
                e.printStackTrace();
                this.linksView.setErrorMessage(e.getMessage());
            }

        }
        else this.linksView.setErrorMessage("Link cannot be empty");
    }




    public void showProfessorDetails(int position){

        Intent intent = new Intent(this.getLinksView(), DetailsProfessorView.class);
        intent.putExtra("Professor", this.beanProfessorDetails.get(position));
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.linksView.startActivity(intent);
    }

    public void goToLink(String link){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        this.linksView.startActivity(intent);
    }

    public void removeLink(int position){

        //Application Controller: Delete Link
        DeleteLink deleteLinkAppController = new DeleteLink();

        try {
            deleteLinkAppController.deleteLink(this.beanLinks.get(position));
            //Removing link from the List
            this.beanLinks.remove(position);
            this.linksView.notifyDataChanged(position, true);
        } catch (GenericException e) {
            e.printStackTrace();
            this.linksView.setErrorMessage(e.getMessage());
        }
    }

    public StudentLinksView getLinksView() {
        return linksView;
    }

    public List<BeanLink> getBeanLinks() {
        return beanLinks;
    }

    public List<BeanProfessorDetails> getBeanProfessorDetails() {
        return beanProfessorDetails;
    }
}
