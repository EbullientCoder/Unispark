package com.example.unispark.controller.guicontroller.professor;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

import com.example.unispark.Session;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.view.professor.fragment.AddExamView;
import com.example.unispark.view.professor.fragment.AddHomeworkView;
import com.example.unispark.view.professor.fragment.AddProfCommunicationView;

import java.util.List;

public class AddItemGuiController extends ProfBaseGuiController{

    private AddExamView addExamView;
    private AddHomeworkView addHomeworkView;
    private AddProfCommunicationView addProfCommunicationView;

    //Selector
    private int coursePosition;

    public AddItemGuiController(Session session, AddExamView addExamView, AddHomeworkView addHomeworkView, AddProfCommunicationView addProfCommunicationView) {
        super(session, null);
        if (addExamView != null){
            this.setView(addExamView.getContext());
            this.addExamView = addExamView;
        }
        if (addHomeworkView != null){
            this.setView(addHomeworkView.getContext());
            this.addHomeworkView = addHomeworkView;
        }
        if (addProfCommunicationView != null){
            this.setView(addProfCommunicationView.getContext());
            this.addProfCommunicationView = addProfCommunicationView;
        }
    }


    public List<BeanCourse> getCourses(BeanLoggedProfessor professor){

        ManageCourses getCoursesController = new ManageCourses();
        return getCoursesController.getCourses(professor);
    }

    public void coursesNamesSelector(){
        BeanLoggedProfessor professor = (BeanLoggedProfessor) this.session.getUser();
        List<String> coursesNames;
        ManageCourses coursesGuiController = new ManageCourses();
        coursesNames = coursesGuiController.getCoursesNames(professor);
        if (addExamView != null){
            this.addExamView.setAdapterItems(coursesNames);
        }
        if (addHomeworkView != null){
            this.addHomeworkView.setAdapterItems(coursesNames);
        }
        if (addProfCommunicationView != null){
            this.addProfCommunicationView.setAdapterItems(coursesNames);
        }

    }

    public void showDateDialog(int year, int month, int day){

        DatePickerDialog datePickerDialog = null;
        if (addExamView != null){
            datePickerDialog = new DatePickerDialog(
                    this.addExamView.getContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    this.addExamView.getDateListener(),
                    year,
                    month,
                    day);
        }
        if (addHomeworkView != null){
            datePickerDialog = new DatePickerDialog(
                    this.addHomeworkView.getContext(),
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    this.addHomeworkView.getDateListener(),
                    year,
                    month,
                    day);
        }


        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }

    public void selectPosition(int position){
        this.coursePosition = position;
    }

    public int getCoursePosition() {
        return coursePosition;
    }
}
