package com.example.unispark.controller.guicontroller.professor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.course.MenageCourses;

import java.util.List;

public class AddItemGuiController {

    public List<BeanCourse> showCourses(BeanLoggedProfessor professor){
        List<BeanCourse> courses;

        MenageCourses getCoursesController = new MenageCourses();
        courses = getCoursesController.getCourses(professor);

        return courses;
    }

    public List<String> getCoursesNames(BeanLoggedProfessor professor){
        List<String> coursesNames;

        MenageCourses coursesGuiController = new MenageCourses();
        coursesNames = coursesGuiController.getCoursesNames(professor);

        return coursesNames;
    }

    public void showDateDialog(Context context, DatePickerDialog.OnDateSetListener dateListener, int year, int month, int day){

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                context,
                android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                dateListener,
                year,
                month,
                day);

        datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        datePickerDialog.show();
    }


    public void getInvalidMessagge(Context context){
        Toast.makeText(context, "All fields are required", Toast.LENGTH_SHORT).show();
    }


    public void errorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
