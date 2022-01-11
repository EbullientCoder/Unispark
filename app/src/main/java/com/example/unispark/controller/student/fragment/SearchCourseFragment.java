package com.example.unispark.controller.student.fragment;

import static com.example.unispark.database.dao.CourseDAO.joinCourse;

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
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;

import java.util.ArrayList;
import java.util.List;

public class SearchCourseFragment extends DialogFragment
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener {
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Courses
    RecyclerView rvCourses;
    List<CourseModel> coursesItem;
    CoursesAdapter coursesAdapter;
    //Get Student Model
    StudentModel student;
    CoursesAdapter joinedCoursesAdapter;

    private static final String YEAR = "2020/2021";


    //Methods
    //Constructor
    public SearchCourseFragment(StudentModel student, CoursesAdapter joinedCoursesAdapter){
        this.student = student;
        this.joinedCoursesAdapter = joinedCoursesAdapter;
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

        //Get list of available courses to join for the student marked by faculty and that
        // are not in the student course list(Implement a method)
        List<CourseModel> courses = student.getCourses();
        List<String> courseNames = null;
        if(courses != null){
            courseNames = new ArrayList<>(courses.size());
            for (int i = 0; i < courses.size(); i++) courseNames.add(courses.get(i).getFullName());
        }
        coursesItem = CourseDAO.selectAvailableCourses(student.getFaculty(), student.getUniYear(), courseNames);
        coursesAdapter = new CoursesAdapter(coursesItem, this, this, "JOIN");
        rvCourses.setAdapter(coursesAdapter);

        return rootView;
    }




    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(getContext(), DetailsCourse.class);
        intent.putExtra("Course", coursesItem.get(position));
        startActivity(intent);
    }

    @Override
    public void onButtonClick(int position) {
        List<CourseModel> joinedCourses;

        //Show Course's Homeworks
        CourseDAO.joinCourse(student.getId(), coursesItem.get(position).getFullName());

        //Add Course to the Student's Joined Courses
        joinedCourses = student.getCourses();
        if (joinedCourses == null) joinedCourses = new ArrayList<>();
        joinedCourses.add(0, coursesItem.get(position));
        student.setCourses(joinedCourses);

        //Notify the Joined Courses Adapter
        joinedCoursesAdapter.notifyDataSetChanged();

        dismiss();
    }
}
