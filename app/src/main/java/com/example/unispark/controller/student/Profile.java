package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.unispark.R;
import com.example.unispark.controller.student.fragment.SearchCourseFragment;
import com.example.unispark.model.CourseModel;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity implements CoursesAdapter.OnCourseClickListener {

    //Attributes
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Courses
    RecyclerView rvCourses;
    List<CourseModel> coursesItem;
    CoursesAdapter coursesAdapter;
    //Search Course
    ImageButton addCourse;
    //Fragment Course
    SearchCourseFragment searchCourseFragment;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;

    private static final String YEAR = "2020/2021";


    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Getting User Object
        extras = getIntent().getExtras();
        //student = (StudentModel) extras.getSerializable("UserObject");
        student = null;


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), student));
                overridePendingTransition(0, 0);
                return true;
            }
        });

        //Open Fragment: Search Course
        addCourse = findViewById(R.id.btn_add_course);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCourseFragment = new SearchCourseFragment();
                searchCourseFragment.show(getSupportFragmentManager(), "SearchCourse");
            }
        });

        //Courses
        rvCourses = findViewById(R.id.rv_courses);
        coursesItem = new ArrayList<>();

        CourseModel courseModel1 = new CourseModel("0", "ARL", "Automatica e Robotica Lab", YEAR, "12.0","Winter", "https://www.google.com");
        CourseModel courseModel2 = new CourseModel("1", "ISPW", "Ing. del Software e prog. Web", YEAR, "12.0", "Winter", "https://www.google.com");
        CourseModel courseModel3 = new CourseModel("2", "CA","Controlli Automatici", YEAR, "9.0", "Winter", "https://www.google.com");
        CourseModel courseModel4 = new CourseModel("3", "CE","Calcolatori Elettronici", YEAR, "9.0", "Winter", "https://www.google.com");

        coursesItem.add(courseModel1);
        coursesItem.add(courseModel2);
        coursesItem.add(courseModel3);
        coursesItem.add(courseModel4);


        coursesAdapter = new CoursesAdapter(coursesItem, this, "LEAVE");
        rvCourses.setAdapter(coursesAdapter);
    }

    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(this, DetailsCourse.class);
        intent.putExtra("Course", coursesItem.get(position));
        startActivity(intent);
    }
}