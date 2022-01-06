package com.example.unispark.controller.university;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.LessonAdapter;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsUniCommunication;
import com.example.unispark.controller.professor.fragment.AddExamFragment;
import com.example.unispark.controller.university.fragment.AddScheduleFragment;
import com.example.unispark.controller.university.fragment.AddUniCommunicationFragment;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.FacultyDAO;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LessonModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.UniversityModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class UniversityHome extends AppCompatActivity implements
        UniCommunicationsAdapter.OnUniComClickListener,
        LessonAdapter.OnDelBtnClickListener {

    //Menu
    ImageButton menuButton;
    //Next Course
    ImageButton btnNextCourse;
    //Floating Button
    FloatingActionButton btnAdd;
    FloatingActionButton btnCommunication;
    TextView txtCommunication;
    FloatingActionButton btnSchedule;
    TextView txtSchedule;
    Boolean isOpen;
    //Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter uniCommunicationsAdapter;
    List<UniversityCommunicationModel> uniCommunicationsItem;
    //Schedules
    TextView txtScheduleTitle;
    RecyclerView rvSchedules;
    LessonAdapter lessonAdapter;
    List<LessonModel> schedulesItem;
    //Get Intent Extras
    Bundle extras;
    //Model
    UniversityModel university;

    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_home);

        //Getting User Object
        extras = getIntent().getExtras();
        university = (UniversityModel) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });

        //Floating Button
        isOpen = false;

        btnAdd = findViewById(R.id.btn_uni_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandButton();
            }
        });
        //Button: Add Communication
        txtCommunication = findViewById(R.id.txt_add_uni_communication);
        txtCommunication.setVisibility(View.GONE);

        btnCommunication = findViewById(R.id.btn_add_uni_communication);
        btnCommunication.setImageTintList(null);
        btnCommunication.setVisibility(View.GONE);
        btnCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUniCommunicationFragment fragment = new AddUniCommunicationFragment(university, uniCommunicationsItem, uniCommunicationsAdapter);
                fragment.show(getSupportFragmentManager(), "AddUniCommunication");
            }
        });
        //Button: Add Schedule
        txtSchedule = findViewById(R.id.txt_add_schedule);
        txtSchedule.setVisibility(View.GONE);

        btnSchedule = findViewById(R.id.btn_add_schedule);
        btnSchedule.setImageTintList(null);
        btnSchedule.setVisibility(View.GONE);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddScheduleFragment fragment = new AddScheduleFragment(university, lessonAdapter, schedulesItem);
                fragment.show(getSupportFragmentManager(), "AddSchedule");
            }
        });



        //------------------------------------------------------------------------------------------

        //Assigned Communications
        rvUniCommunications = findViewById(R.id.rv_assigned_communications);
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications("all");

        if(uniCommunicationsItem != null){
            uniCommunicationsAdapter = new UniCommunicationsAdapter(uniCommunicationsItem, this);
            rvUniCommunications.setAdapter(uniCommunicationsAdapter);
        }


        //Schedules
        txtScheduleTitle = findViewById(R.id.txt_schedule_day);
        rvSchedules = findViewById(R.id.rv_schedules);
        //Button: Next Course
        btnNextCourse = findViewById(R.id.btn_course_next);
        btnNextCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == 4) index = 0;
                else index++;

                switch (index){
                    case 0: showSchedule("MONDAY");
                    break;
                    case 1: showSchedule("TUESDAY");
                    break;
                    case 2: showSchedule("WEDNESDAY");
                    break;
                    case 3: showSchedule("THURSDAY");
                    break;
                    case 4: showSchedule("FRIDAY");
                    break;
                }
            }
        });
        showSchedule("MONDAY");
    }


    //Open Button
    public void expandButton(){
        if(!isOpen){
            //Show Buttons
            btnCommunication.show();
            btnSchedule.show();

            //Expand Floating Button
            txtCommunication.setVisibility(View.VISIBLE);
            txtSchedule.setVisibility(View.VISIBLE);

            //Rotate
            btnAdd.setRotation(45);

            isOpen = true;
        }
        else{
            //Hide Buttons
            btnCommunication.hide();
            btnSchedule.hide();

            //Hide text
            txtCommunication.setVisibility(View.GONE);
            txtSchedule.setVisibility(View.GONE);

            //Rotate
            btnAdd.setRotation(0);

            isOpen = false;
        }
    }


    //Show Schedule
    private void showSchedule(String day){
        //Set Course's Schedule
        txtScheduleTitle.setText("SCHEDULE: " + day);

        //Lessons
        List<CourseModel> courses = CourseDAO.selectAvailableCourses("Ingegneria Informatica", 3, null);
        schedulesItem = LessonsDAO.getLessons(day, courses);
        lessonAdapter = new LessonAdapter(schedulesItem, this, "UNIVERSITY");

        //Set adapter
        if(schedulesItem != null) rvSchedules.setAdapter(lessonAdapter);
    }


    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(this, DetailsUniCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", uniCommunicationsItem.get(position));
        startActivity(intent);
    }

    @Override
    public void onDelBtnClick(int position) {
        //Remove Schedule from DB
        LessonsDAO.removeLesson(schedulesItem.get(position));

        //Show Removed Schedule
        schedulesItem.remove(position);
        lessonAdapter.notifyItemRemoved(position);
        rvSchedules.setAdapter(lessonAdapter);
    }
}