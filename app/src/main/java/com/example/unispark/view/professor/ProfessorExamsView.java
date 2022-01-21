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
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.professor.ManageExamsGuiController;
import com.example.unispark.viewadapter.exams.ExamAdapter;
import com.example.unispark.bean.BeanExamType;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorExamsView extends AppCompatActivity
        implements ExamAdapter.OnViewExamClickListener{


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
    //Menu ExamModel Page
    TextView examsTitle;
    //Get Intent Extras
    Bundle extras;
    //ExamModel
    RecyclerView rvExams;
    ExamAdapter examAdapter;



    //Bean
    BeanLoggedProfessor bProfessor;
    List<BeanExamType> examsItem;


    //Gui Controller
    private ManageExamsGuiController examsGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_exams);

        this.examsGuiController = new ManageExamsGuiController();

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
        bottomNavigationView.setSelectedItemId(R.id.professor_exams);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                examsGuiController.selectNextView(bProfessor, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });


        //ExamModel Page Title
        examsTitle = findViewById(R.id.txt_professor_exams_title);

        //ExamModel List
        rvExams = findViewById(R.id.rv_professor_exams);


        //Gui Controller
        examsItem = examsGuiController.showExams(bProfessor);
        examAdapter = new ExamAdapter(examsItem, this);
        rvExams.setAdapter(examAdapter);



        //Button: Add Homework - Communication
        isOpen = false;

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOpen = examsGuiController.expandButton(isOpen, btnExam, btnCommunication, btnHomework,
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
                examsGuiController.showAddExam(getSupportFragmentManager(), bProfessor);
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
                examsGuiController.showAddHomework(getSupportFragmentManager(), bProfessor);
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
                examsGuiController.showAddCommunication(getSupportFragmentManager(), bProfessor);
            }
        });
    }



    //On ViewExam Button Click
    @Override
    public void onViewBtnClick(int position) {
        examsGuiController.showVerbalizeExam(getApplicationContext(), examsItem.get(position).getBeanExamType());
    }
}