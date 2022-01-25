package com.example.unispark.controller.guicontroller.university;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.controller.applicationcontroller.communications.AddCommunication;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;

import java.util.List;

public class AddCommunicationGuiController extends UniBaseGuiController {

    public void showAddMedia(Activity activity){
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        i.setFlags(i.FLAG_ACTIVITY_NEW_TASK);
        final int ACTIVITY_SELECT_IMAGE = 1234;
        activity.startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
    }



    //Creating the Communication & Adding it into the Database
    public void addCommunication(Context context, Dialog dialog, String title, String text, String facultySelection, String date,
                                 List<BeanUniCommunication> uniCommunications, UniCommunicationsAdapter uniCommunicationsAdapter){

        if (title.equals("") || text.equals("") || facultySelection.equals("")){
            getInvalidMessagge(context);
        }

        else{
            //Bean
            BeanUniCommunication beanUniCommunication;
            beanUniCommunication = new BeanUniCommunication();
            beanUniCommunication.setBackground(R.drawable.blank_img);
            beanUniCommunication.setTitle(title);
            beanUniCommunication.setDate(date);
            beanUniCommunication.setCommunication(text);
            beanUniCommunication.setFaculty(facultySelection);



            //Application Controller
            AddCommunication addCommunicationAppController = new AddCommunication();
            try {
                addCommunicationAppController.addUniCommunication(beanUniCommunication);
                communicationAddedMessage(context);
                //Notify the Communications Adapter
                uniCommunications.add(0, beanUniCommunication);
                uniCommunicationsAdapter.notifyDataSetChanged();

                dialog.dismiss();
            } catch (GenericException e) {
                e.printStackTrace();
                getErrorMessage(context, e.getMessage());
            }
        }
    }



    private void communicationAddedMessage(Context context){
        Toast.makeText(context, "Communication added", Toast.LENGTH_SHORT).show();
    }
}
