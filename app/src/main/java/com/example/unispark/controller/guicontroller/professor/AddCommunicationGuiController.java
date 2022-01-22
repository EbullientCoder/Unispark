package com.example.unispark.controller.guicontroller.professor;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.controller.applicationcontroller.communications.AddCommunication;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.GenericException;


public class AddCommunicationGuiController extends AddItemGuiController{

    public void addCommunication(Context context, Dialog dialog, String courseSelection, String type, String text,
                                 int profilePicture, String courseShortName, String courseName,
                                 String firstName, String lastName, String date){

        if(courseSelection.equals("") || type.equals("") || text.equals("")) {
            getInvalidMessagge(context);
        }

        else{

            //Bean
            BeanProfessorCommunication bCommunication;
            bCommunication = new BeanProfessorCommunication();
            bCommunication.setProfilePhoto(profilePicture );
            bCommunication.setShortCourseName(courseShortName);
            bCommunication.setFullName(courseName);
            bCommunication.setProfessorName(firstName + " " + lastName);
            bCommunication.setDate(date);
            bCommunication.setType(type);
            bCommunication.setCommunication(text);

            //Application Controller
            AddCommunication addCommunicationAppController = new AddCommunication();
            try {
                addCommunicationAppController.addProfCommunication(bCommunication);
                communicationAddedMessage(context);
                dialog.dismiss();
            } catch (GenericException | CourseDoesNotExist e) {
                e.printStackTrace();
                errorMessage(context, e.getMessage());
            }

        }
    }



    private void communicationAddedMessage(Context context){

        Toast.makeText(context, "Communication added", Toast.LENGTH_SHORT).show();
    }


}
