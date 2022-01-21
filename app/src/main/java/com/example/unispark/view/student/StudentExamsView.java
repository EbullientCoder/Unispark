package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.student.MenageStudentExamsGuiController;
import com.example.unispark.viewadapter.exams.ExamAdapter;
import com.example.unispark.bean.BeanExamType;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentExamsView extends AppCompatActivity
implements ExamAdapter.OnBookExamClickListener,
        ExamAdapter.OnLeaveExamClickListener {

    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Menu ExamModel Page
    ImageButton btnPageRight;
    ImageButton btnPageLeft;
    //Exams List
    TextView examsTitle;
    RecyclerView rvExams;
    ExamAdapter examAdapter;
    //Get Intent Extras
    Bundle extras;

    //Bean
    BeanLoggedStudent bStudent;
    List<BeanExamType> bExams;
    int page;

    private MenageStudentExamsGuiController examsGuiController;


    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        this.examsGuiController  = new MenageStudentExamsGuiController();


        //Getting User Object
        extras = getIntent().getExtras();
        bStudent = (BeanLoggedStudent) extras.getSerializable("UserObject");


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.exams);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Menu Gui Controller
                examsGuiController.selectNextView(bStudent, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);

                return true;
            }
        });


        //ExamModel Page Title
        examsTitle = findViewById(R.id.txt_exams_title);
        //ExamModel List
        rvExams = findViewById(R.id.rv_exams);


        page = 0;
        //Right Click
        btnPageRight = findViewById(R.id.btn_exams_next);
        btnPageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                page = examsGuiController.getNextPageExams(page);
                pageMenu(bStudent);
            }
        });
        //Left Click
        btnPageLeft = findViewById(R.id.btn_exams_previusly);
        btnPageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                page = examsGuiController.getPrevPageExams(page);
                pageMenu(bStudent);
            }
        });

        pageMenu(bStudent);
    }


    //Gui Controller and Adapter
    private void pageMenu(BeanLoggedStudent bStudent){

        bExams = examsGuiController.showExams(page, examsTitle, bStudent);

        examAdapter = new ExamAdapter(bExams, this, this);
        rvExams.setAdapter(examAdapter);
    }


    //On BookExam Click
    @Override
    public void onBookBtnClick(int position) {

        //Gui controller
        examsGuiController.bookExam(getApplicationContext(), bStudent, bExams, position, examAdapter);
    }

    //On LeaveExam Click
    @Override
    public void onLeaveBtnClick(int position) {

        //Gui Controller
        examsGuiController.leaveExam(getApplicationContext(), bStudent, bExams, position, examAdapter);

    }
}