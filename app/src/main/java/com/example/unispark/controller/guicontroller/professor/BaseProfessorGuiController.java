package com.example.unispark.controller.guicontroller.professor;

import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.example.unispark.bean.homework.BeanHomework;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.guicontroller.BottomNavigationMenuGuiController;
import com.example.unispark.view.professor.fragment.AddExamView;
import com.example.unispark.view.professor.fragment.AddHomeworkView;
import com.example.unispark.view.professor.fragment.AddProfCommunicationView;
import com.example.unispark.viewadapter.HomeworksAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class BaseProfessorGuiController extends BottomNavigationMenuGuiController {



    //Open Button
    public boolean expandButton(boolean isOpen, FloatingActionButton btnExam, FloatingActionButton btnCommunication, FloatingActionButton btnHomework,
                             TextView txtExam, TextView txtCommunication, TextView txtHomework, FloatingActionButton btnAdd){
        if(!isOpen){
            //Show Buttons
            btnExam.show();
            btnCommunication.show();
            btnHomework.show();

            //Expand Floating Button
            txtExam.setVisibility(View.VISIBLE);
            txtCommunication.setVisibility(View.VISIBLE);
            txtHomework.setVisibility(View.VISIBLE);

            //Rotate
            btnAdd.setRotation(45);

            isOpen = true;
        }
        else{
            //Hide Buttons
            btnExam.hide();
            btnCommunication.hide();
            btnHomework.hide();

            //Expand Floating Button
            txtExam.setVisibility(View.GONE);
            txtCommunication.setVisibility(View.GONE);
            txtHomework.setVisibility(View.GONE);

            //Rotate
            btnAdd.setRotation(0);

            isOpen = false;
        }
        return isOpen;
    }


    public void showAddExam(FragmentManager fragmentManager, BeanLoggedProfessor professor){

        AddExamView fragment = new AddExamView(professor);
        fragment.show(fragmentManager, "AddExam");
    }


    public void showAddHomework(FragmentManager fragmentManager, BeanLoggedProfessor professor,
                                List<BeanHomework> homeworkList, HomeworksAdapter homeworkAdapter){

        AddHomeworkView fragment= new AddHomeworkView(professor, homeworkList, homeworkAdapter);
        fragment.show(fragmentManager, "AddHomework");
    }

    public void showAddHomework(FragmentManager fragmentManager, BeanLoggedProfessor professor){

        AddHomeworkView fragment= new AddHomeworkView(professor);
        fragment.show(fragmentManager, "AddHomework");
    }


    public void showAddCommunication(FragmentManager fragmentManager, BeanLoggedProfessor professor){
        AddProfCommunicationView fragment= new AddProfCommunicationView(professor);
        fragment.show(fragmentManager, "AddCommunication");
    }



}
