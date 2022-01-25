package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.unispark.R;
import com.example.unispark.Session;
import com.example.unispark.controller.guicontroller.student.ManageStudentExamsGuiController;
import com.example.unispark.viewadapter.exams.ExamAdapter;
import com.example.unispark.bean.exams.BeanExamType;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentExamsView extends AppCompatActivity
implements ExamAdapter.OnBookExamClickListener,
        ExamAdapter.OnLeaveExamClickListener {

    //Menu
    private ImageButton menuButton;
    //Bottom Menu Elements
    private BottomNavigationView bottomNavigationView;
    //Menu ExamModel Page
    private ImageButton btnPageRight;
    private ImageButton btnPageLeft;
    //Exams List
    private TextView examsTitle;
    private RecyclerView rvExams;
    private ExamAdapter examAdapter;


    //Gui Controller
    private ManageStudentExamsGuiController examsGuiController;


    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        this.examsGuiController  = new ManageStudentExamsGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.examAdapter = new ExamAdapter(this, this);


        //Bottom Navigation Menu
        this.bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        this.bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        this.bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        this.bottomNavigationView.setSelectedItemId(R.id.exams);
        //Click Listener
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Menu Gui Controller
                examsGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });


        //Exam Page Title
        this.examsTitle = findViewById(R.id.txt_exams_title);
        //Exam List
        this.rvExams = findViewById(R.id.rv_exams);


        //Gui Controller
        this.examsGuiController.showExams();

        //Right Click
        this.btnPageRight = findViewById(R.id.btn_exams_next);
        this.btnPageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                examsGuiController.showNextPageExams();
            }
        });
        //Left Click
        this.btnPageLeft = findViewById(R.id.btn_exams_previusly);
        this.btnPageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                examsGuiController.showPrevPageExams();
            }
        });


    }


    //On BookExam Click
    @Override
    public void onBookBtnClick(int position) {

        //Gui controller
        this.examsGuiController.bookExam(position);
    }

    //On LeaveExam Click
    @Override
    public void onLeaveBtnClick(int position) {

        //Gui Controller
        this.examsGuiController.leaveExam(position);

    }

    public void setExamsTitle(String content) {
        this.examsTitle.setText(content);
    }

    public void setExamAdapter(List<BeanExamType> beanExamTypes) {
        this.examAdapter.setbExams(beanExamTypes);
        this.rvExams.setAdapter(this.examAdapter);
    }

    public void notifyDataChanged(int position){
        this.examAdapter.notifyItemRemoved(position);
    }

    public void setMessage(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}