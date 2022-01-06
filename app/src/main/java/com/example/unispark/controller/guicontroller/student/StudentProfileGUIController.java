package com.example.unispark.controller.guicontroller.student;

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
import com.example.unispark.controller.applicationcontroller.average.CalculateAverage;
import com.example.unispark.controller.applicationcontroller.courses.ShowJoinedCourses;
import com.example.unispark.controller.applicationcontroller.menu.RightButtonMenu;
import com.example.unispark.controller.student.fragment.SearchCourseFragment;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.VerbalizedExamModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

public class StudentProfileGUIController extends AppCompatActivity{

    //Attributes
    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Averages
    TextView aAverage;
    CircularProgressIndicator aCircleAverage;
    TextView wAverage;
    CircularProgressIndicator wCircleAverage;
    //Courses
    RecyclerView rvCourses;
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


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightButtonMenu rightMenuAppController = new RightButtonMenu(getApplicationContext());

                //Serve un modo per determinare il giorno e la notte.
                rightMenuAppController.dayColor();
                rightMenuAppController.nightColor();
            }
        });



        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.profile);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Menu Applicative Controller
                BottomNavigationMenu bottomMenuAppController = new BottomNavigationMenu(student, getApplicationContext(), item.getItemId());

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity();
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });



        //Use Case: Calculate Averages
        //Arithmetic Average
        aAverage = findViewById(R.id.txt_arithmetic_average_number);
        aCircleAverage = findViewById(R.id.avg_arithmetic_average);
        //Application Controller
        CalculateAverage averageAppController = new CalculateAverage(student, getApplicationContext());
        float average = averageAppController.arithmeticAverage();
        int cAverage = averageAppController.graphicArithmeticAverage(average);
        aAverage.setText(String.format("%.02f", average));
        aCircleAverage.setProgress(cAverage, false);

        //Weighted Average
        wAverage = findViewById(R.id.txt_weighted_average_number);
        wCircleAverage = findViewById(R.id.avg_weighted_average);
        //Application Controller
        average = averageAppController.weightedAverage();
        cAverage = averageAppController.graphicWeightedAverage(average);
        wAverage.setText(String.format("%.02f", average));
        wCircleAverage.setProgress(cAverage, false);



        //Use Case: Search Course
        //Open SearchCourse Fragment
        addCourse = findViewById(R.id.btn_add_course);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCourseFragment = new SearchCourseFragment(student, coursesAdapter);
                searchCourseFragment.show(getSupportFragmentManager(), "SearchCourse");
            }
        });



        //Student Picture
        imgProfile = findViewById(R.id.img_user_image);
        imgProfile.setImageResource(student.getProfilePicture());

        //Student Name
        txtFullName = findViewById(R.id.txt_user_fullname);
        txtFullName.setText(student.getFirstName() + " " + student.getLastName());

        //Student Courses
        rvCourses = findViewById(R.id.rv_courses);
        //Application Controller
        ShowJoinedCourses joinedCoursesAppController = new ShowJoinedCourses(student, getApplicationContext());
        coursesAdapter = joinedCoursesAppController.setCoursesAdapter();
        rvCourses.setAdapter(coursesAdapter);
    }
}