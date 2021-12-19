package com.example.unispark.controller.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.CoursesAdapter;
import com.example.unispark.controller.details.DetailsCourse;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class ProfessorProfile extends AppCompatActivity implements CoursesAdapter.OnCourseClickListener {
    //Attributes
    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Get Intent Extras
    Bundle extras;
    int imageID;
    String firstname;
    String lastname;
    String website;
    //Set Intent Extras
    ImageView imgProfImage;
    TextView txtProfName;
    TextView txtWebsite;
    RecyclerView rvCourses;
    //Courses
    List<CourseModel> coursesItem;
    //Model
    ProfessorModel professor;

    private static final String YEAR = "2020/2021";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_profile);

        //Getting User Object
        extras = getIntent().getExtras();
        professor = (ProfessorModel) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.professor_profile);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), professor));
                overridePendingTransition(0, 0);
                return true;
            }
        });



        //Get Parameters
        imageID = professor.getImage();
        firstname = professor.getFirstName();
        lastname = professor.getLastName();
        website = professor.getWebsite();
        coursesItem = professor.getCourses();

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
                Uri uri = Uri.parse(website);
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
        //Courses
        rvCourses = findViewById(R.id.rv_professor_courses);
        rvCourses.setAdapter(new CoursesAdapter(coursesItem, this, "PROFESSOR"));
    }



    //Course Click
    @Override
    public void onCourseClick(int position) {
        Intent intent = new Intent(this, DetailsCourse.class);
        intent.putExtra("Course", coursesItem.get(position));
        startActivity(intent);
    }
}