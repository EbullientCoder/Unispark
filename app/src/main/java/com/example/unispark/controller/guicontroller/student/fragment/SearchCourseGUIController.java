package com.example.unispark.controller.guicontroller.student.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;

import com.example.unispark.controller.applicationcontroller.course.MenageCourses;
import com.example.unispark.controller.guicontroller.details.DetailsCourseGUIController;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.CourseModel;


import java.util.List;

public class SearchCourseGUIController extends DialogFragment
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener {


    //Dismiss Button
    ImageButton btnDismiss;
    //Courses
    RecyclerView rvCourses;
    List<BeanCourse> beanAvailableCourses;
    CoursesAdapter coursesAdapter;
    //Get Student Model
    BeanLoggedStudent student;
    CoursesAdapter joinedCoursesAdapter;
    List<BeanCourse> joinedCourses;


    //Constructor
    public SearchCourseGUIController(BeanLoggedStudent student,
                                     CoursesAdapter joinedCoursesAdapter,
                                     List<BeanCourse> joinedCourses){
        this.student = student;
        this.joinedCoursesAdapter = joinedCoursesAdapter;
        this.joinedCourses = joinedCourses;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search_course, container, false);
        getDialog().setTitle("Simple Dialog");


        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //Courses
        rvCourses = (RecyclerView) rootView.findViewById(R.id.rv_choose_course);
        //Application Controller
        MenageCourses getCoursesController = new MenageCourses();
        beanAvailableCourses = getCoursesController.getAvaliableCourses(student);
        coursesAdapter = new CoursesAdapter(beanAvailableCourses, this, this, "JOIN");
        rvCourses.setAdapter(coursesAdapter);

        return rootView;
    }




    //On Course Click
    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(getContext(), DetailsCourseGUIController.class);
        intent.putExtra("Course", beanAvailableCourses.get(position));
        startActivity(intent);
    }

    //On JoinCourse Click
    @Override
    public void onButtonClick(int position) {

        //Application Controller
        MenageCourses joinCourseAppController = new MenageCourses();
        try {
            joinCourseAppController.joinCourse(student, beanAvailableCourses.get(position));

            //Notify the Joined Courses Adapter
            joinedCourses.add(0, beanAvailableCourses.get(position));
            joinedCoursesAdapter.notifyDataSetChanged();

            //Remove Course from the Available Courses
            beanAvailableCourses.remove(position);

            dismiss();
        } catch ( GenericException e) {
            e.printStackTrace();
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
