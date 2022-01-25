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


    private Context view;
    private boolean isOpen;

    protected ProfBaseGuiController(Session session, Context view) {
        super(session);
        this.view = view;

    }

    public void selectNextView(int selectedID){
        Intent intent = null;
        Context contextView = this.getView();
        switch (selectedID){
            //Professor
            case R.id.professor_home: intent = new Intent(contextView, ProfessorHomeView.class);
                break;
            case R.id.professor_profile: intent = new Intent(contextView, ProfessorProfileView.class);
                break;
            case R.id.professor_exams: intent = new Intent(contextView, ProfessorExamsView.class);
                break;
            default: break;
        }
        intent.putExtra("session", this.session);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        contextView.startActivity(intent);
    }


    public Context getView() {
        return view;
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
