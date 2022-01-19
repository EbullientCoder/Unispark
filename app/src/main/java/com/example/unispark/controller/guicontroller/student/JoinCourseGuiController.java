package com.example.unispark.controller.guicontroller.student;



import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.course.MenageCourses;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.viewadapter.CoursesAdapter;

import java.util.List;

public class JoinCourseGuiController extends MenageStudentProfileGuiController {

    public List<BeanCourse> showAvaliableCourses(BeanLoggedStudent student){
        List<BeanCourse> courseList;
        MenageCourses getCoursesController = new MenageCourses();
        courseList = getCoursesController.getAvaliableCourses(student);

        return courseList;

    }

    public void joinCourse(Dialog dialog, Context context, BeanLoggedStudent student, List<BeanCourse> avaliableCourses, List<BeanCourse> joinedCourses, int position, CoursesAdapter coursesAdapter){

        MenageCourses joinCourseAppController = new MenageCourses();
        BeanCourse course = avaliableCourses.get(position);
        try {
            joinCourseAppController.joinCourse(student, course);

            //Notify the Joined Courses Adapter
            joinedCourses.add(0, course);
            coursesAdapter.notifyDataSetChanged();

            //Remove Course from the Available Courses
            avaliableCourses.remove(position);
            dialog.dismiss();

        } catch ( GenericException e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }
    }


    public void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
