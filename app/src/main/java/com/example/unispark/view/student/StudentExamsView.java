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


    //Exams
    private TextView examsTitle;
    private RecyclerView rvExams;
    private ExamAdapter examAdapter;

    //Gui Controller
    private ManageStudentExamsGuiController examsGuiController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        this.examsGuiController  = new ManageStudentExamsGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.examAdapter = new ExamAdapter(this, this);


        //Bottom Navigation Menu
        BottomNavigationView bottomNavigationView;
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
                examsGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });


        //Exam Page Title
        this.examsTitle = findViewById(R.id.txt_exams_title);

        this.rvExams = findViewById(R.id.rv_exams);

        //Gui Controller
        this.examsGuiController.showExams();

        //Right Click
        ImageButton btnPageRight;
        btnPageRight = findViewById(R.id.btn_exams_next);
        btnPageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                examsGuiController.showNextPageExams();
            }
        });
        //Left Click
        ImageButton btnPageLeft;
        btnPageLeft = findViewById(R.id.btn_exams_previusly);
        btnPageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                examsGuiController.showPrevPageExams();
            }
        });


    }


    //On BookExam Click
    @Override
    public void onBookBtnClick(int position) {

        //Book Exam
        this.examsGuiController.bookExam(position);
    }

    //On LeaveExam Click
    @Override
    public void onLeaveBtnClick(int position) {

        //Leave Exam
        this.examsGuiController.leaveExam(position);

    }


   //Setters used by the GuiController in order to set the information of the View
    public void setExamsTitle(String content) {
        this.examsTitle.setText(content);
    }

    public void notifyDataChanged(int position){
        this.examAdapter.notifyItemRemoved(position);
    }

    public void setMessage(String message){

        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


    public void setExamAdapter(List<BeanExamType> beanExamTypes) {
        this.examAdapter.setbExams(beanExamTypes);
        this.rvExams.setAdapter(this.examAdapter);
    }
}