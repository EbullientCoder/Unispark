package com.example.unispark.controller.student.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

public class SearchCourseFragment extends DialogFragment implements CoursesAdapter.OnCourseClickListener{
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Courses
    RecyclerView rvCourses;
    List<CourseModel> coursesItem;

    private static final String YEAR = "2020/2021";

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
        coursesItem = new ArrayList<>();

        CourseModel courseModel1 = new CourseModel("1", "ARL","Automatica e Robotica Lab.", YEAR, "12.0", "Winter", "https://www.google.com");


        coursesItem.add(courseModel1);



        rvCourses.setAdapter(new CoursesAdapter(coursesItem, this, "JOIN"));

        return rootView;
    }

    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(getContext(), DetailsCourse.class);
        intent.putExtra("Course", coursesItem.get(position));
        startActivity(intent);
    }
}
