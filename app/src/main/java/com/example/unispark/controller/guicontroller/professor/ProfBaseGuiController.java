package com.example.unispark.controller.guicontroller.professor;


import android.content.Context;
import android.content.Intent;




import com.example.unispark.R;
import com.example.unispark.Session;

import com.example.unispark.controller.guicontroller.UserBaseGuiController;
import com.example.unispark.view.professor.ProfessorExamsView;
import com.example.unispark.view.professor.ProfessorHomeView;
import com.example.unispark.view.professor.ProfessorProfileView;


public class ProfBaseGuiController extends UserBaseGuiController {

    //Attributes
    private Context view;
    private boolean isOpen;

    //Constructor
    protected ProfBaseGuiController(Session session, Context view) {
        super(session);
        this.view = view;
    }

    //Select the next Activity through BottomNavigationMenu
    public void selectNextView(int selectedID){
        Intent intent = null;

        //Use the ID to select the activity
        switch (selectedID){
            //Professor
            case R.id.professor_home: intent = new Intent(view, ProfessorHomeView.class);
                break;
            case R.id.professor_profile: intent = new Intent(view, ProfessorProfileView.class);
                break;
            case R.id.professor_exams: intent = new Intent(view, ProfessorExamsView.class);
                break;
            default: break;
        }
        //Pass the Session to the new Activity
        intent.putExtra("session", session);

        //If the Activity already exist open it again, don't create a new instance of the same.
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //Start the Activity
        view.startActivity(intent);
    }


    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setView(Context view) {
        this.view = view;
    }

    public boolean isOpen() {
        return isOpen;
    }
}
