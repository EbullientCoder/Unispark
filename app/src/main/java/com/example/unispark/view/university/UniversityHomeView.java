package com.example.unispark.view.university;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.university.ManageUniHomeGuiController;
import com.example.unispark.viewadapter.LessonAdapter;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedUniversity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UniversityHomeView extends AppCompatActivity implements
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

    //Schedules
    TextView txtScheduleTitle;
    RecyclerView rvSchedules;
    LessonAdapter lessonAdapter;

    //Get Intent Extras
    Bundle extras;


    //Bean
    BeanLoggedUniversity bUniversity;
    List<BeanUniCommunication> beanUniCommunicationList;
    List<BeanLesson> bLessons;

    int index = 0;


    //Gui Controller
    private ManageUniHomeGuiController uniHomeGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_home);

        this.uniHomeGuiController = new ManageUniHomeGuiController();

        //Getting User Object
        extras = getIntent().getExtras();
        bUniversity = (BeanLoggedUniversity) extras.getSerializable("UserObject");



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

                isOpen = uniHomeGuiController.expandButton(isOpen, btnCommunication, btnSchedule, txtCommunication, txtSchedule, btnAdd);
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

                uniHomeGuiController.showAddCommunication(getSupportFragmentManager(), bUniversity, uniCommunicationsAdapter, beanUniCommunicationList);
            }
        });

        //Button: Add StudentScheduleGUIController
        txtSchedule = findViewById(R.id.txt_add_schedule);
        txtSchedule.setVisibility(View.GONE);

        btnSchedule = findViewById(R.id.btn_add_schedule);
        btnSchedule.setImageTintList(null);
        btnSchedule.setVisibility(View.GONE);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uniHomeGuiController.showAddSchedule(getSupportFragmentManager(), bUniversity, lessonAdapter, bLessons, index);
            }
        });



        //Assigned Communications
        rvUniCommunications = findViewById(R.id.rv_assigned_communications);
        //Gui Controller
        beanUniCommunicationList = uniHomeGuiController.showCommunications();
        uniCommunicationsAdapter = new UniCommunicationsAdapter(beanUniCommunicationList, this);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Schedules
        txtScheduleTitle = findViewById(R.id.txt_schedule_day);
        rvSchedules = findViewById(R.id.rv_schedules);
        //Button: Next Course
        btnNextCourse = findViewById(R.id.btn_course_next);
        btnNextCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                index = uniHomeGuiController.getCorrectIndex(index);
                showSchedule(index);
            }
        });

        showSchedule(0);
    }


    //Gui Controller: Get Lessons
    private void showSchedule(int index){

        bLessons = uniHomeGuiController.showSchedule(index, txtScheduleTitle,
                bUniversity.getFaculties(), txtSchedule, rvSchedules);

        lessonAdapter = new LessonAdapter(bLessons, this, "UNIVERSITY");
        rvSchedules.setAdapter(lessonAdapter);

    }


    //On UniversityCommunications Click
    @Override
    public void onUniClick(int position) {

        uniHomeGuiController.showDetailsCommunication(getApplicationContext(), beanUniCommunicationList.get(position));
    }


    //On DeleteLesson Button Click
    @Override
    public void onDelBtnClick(int position) {

        uniHomeGuiController.deleteLesson(getApplicationContext(), bLessons, position, lessonAdapter, rvSchedules);
    }





}