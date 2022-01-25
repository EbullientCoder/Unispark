package com.example.unispark.controller.guicontroller.student;


import android.content.Intent;
import android.net.Uri;


import com.example.unispark.Session;
import com.example.unispark.bean.BeanLink;
import com.example.unispark.bean.professor.BeanProfessorDetails;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.links.AddLink;
import com.example.unispark.controller.applicationcontroller.links.DeleteLink;
import com.example.unispark.controller.applicationcontroller.links.ShowLinks;
import com.example.unispark.controller.applicationcontroller.professor.ShowFacultyProfessors;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LinkAlreadyExists;
import com.example.unispark.view.details.DetailsProfessorView;
import com.example.unispark.view.student.StudentLinksView;


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
