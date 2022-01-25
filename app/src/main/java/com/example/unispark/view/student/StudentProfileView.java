package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.Session;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.student.BeanLoggedStudent;

import com.example.unispark.controller.guicontroller.student.ManageStudentProfileGuiController;
import com.example.unispark.viewadapter.CoursesAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

public class StudentProfileView extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener{

    //Menu
    private ImageButton menuButton;
    //Bottom Menu Elements
    private BottomNavigationView bottomNavigationView;
    //Student
    private ImageView imgProfile;
    private TextView txtFullName;
    //Averages
    private TextView aAverage;
    private CircularProgressIndicator aCircleAverage;
    private TextView wAverage;
    private CircularProgressIndicator wCircleAverage;
    //Courses
    private RecyclerView rvCourses;
    private CoursesAdapter coursesAdapter;
    //Search Course
    private ImageButton addCourse;


    //Gui Controller
    private ManageStudentProfileGuiController profileGuiController;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        this.profileGuiController = new ManageStudentProfileGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.coursesAdapter = new CoursesAdapter(this, this, "LEAVE");



        //Bottom Navigation Menu
        this.bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        this.bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        this.bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        this.bottomNavigationView.setSelectedItemId(R.id.profile);
        //Click Listener
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                profileGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Student Picture
        this.imgProfile = findViewById(R.id.img_user_image);
        //Student Name
        this.txtFullName = findViewById(R.id.txt_user_fullname);

        //Gui Controller
        this.profileGuiController.showProfile();


        //Use Case: Calculate Averages
        //Arithmetic Average
        this.aAverage = findViewById(R.id.txt_arithmetic_average_number);
        this.aCircleAverage = findViewById(R.id.avg_arithmetic_average);
        //Weighted Average
        this.wAverage = findViewById(R.id.txt_weighted_average_number);
        this.wCircleAverage = findViewById(R.id.avg_weighted_average);

        //Gui Controller
        this.profileGuiController.showAverages();

        //Show student Courses
        this.rvCourses = findViewById(R.id.rv_courses);
        //Gui Controller
        this.profileGuiController.showCourses();



        //Open SearchCourse Fragment
        this.addCourse = findViewById(R.id.btn_add_course);
        this.addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Gui controller
                profileGuiController.showJoinCourses();

            }
        });

    }



    //On Course Click
    @Override
    public void onCourseClick(int position) {
        this.profileGuiController.showCourseDetails(position);
    }


    //On LeaveCourse Click
    @Override
    public void onButtonClick(int position) {

        //Gui Controller
        this.profileGuiController.leaveCourse(position);
    }






    //Getters and setters
    public void setImgProfile(int content) {
        this.imgProfile.setImageResource(content);
    }

    public void setTxtFullName(String fullName) {
        this.txtFullName.setText(fullName);
    }

    public void setAverage(String aAverage, int aCircularAverage, String wAverage, int wCircularAverage){
        this.aAverage.setText(aAverage);
        this.aCircleAverage.setProgress(aCircularAverage, false);
        this.wAverage.setText(wAverage);
        this.wCircleAverage.setProgress(wCircularAverage, false);
    }

    public void setCoursesAdapter(List<BeanCourse> beanCourses) {
        this.coursesAdapter.setbCourses(beanCourses);
        this.rvCourses.setAdapter(this.coursesAdapter);
    }

    public void notifyDataChanged(int position){
        this.coursesAdapter.notifyItemRemoved(position);
    }

    public CoursesAdapter getCoursesAdapter() {
        return coursesAdapter;
    }


    public void setErrorMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}