package com.example.unispark.controller.guicontroller.university;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class BaseUniGuiController {


    //Open Button
    public boolean expandButton(boolean isOpen, FloatingActionButton btnCommunication, FloatingActionButton btnSchedule,
                             TextView txtCommunication, TextView txtSchedule, FloatingActionButton btnAdd){
        if(!isOpen){
            //Show Buttons
            btnCommunication.show();
            btnSchedule.show();

            //Expand Floating Button
            txtCommunication.setVisibility(View.VISIBLE);
            txtSchedule.setVisibility(View.VISIBLE);

            //Rotate
            btnAdd.setRotation(45);

            isOpen = true;
        }
        else{
            //Hide Buttons
            btnCommunication.hide();
            btnSchedule.hide();

            //Hide text
            txtCommunication.setVisibility(View.GONE);
            txtSchedule.setVisibility(View.GONE);

            //Rotate
            btnAdd.setRotation(0);

            isOpen = false;
        }

        return isOpen;
    }

    public void getInvalidMessagge(Context context){
        Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show();
    }



    public void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
