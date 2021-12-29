package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookingExamModel;
import com.example.unispark.model.exams.ExamGradeModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Exams extends AppCompatActivity {

    //Attributes
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //ExamModel
    RecyclerView rvExams;
    List<ExamItem> examsExamItem;
    //Menu ExamModel Page
    ImageButton btnPageRight;
    ImageButton btnPageLeft;
    TextView examsTitle;
    int page;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;

    private static final String YEAR = "2020/2021";


    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.exams);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), student));
                overridePendingTransition(0, 0);
                return true;
            }
        });


        //ExamModel List
        rvExams = findViewById(R.id.rv_exams);
        examsExamItem = new ArrayList<>();
        //ExamModel Page Title
        examsTitle = findViewById(R.id.txt_exams_title);
        //ExamModel Page Menu Buttons
        btnPageRight = (ImageButton) findViewById(R.id.btn_exams_next);
        btnPageLeft = (ImageButton) findViewById(R.id.btn_exams_previusly);
        page = 0;
        //Right Click
        btnPageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;

                pageMenu();
            }
        });
        //Left Click
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
        //Edit the page index
        if(page > 2 || page < -2) page = 0;

        //Select the Page
        if(page == 0) verbalizedExams();
        if(page == 1 || page == -1) failedExams();
        if(page == 2 || page == -2) reserveExams();

        rvExams.setAdapter(new ExamAdapter(examsExamItem));
    }



    //Page: Verbalized ExamModel
    private void verbalizedExams(){
        //Set Title
        examsTitle.setText("VERBALIZED EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        //Types: 0 = Verbalized ExamModel | 1 = Failed ExamModel | 2 = Accept ExamModel | 3 = Reserve ExamModel

        List<ExamGradeModel> verbalizedExams = student.getvExams();
        for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
            examsExamItem.add(new ExamItem(1, verbalizedExams.get(i)));
        }
    }

    //Page: Failed ExamModel
    private void failedExams(){
        //Set Title
        examsTitle.setText("FAILED EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        List<ExamGradeModel> failedExams = student.getfExams();
        for (int i = 0; failedExams != null && i < failedExams.size(); i++){
            examsExamItem.add(new ExamItem(1, failedExams.get(i)));
        }


    }

    //Page: Reserve ExamModel
    private void reserveExams(){
        //Set Title
        examsTitle.setText("BOOK EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        List<BookingExamModel> bookingExams = student.getbExams();
        for (int i = 0; bookingExams != null && i < bookingExams.size(); i++){
            examsExamItem.add(new ExamItem(2, bookingExams.get(i)));
        }
    }
}