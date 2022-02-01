package com.example.unispark.controller.guicontroller.professor;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.Session;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.homeworks.AddHomework;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.view.professor.fragment.AddExamView;
import com.example.unispark.view.professor.fragment.AddHomeworkView;
import com.example.unispark.view.professor.fragment.AddProfCommunicationView;
import com.example.unispark.viewadapter.HomeworksAdapter;

import java.util.List;

public class AddHomeworkGuiController extends AddItemGuiController{

    private List<BeanHomework> beanHomeworks;
    private AddHomeworkView addHomeworkView;

    public AddHomeworkGuiController(Session session, AddHomeworkView addHomeworkView, List<BeanHomework> beanHomeworks) {
        super(session, null, addHomeworkView, null);
        this.addHomeworkView = addHomeworkView;
        this.beanHomeworks = beanHomeworks;
    }

    public void addHomework(String courseSelection, String title,
                            String instructions, String points, String date){

        //Get the LoggedProfessor from the Session
        BeanLoggedProfessor professor = (BeanLoggedProfessor) session.getUser();
        BeanCourse beanCourse = getCourses(professor).get(getCoursePosition());

        //Homework Object
        BeanHomework bHomework;
        bHomework = new BeanHomework();
        bHomework.setFullName(beanCourse.getFullName());
        bHomework.setShortName(beanCourse.getShortName());

        //Syntactic Error check through the BeanHomework private methods
        if (courseSelection.equals("") ||
                !bHomework.setPoints(points) ||
                !bHomework.setInstructions(instructions) ||
                !bHomework.setExpiration(date) ||
                !bHomework.setTitle(title)){

            addHomeworkView.setMessage("All fields are required");
        }
        else{
            //Application Controller
            AddHomework addHomeworkAppController = new AddHomework();
            try {
                addHomeworkAppController.addHomework(bHomework, professor);
                addHomeworkView.setMessage("Homework added");

                //Notify the Homework Adapter
                if(beanHomeworks != null && addHomeworkView.getHomeworksAdapter() != null){
                    beanHomeworks.add(0, bHomework);
                    addHomeworkView.notifyDataChanged();
                }
                addHomeworkView.dismiss();
            } catch (GenericException e) {
                e.printStackTrace();
                addHomeworkView.setMessage(e.getMessage());
            }
        }
    }




}
