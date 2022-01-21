package com.example.unispark.controller.guicontroller.student;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.average.CalculateAverage;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.exceptions.CourseNeverJoined;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.controller.guicontroller.BottomNavigationMenuGuiController;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.view.details.DetailsCourseView;
import com.example.unispark.view.student.fragment.JoinCourseView;
import com.example.unispark.viewadapter.CoursesAdapter;

import java.util.List;


public class MenageStudentProfileGuiController extends BottomNavigationMenuGuiController {



    public float calculateArithmeticAverage(BeanLoggedStudent student){
        float average;
        CalculateAverage calculateAverageController = new CalculateAverage();
        average = calculateAverageController.arithmeticAverage(student);

        return average;
    }



    public int calculateGraphicArithmeticAverage(float average){
        int cAverage;
        CalculateAverage calculateAverageController = new CalculateAverage();
        cAverage = calculateAverageController.graphicArithmeticAverage(average);

        return cAverage;

    }



    public float calculateWeightedAverage(BeanLoggedStudent student){

        float average;
        CalculateAverage calculateAverageController = new CalculateAverage();
        average = calculateAverageController.weightedAverage(student);

        return average;
    }



    //Circular Weighted Average
    public int calculateGraphicWeightedAverage(float average){
        int circularAverage;
        CalculateAverage calculateAverageController = new CalculateAverage();
        circularAverage = calculateAverageController.graphicWeightedAverage(average);

        return circularAverage;
    }



    public void showJoinCourses(FragmentManager fragmentManager, BeanLoggedStudent student, CoursesAdapter coursesAdapter, List<BeanCourse> courseList){

        JoinCourseView joinCourseFragment;
        joinCourseFragment = new JoinCourseView(student, coursesAdapter, courseList);
        joinCourseFragment.show(fragmentManager, "Search Course");
    }



    public List<BeanCourse> showCourses(BeanLoggedStudent student){
        List<BeanCourse> courseList;
        ManageCourses getCoursesController = new ManageCourses();
        courseList = getCoursesController.getCourses(student);

        return courseList;
    }



    public void showCourseDetails(Context context, BeanCourse course){
        Intent intent = new Intent(context, DetailsCourseView.class);
        intent.putExtra("Course", course);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }



    public void leaveCourse(Context context, BeanLoggedStudent student, List<BeanCourse> courseList, int position, CoursesAdapter coursesAdapter){

        ManageCourses leaveCourseAppController = new ManageCourses();
        try {
            leaveCourseAppController.leaveCourse(student, courseList.get(position), position);
            courseList.remove(position);
            //Notify changed dimension to the Adapter
            coursesAdapter.notifyItemRemoved(position);
        } catch (GenericException | ExamBookedException | CourseNeverJoined e) {
            e.printStackTrace();
            getErrorMessage(context, e.getMessage());
        }

    }



    public void getErrorMessage(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
