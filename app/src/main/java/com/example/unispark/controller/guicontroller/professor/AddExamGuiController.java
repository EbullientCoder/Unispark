package com.example.unispark.controller.guicontroller.professor;

import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.exams.AddExam;
import com.example.unispark.exceptions.ExamAlreadyExists;
import com.example.unispark.exceptions.GenericException;

public class AddExamGuiController extends AddItemGuiController{


    public void addExam(Context context, Dialog dialog, BeanLoggedProfessor professor,
                        String courseName, String courseYear, String date, String courseSelection,
                        String hour, String cfu, String building, String classroom){

        if (courseSelection.equals("") || hour.equals("") || building.equals("") || classroom.equals("")){
            getInvalidMessagge(context);
        }

        else{

            //Bean Exam
            BeanBookExam bExam;
            bExam = new BeanBookExam();
            bExam.setId(1);
            bExam.setName(courseName);
            bExam.setYear(courseYear);
            bExam.setDate(date + hour);
            bExam.setCfu(cfu);
            bExam.setClassroom(classroom);
            bExam.setBuilding(building);


            //Application Controller
            AddExam addExamAppController = new AddExam();
            try {

                addExamAppController.addExam(bExam);
                examAddedMessage(context);
                dialog.dismiss();

            } catch (ExamAlreadyExists | GenericException e) {
                e.printStackTrace();
                errorMessage(context, e.getMessage());
            }
        }
    }


    private void examAddedMessage(Context context){
        Toast.makeText(context, "Exam added", Toast.LENGTH_SHORT).show();
    }



}
