package com.example.unispark.controller.guicontroller.student;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.example.unispark.bean.BeanLink;
import com.example.unispark.bean.BeanProfessorDetails;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.links.AddLink;
import com.example.unispark.controller.applicationcontroller.links.DeleteLink;
import com.example.unispark.controller.applicationcontroller.links.ShowLinks;
import com.example.unispark.controller.applicationcontroller.professor.ShowFacultyProfessors;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LinkAlreadyExists;
import com.example.unispark.controller.guicontroller.BottomNavigationMenuGuiController;
import com.example.unispark.view.details.DetailsProfessorView;
import com.example.unispark.viewadapter.LinksAdapter;

import java.sql.SQLException;
import java.util.List;

public class MenageLinksGuiController extends BottomNavigationMenuGuiController {


    public List<BeanProfessorDetails> showProfessorDetails(BeanLoggedStudent student){
        List<BeanProfessorDetails> professorDetails = null;

        //Application Controller
        ShowFacultyProfessors facultyProfessorsAppController = new ShowFacultyProfessors();
        professorDetails = facultyProfessorsAppController.setFacultyProfessors(student);


        return professorDetails;
    }

    public List<BeanLink> showLinks(BeanLoggedStudent student){
        List<BeanLink> beanLinks;

        ShowLinks linksAppController = new ShowLinks();
        beanLinks = linksAppController.showLinks(student);

        return beanLinks;
    }

    public void addLink(Context context, String linkName, String link, BeanLoggedStudent student, List<BeanLink> links, LinksAdapter linksAdapter){

        if(linkName.length() != 0 && link.length() != 0){

            BeanLink newLink = new BeanLink(linkName, link);

            //Application Controller
            AddLink addLinksAppController = new AddLink();
            try {
                addLinksAppController.addLink(student, newLink);
                links.add(newLink);
                linksAdapter.notifyItemInserted(links.size());
            } catch (LinkAlreadyExists | GenericException e) {
                e.printStackTrace();
                getErrorMessage(context, e.getMessage());
            }

        }
        else getEmptyErrorMessage(context);
    }

    private void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


    private void getEmptyErrorMessage(Context context){
        Toast.makeText(context, "EMPTY LINK", Toast.LENGTH_SHORT).show();
    }

    public void showProfessorDetails(Context context, BeanProfessorDetails professorDetails){

        Intent intent = new Intent(context, DetailsProfessorView.class);
        intent.putExtra("Professor", professorDetails);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void goToLink(Context context, String link){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void removeLink(Context context, List<BeanLink> links, int position, LinksAdapter linksAdapter){

        //Application Controller: Delete Link
        DeleteLink deleteLinkAppController = new DeleteLink();

        try {
            deleteLinkAppController.deleteLink(links.get(position));
            //Removing link from the List
            links.remove(position);
            linksAdapter.notifyItemRemoved(position);
        } catch (GenericException e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }
    }


}
