package com.example.unispark.view.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.professor.ManageProfileGuiController;
import com.example.unispark.viewadapter.CoursesAdapter;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorProfileView extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener{

    //Attributes
    //Menu
    ImageButton menuButton;
    //Floating Button
    FloatingActionButton btnAdd;
    FloatingActionButton btnExam;
    TextView txtExam;
    FloatingActionButton btnHomework;
    TextView txtHomework;
    FloatingActionButton btnCommunication;
    TextView txtCommunication;
    Boolean isOpen;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Get Intent Extras
    Bundle extras;
    //Set Intent Extras
    ImageView imgProfImage;
    TextView txtProfName;
    TextView txtWebsite;
    //Courses
    RecyclerView rvCourses;
    CoursesAdapter coursesAdapter;



    //Bean
    BeanLoggedProfessor bProfessor;
    List<BeanCourse> beanCourseList;


    //Gui Controller
    private ManageProfileGuiController profileGuiController;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_profile);

        this.profileGuiController = new ManageProfileGuiController();

        //Getting User Object
        extras = getIntent().getExtras();
        bProfessor = (BeanLoggedProfessor) extras.getSerializable("UserObject");


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.professor_profile);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                profileGuiController.selectNextView(bProfessor, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Button: Add Homework - Communication
        isOpen = false;

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                isOpen = profileGuiController.expandButton(isOpen, btnExam, btnCommunication, btnHomework,
                        txtExam, txtCommunication, txtHomework, btnAdd);
            }
        });


        //Button: Add Exam
        txtExam = findViewById(R.id.txt_add_exam);
        txtExam.setVisibility(View.GONE);

        btnExam = findViewById(R.id.btn_add_exam);
        btnExam.setImageTintList(null);
        btnExam.setVisibility(View.GONE);
        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.showAddExam(getSupportFragmentManager(), bProfessor);
            }
        });
        //Button: Add Homework
        txtHomework = findViewById(R.id.txt_add_homework);
        txtHomework.setVisibility(View.GONE);

        btnHomework = findViewById(R.id.btn_add_homework);
        btnHomework.setImageTintList(null);
        btnHomework.setVisibility(View.GONE);
        btnHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.showAddHomework(getSupportFragmentManager(), bProfessor);
            }
        });


        //Button: Add Communication
        txtCommunication = findViewById(R.id.txt_add_communication);
        txtCommunication.setVisibility(View.GONE);

        btnCommunication = findViewById(R.id.btn_add_communication);
        btnCommunication.setImageTintList(null);
        btnCommunication.setVisibility(View.GONE);
        btnCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.showAddCommunication(getSupportFragmentManager(), bProfessor);
            }
        });



        //Get Parameters
        int imageID = bProfessor.getProfilePicture();
        String firstname = bProfessor.getFirstName();
        String lastname = bProfessor.getLastName();
        String website = bProfessor.getWebsite();

        //Display Parameters
        imgProfImage = findViewById(R.id.img_professor_profile_image);
        imgProfImage.setImageResource(imageID);
        txtProfName = findViewById(R.id.txt_professor_fullname);
        txtProfName.setText(firstname + ' ' + lastname);
        txtWebsite = findViewById(R.id.txt_professor_website);
        txtWebsite.setText(website);
        //Clickable Website
        txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.goToLink(getApplicationContext(), website);
            }
        });

        //Courses
        rvCourses = findViewById(R.id.rv_professor_courses);

        //Gui Controller
        beanCourseList = profileGuiController.showCourses(bProfessor);

        coursesAdapter = new CoursesAdapter(beanCourseList, this, "PROFESSOR");
        rvCourses.setAdapter(coursesAdapter);
    }



    //Course Click
    @Override
    public void onCourseClick(int position) {
        profileGuiController.showCourseDetails(getApplicationContext(), beanCourseList.get(position));
    }
}