package com.example.unispark.controller.guicontroller.professor;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.homeworks.AddHomework;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.viewadapter.HomeworksAdapter;

import java.util.List;

public class AddHomeworkGuiController extends AddItemGuiController{



    public void addHomework(Context context, Dialog dialog, String courseSelection, String title,
                            String instructions, String points, String courseShortName, String courseName,
                            String date, BeanLoggedProfessor professor, HomeworksAdapter homeworksAdapter, List<BeanHomework> homeworkList){


        //Homework Object
        BeanHomework bHomework;
        bHomework = new BeanHomework();
        bHomework.setTrackProfessor(professor.getId());
        bHomework.setFullName(courseName);
        bHomework.setShortName(courseShortName);

        //Syntactic Error check
        if (courseSelection.equals("") || !bHomework.setPoints(points) || !bHomework.setInstructions(instructions) || !bHomework.setExpiration(date) || !bHomework.setTitle(title)){

            getInvalidMessagge(context);
        }
        else{
            //Application Controller
            AddHomework addHomeworkAppController = new AddHomework();
            try {
                addHomeworkAppController.addHomework(bHomework, professor);
                HomeworkAddedMessage(context);

                //Notify the Homework Adapter
                if(homeworkList != null && homeworksAdapter != null){
                    homeworkList.add(bHomework);
                    homeworksAdapter.notifyDataSetChanged();
                }
                dialog.dismiss();
            } catch (GenericException e) {
                e.printStackTrace();
                errorMessage(context, e.getMessage());
            }
        }
    }


    private void HomeworkAddedMessage(Context context){
        Toast.makeText(context, "Homework added", Toast.LENGTH_SHORT).show();
    }


}
