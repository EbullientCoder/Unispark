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

public class Profile extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener,
        CoursesAdapter.OnCourseBtnClickListener {

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


        //Average
        List<VerbalizedExamModel> exams = student.getVerbalizedExams();
        float average = 0;
        float CFU = 0;
        //Arithmetic Average
        if(exams != null){
            for(int i = 0; i < exams.size(); i++) average += Double.parseDouble(exams.get(i).getResult());
            average = average/exams.size();
        }
        aAverage = findViewById(R.id.txt_arithmetic_average_number);
        aAverage.setText(String.format("%.02f", average));
        average = (average * 100 / 35);
        aCircleAverage = findViewById(R.id.avg_arithmetic_average);
        aCircleAverage.setProgress((int) average, false);
        //Weighted Average
        average = 0;
        if(exams != null){
            for(int i = 0; i < exams.size(); i++){
                average += (Double.parseDouble(exams.get(i).getResult()) * Double.parseDouble(exams.get(i).getCFU()));
                CFU += Double.parseDouble(exams.get(i).getCFU());
            }
            average = average/CFU;
        }
        wAverage = findViewById(R.id.txt_weighted_average_number);
        wAverage.setText(String.format("%.02f", average));
        average = (average * 100 / 36);
        wCircleAverage = findViewById(R.id.avg_weighted_average);
        wCircleAverage.setProgress((int) average, false);



        //Open Fragment: Search Course
        addCourse = findViewById(R.id.btn_add_course);
        addCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchCourseFragment = new SearchCourseFragment(student, coursesAdapter);
                searchCourseFragment.show(getSupportFragmentManager(), "SearchCourse");
            }
        });


        //Profile Picture
        imgProfile = findViewById(R.id.img_user_image);
        imgProfile.setImageResource(student.getProfilePicture());
        //Name
        txtFullName = findViewById(R.id.txt_user_fullname);
        txtFullName.setText(student.getFirstName() + " " + student.getLastName());

        //Courses
        rvCourses = findViewById(R.id.rv_courses);
        coursesItem = student.getCourses();


        if (coursesItem  != null){
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

        //Remove Course Joined from DB
        boolean leaveCourse = CourseDAO.leaveCourse(student.getId(), coursesItem.get(position).getFullName());

        if(leaveCourse){
            //Remove Course from Student's joined Courses
            joinedCourses.remove(position);
            student.setCourses(joinedCourses);

            //Notify changed dimension to the Adapter
            coursesAdapter.notifyItemRemoved(position);
        }
        else Toast.makeText(getApplicationContext(), "Cannot leave course: EXAM BOOKED", Toast.LENGTH_SHORT).show();
    }
}