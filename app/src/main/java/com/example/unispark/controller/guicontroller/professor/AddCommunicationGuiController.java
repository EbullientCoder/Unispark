package com.example.unispark.controller.guicontroller.professor;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.BeanProfessorCommunication;
import com.example.unispark.controller.applicationcontroller.communications.AddProfCommunication;
import com.example.unispark.exceptions.GenericException;


public class AddCommunicationGuiController extends AddExamGuiController{

    public void addCommunication(Context context, Dialog dialog, String courseSelection, String type, String text,
                                 int profilePicture, String courseShortName, String courseName,
                                 String firstName, String lastName, String date){

        if(courseSelection.equals("") || type.equals("") || text.equals("")) {
            getInvalidMessagge(context);
        }

        else{

            //Bean
            BeanProfessorCommunication bCommunication;
            bCommunication = new BeanProfessorCommunication(
                    profilePicture,
                    courseShortName,
                    courseName,
                    firstName + " " + lastName,
                    date,
                    type,
                    text);

            //Application Controller
            AddProfCommunication addCommunicationAppController = new AddProfCommunication();
            try {
                addCommunicationAppController.addProfCommunication(bCommunication);
                CommunicationAddedMessage(context);
                dialog.dismiss();
            } catch (GenericException e) {
                e.printStackTrace();
                errorMessage(context, e.getMessage());
            }

        }
    }



    private void CommunicationAddedMessage(Context context){

        Toast.makeText(context, "Communication added", Toast.LENGTH_SHORT).show();
    }


}
