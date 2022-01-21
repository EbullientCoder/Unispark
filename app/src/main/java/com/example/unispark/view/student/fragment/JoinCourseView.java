package com.example.unispark.view.student.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.student.JoinCourseGuiController;
import com.example.unispark.viewadapter.CoursesAdapter;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;


import java.util.List;

public class JoinCourseView extends DialogFragment
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener {


    //Dismiss Button
    ImageButton btnDismiss;
    //Courses
    RecyclerView rvCourses;
    List<BeanCourse> beanAvailableCourses;
    CoursesAdapter coursesAdapter;
    CoursesAdapter joinedCoursesAdapter;

    //Bean
    BeanLoggedStudent student;
    List<BeanCourse> joinedCourses;

    //Gui controller
    private JoinCourseGuiController joinCourseGuiController;


    //Constructor
    public JoinCourseView(BeanLoggedStudent student,
                          CoursesAdapter joinedCoursesAdapter,
                          List<BeanCourse> joinedCourses){
        this.student = student;
        this.joinedCoursesAdapter = joinedCoursesAdapter;
        this.joinedCourses = joinedCourses;
        this.joinCourseGuiController = new JoinCourseGuiController();
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
        //Gui Controller
        beanAvailableCourses = joinCourseGuiController.showAvaliableCourses(student);
        coursesAdapter = new CoursesAdapter(beanAvailableCourses, this, this, "JOIN");
        rvCourses.setAdapter(coursesAdapter);

        return rootView;
    }



    //On Course Click
    @Override
    public void onCourseClick(int position) {
        joinCourseGuiController.showCourseDetails(getContext(), beanAvailableCourses.get(position));
    }

    //On JoinCourse Click
    @Override
    public void onButtonClick(int position) {

        joinCourseGuiController.joinCourse(getDialog(), getContext(), student, beanAvailableCourses, joinedCourses, position, joinedCoursesAdapter);
    }

}
