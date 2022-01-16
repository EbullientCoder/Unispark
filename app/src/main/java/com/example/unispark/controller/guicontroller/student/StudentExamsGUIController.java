package com.example.unispark.controller.guicontroller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanExamType;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.exams.BookExam;
import com.example.unispark.controller.applicationcontroller.exams.LeaveExam;
import com.example.unispark.controller.applicationcontroller.exams.ShowExams;
import com.example.unispark.controller.guicontroller.menu.RightButtonMenu;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.example.unispark.exceptions.ExamAlreadyVerbalized;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.exams.BookExamModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StudentExamsGUIController extends AppCompatActivity
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
    List<BeanExamType> bExams;
    int page;
    //Get Intent Extras
    Bundle extras;
    BeanLoggedStudent bStudent;


    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        //Getting User Object
        extras = getIntent().getExtras();
        bStudent = (BeanLoggedStudent) extras.getSerializable("UserObject");



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
        bottomNavigationView.setSelectedItemId(R.id.exams);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Menu Applicative Controller
                BottomNavigationMenuGuiController bottomMenuAppController = new BottomNavigationMenuGuiController();

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity(bStudent, getApplicationContext(), item.getItemId());
                startActivity(intent);
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
                page++;

                pageMenu();
            }
        });
        //Left Click
        btnPageLeft = findViewById(R.id.btn_exams_previusly);
        btnPageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page--;

                pageMenu();
            }
        });

        pageMenu();
    }


    //Page Menu
    private void pageMenu(){
        //Application Controller
        ShowExams studentExamsAppController = new ShowExams();

        //Edit the page index
        if(page > 3 || page < -3) page = 0;

        //Select the Page
        if(page == 0){
            //Set Title
            examsTitle.setText("VERBALIZED EXAMS");
            //Exams Item
            bExams = studentExamsAppController.verbalizedExams(bStudent);
        }
        if(page == 1 || page == -3) {
            //Set Title
            examsTitle.setText("FAILED EXAMS");
            //Exams Item
            bExams = studentExamsAppController.failedExams(bStudent);
        }
        if(page == 2 || page == -2) {
            //Set Title
            examsTitle.setText("BOOK UPCOMING EXAMS");
            //Exams Item
            bExams = studentExamsAppController.bookExams(bStudent);

        }
        if(page == 3 || page == -1) {
            //Set Title
            examsTitle.setText("BOOKED EXAMS");
            //Exams Item
            bExams = studentExamsAppController.bookedExams(bStudent);
        }

        examAdapter = new ExamAdapter(bExams, this, this);
        rvExams.setAdapter(examAdapter);
    }




    //On BookExam Click
    @Override
    public void onBookBtnClick(int position) {

        //Application Controller
        BookExam bookExamAppController = new BookExam();

        try {
            bookExamAppController.bookExam(bStudent, (BeanBookExam) bExams.get(position).getBeanExamType());

            //Removing the Booked Exam from the List
            bExams.remove(position);
            examAdapter.notifyItemRemoved(position);
        } catch (ExamAlreadyVerbalized | GenericException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //On LeaveExam Click
    @Override
    public void onLeaveBtnClick(int position) {

        //Application Controller
        LeaveExam leaveExamAppController = new LeaveExam();
        try {
            leaveExamAppController.removeExam(bStudent, position);
        } catch (GenericException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Removing the Booked Exam from the List
        bExams.remove(position);
        examAdapter.notifyItemRemoved(position);
    }
}