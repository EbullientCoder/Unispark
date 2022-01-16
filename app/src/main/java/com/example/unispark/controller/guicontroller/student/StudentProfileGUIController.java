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
import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.average.CalculateAverage;

import com.example.unispark.controller.applicationcontroller.course.MenageCourses;
import com.example.unispark.controller.guicontroller.menu.RightButtonMenu;
import com.example.unispark.controller.guicontroller.student.fragment.SearchCourseGUIController;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.guicontroller.details.DetailsCourseGUIController;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

public class StudentProfileGUIController extends AppCompatActivity
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
    List<BeanCourse> bCourses;
    //Search Course
    ImageButton addCourse;
    //Fragment Course
    SearchCourseGUIController searchCourseFragment;
    //Get Intent Extras
    Bundle extras;
    BeanLoggedStudent student;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_profile);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (BeanLoggedStudent) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightButtonMenu rightMenuAppController = new RightButtonMenu();

                //Serve un modo per determinare il giorno e la notte.
                rightMenuAppController.dayColor(getApplicationContext());
                rightMenuAppController.nightColor(getApplicationContext());
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
                BottomNavigationMenuGuiController bottomMenuAppController = new BottomNavigationMenuGuiController();

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity(student, getApplicationContext(), item.getItemId());
                startActivity(intent);
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
        //Application Controller
        CalculateAverage averageAppController = new CalculateAverage();
        float average = averageAppController.arithmeticAverage(student.getVerbalizedExams());
        int cAverage = averageAppController.graphicArithmeticAverage(average);
        aAverage.setText(String.format("%.02f", average));
        aCircleAverage.setProgress(cAverage, false);

        //Weighted Average
        wAverage = findViewById(R.id.txt_weighted_average_number);
        wCircleAverage = findViewById(R.id.avg_weighted_average);
        //Application Controller
        average = averageAppController.weightedAverage(student.getVerbalizedExams());
        cAverage = averageAppController.graphicWeightedAverage(average);
        wAverage.setText(String.format("%.02f", average));
        wCircleAverage.setProgress(cAverage, false);



        //Use Case: Search Course
        //Open SearchCourse Fragment
        addCourse = findViewById(R.id.btn_add_course);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCourseFragment = new SearchCourseGUIController(student, coursesAdapter);
                searchCourseFragment.show(getSupportFragmentManager(), "SearchCourse");
            }
        });



        //Student Courses
        rvCourses = findViewById(R.id.rv_courses);
        MenageCourses getCoursesController = new MenageCourses();
        bCourses = getCoursesController.getCourses(student);

        coursesAdapter = new CoursesAdapter(bCourses, this, this, "LEAVE");
        rvCourses.setAdapter(coursesAdapter);
    }



    //On Course Click
    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsCourseGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Course", bCourses.get(position));

        startActivity(intent);
    }

    //On LeaveCourse Click
    @Override
    public void onButtonClick(int position) {
        //Application Controller
        MenageCourses leaveCourseAppController = new MenageCourses();
        try {
            leaveCourseAppController.leaveCourse(student, bCourses.get(position), position);
            bCourses.remove(position);
            //Notify changed dimension to the Adapter
            coursesAdapter.notifyItemRemoved(position);
       } catch (GenericException | ExamBookedException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}