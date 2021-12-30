package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.controller.student.fragment.SearchCourseFragment;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Profile extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener {

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
    ImageView imgProfile;
    TextView txtFullName;
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
        student = (StudentModel) extras.getSerializable("UserObject");



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
                searchCourseFragment = new SearchCourseFragment(student);
                searchCourseFragment.show(getSupportFragmentManager(), "SearchCourse");
            }
        });


        //Profile Picture
        imgProfile = findViewById(R.id.img_user_image);
        imgProfile.setImageResource(student.getImageID());
        //Name
        txtFullName = findViewById(R.id.txt_user_fullname);
        txtFullName.setText(student.getFirstName() + " " + student.getLastName());

        //Courses
        rvCourses = findViewById(R.id.rv_courses);
        coursesItem = student.getCourses();

        if(coursesItem == null) Toast.makeText(getApplicationContext(), "EMPTY COURSES LIST", Toast.LENGTH_SHORT).show();
        else{
            coursesAdapter = new CoursesAdapter(coursesItem, this, this,"LEAVE");
            rvCourses.setAdapter(coursesAdapter);
        }
    }

    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(this, DetailsCourse.class);
        intent.putExtra("Course", coursesItem.get(position));
        startActivity(intent);
    }

    @Override
    public void onButtonClick(int position) {
        List<CourseModel> joinedCourses = student.getCourses();

        //Leave Course
        Toast.makeText(getApplicationContext(), coursesItem.get(position).getFullName() + ": Leaved", Toast.LENGTH_SHORT).show();

        //Remove Course Joined from DB
        CourseDAO.leaveCourse(student.getId(), coursesItem.get(position).getFullName());

        //Remove Course from Student's joined Courses
        joinedCourses.remove(position);
        student.setCourses(joinedCourses);

        //Notify changed dimension to the Adapter
        coursesAdapter.notifyItemRemoved(position);
    }
}