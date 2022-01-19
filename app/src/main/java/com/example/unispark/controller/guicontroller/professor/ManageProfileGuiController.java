package com.example.unispark.controller.guicontroller.professor;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.course.MenageCourses;
import com.example.unispark.view.details.DetailsCourseView;

import java.util.List;

public class ManageProfileGuiController extends BaseProfessorGuiController {


    public void goToLink(Context context, String link){
        Uri uri = Uri.parse(link);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public List<BeanCourse> showCourses(BeanLoggedProfessor professor){
        List<BeanCourse> courseList;
        MenageCourses getCoursesController = new MenageCourses();
        courseList = getCoursesController.getCourses(professor);

        return courseList;
    }


    public void showCourseDetails(Context context, BeanCourse course){
        Intent intent = new Intent(context, DetailsCourseView.class);
        intent.putExtra("Course", course);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
