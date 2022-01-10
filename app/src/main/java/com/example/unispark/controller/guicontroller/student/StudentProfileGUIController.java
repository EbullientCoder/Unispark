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
import com.example.unispark.controller.applicationcontroller.menu.RightButtonMenu;
import com.example.unispark.controller.student.fragment.SearchCourseFragment;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;

public class StudentProfileGUIController extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener{

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
    List<CourseModel> coursesItem;
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
                BottomNavigationMenu bottomMenuAppController = new BottomNavigationMenu();

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
                searchCourseFragment = new SearchCourseFragment(student, coursesAdapter);
                searchCourseFragment.show(getSupportFragmentManager(), "SearchCourse");
            }
        });



        //Student Courses
        rvCourses = findViewById(R.id.rv_courses);
        coursesItem = student.getCourses();
        coursesAdapter = new CoursesAdapter(coursesItem, this, this, "LEAVE");
        rvCourses.setAdapter(coursesAdapter);
    }



    //On Course Click
    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsCourse.class);
        //Pass Items to the new Activity
        intent.putExtra("Course", coursesItem.get(position));

        startActivity(intent);
    }

    //On LeaveCourse Click
    @Override
    public void onButtonClick(int position) {
        //Remove Course Joined from DB
        boolean leaveCourse = CourseDAO.leaveCourse(student.getId(), coursesItem.get(position).getFullName());

        if(leaveCourse){
            //Remove Course from Student's joined Courses
            coursesItem.remove(position);
            student.setCourses(coursesItem);

            //Notify changed dimension to the Adapter
            coursesAdapter.notifyItemRemoved(position);
        }
        else Toast.makeText(getApplicationContext(), "Cannot leave course: EXAM BOOKED", Toast.LENGTH_SHORT).show();
    }
}