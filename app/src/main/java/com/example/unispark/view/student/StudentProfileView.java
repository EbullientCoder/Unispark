package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.bean.course.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;

import com.example.unispark.controller.guicontroller.student.MenageStudentProfileGuiController;
import com.example.unispark.viewadapter.CoursesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

public class StudentProfileView extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener{

    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Student
    ImageView imgProfile;
    TextView txtFullName;
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

    //Get Intent Extras
    Bundle extras;

    //Bean
    BeanLoggedStudent student;
    List<BeanCourse> bCourses;

    //Gui Controller
    private MenageStudentProfileGuiController profileGuiController;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        this.profileGuiController = new MenageStudentProfileGuiController();

        //Getting User Object
        extras = getIntent().getExtras();


        student = (BeanLoggedStudent) extras.getSerializable("UserObject");


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

                //Menu Gui Controller
                profileGuiController.selectNextView(student, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Student Picture
        imgProfile = findViewById(R.id.img_user_image);
        imgProfile.setImageResource(student.getProfilePicture());

        //Student Name
        txtFullName = findViewById(R.id.txt_user_fullname);
        txtFullName.setText(student.getFirstName() + " " + student.getLastName());



        //Use Case: Calculate Averages
        //Arithmetic Average
        aAverage = findViewById(R.id.txt_arithmetic_average_number);
        aCircleAverage = findViewById(R.id.avg_arithmetic_average);
        //Gui Controller
        float average = profileGuiController.calculateArithmeticAverage(student);
        int cAverage = profileGuiController.calculateGraphicArithmeticAverage(average);
        aAverage.setText(String.format("%.02f", average));
        aCircleAverage.setProgress(cAverage, false);

        //Weighted Average
        wAverage = findViewById(R.id.txt_weighted_average_number);
        wCircleAverage = findViewById(R.id.avg_weighted_average);
        //Application Controller
        average = profileGuiController.calculateWeightedAverage(student);
        cAverage = profileGuiController.calculateGraphicWeightedAverage(average);
        wAverage.setText(String.format("%.02f", average));
        wCircleAverage.setProgress(cAverage, false);


        //Student Courses
        rvCourses = findViewById(R.id.rv_courses);
        //Gui Controller
        bCourses = profileGuiController.showCourses(student);
        coursesAdapter = new CoursesAdapter(bCourses, this, this, "LEAVE");
        rvCourses.setAdapter(coursesAdapter);



        //Open SearchCourse Fragment
        addCourse = findViewById(R.id.btn_add_course);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gui controller
                profileGuiController.showJoinCourses(getSupportFragmentManager(), student, coursesAdapter, bCourses);

            }
        });

    }


    //On Course Click
    @Override
    public void onCourseClick(int position) {
        profileGuiController.showCourseDetails(getApplicationContext(), bCourses.get(position));
    }


    //On LeaveCourse Click
    @Override
    public void onButtonClick(int position) {

        //Gui Controller
        profileGuiController.leaveCourse(getApplicationContext(), student, bCourses, position, coursesAdapter);
    }
}