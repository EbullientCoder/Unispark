package com.example.unispark.controller.guicontroller.student;



import android.app.Dialog;
import android.content.Context;
import android.widget.Toast;

import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.exceptions.CourseAlreadyJoined;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.viewadapter.CoursesAdapter;

import java.util.List;

public class JoinCourseGuiController extends ManageStudentProfileGuiController {

    public List<BeanCourse> showAvaliableCourses(BeanLoggedStudent student){
        List<BeanCourse> courseList;
        ManageCourses getCoursesController = new ManageCourses();
        courseList = getCoursesController.getAvaliableCourses(student);


        return courseList;

    }

    public void joinCourse(Dialog dialog, Context context, BeanLoggedStudent student, List<BeanCourse> avaliableCourses, List<BeanCourse> joinedCourses, int position, CoursesAdapter coursesAdapter){

        ManageCourses joinCourseAppController = new ManageCourses();
        BeanCourse course = avaliableCourses.get(position);
        try {
            joinCourseAppController.joinCourse(student, course);

            //Notify the Joined Courses Adapter
            joinedCourses.add(0, course);
            coursesAdapter.notifyDataSetChanged();

            //Remove Course from the Available Courses
            avaliableCourses.remove(position);
            dialog.dismiss();

        } catch (GenericException | CourseDoesNotExist | CourseAlreadyJoined e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }
    }


    public void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
