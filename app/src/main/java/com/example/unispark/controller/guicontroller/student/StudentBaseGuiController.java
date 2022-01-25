package com.example.unispark.controller.guicontroller.student;

import android.content.Context;
import android.content.Intent;

import com.example.unispark.R;
import com.example.unispark.Session;
import com.example.unispark.controller.guicontroller.UserBaseGuiController;
import com.example.unispark.view.student.StudentExamsView;
import com.example.unispark.view.student.StudentHomeView;
import com.example.unispark.view.student.StudentLinksView;
import com.example.unispark.view.student.StudentProfileView;
import com.example.unispark.view.student.StudentScheduleView;


public class StudentBaseGuiController extends UserBaseGuiController {

    private Context view;

    protected StudentBaseGuiController(Session session, Context view) {
        super(session);
        this.view = view;
    }

    public void selectNextView(int selectedID){
        Intent intent = null;
        Context contextView = this.getView();
        switch (selectedID){
            //Student
            case R.id.home: intent = new Intent(contextView, StudentHomeView.class);
            break;
            case R.id.profile: intent = new Intent(contextView, StudentProfileView.class);
            break;
            case R.id.exams: intent = new Intent(contextView, StudentExamsView.class);
            break;
            case R.id.links: intent =  new Intent(contextView, StudentLinksView.class);
            break;
            case R.id.schedule: intent = new Intent(contextView, StudentScheduleView.class);
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
}
