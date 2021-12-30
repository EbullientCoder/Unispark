package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Exams extends AppCompatActivity
implements ExamAdapter.OnBookExamClickListener,
        ExamAdapter.OnLeaveExamClickListener {

    //Attributes
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //ExamModel
    RecyclerView rvExams;
    ExamAdapter examAdapter;
    List<ExamItem> examsItem;
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
        examsItem = new ArrayList<>();
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
        if(page > 3 || page < -3) page = 0;

        //Select the Page
        if(page == 0) verbalizedExams();
        if(page == 1 || page == -3) failedExams();
        if(page == 2 || page == -2) bookExams();
        if(page == 3 || page == -1) bookedExams();

        examAdapter = new ExamAdapter(examsItem, this, this);
        rvExams.setAdapter(examAdapter);
    }



    //Page: Verbalized ExamModel
    private void verbalizedExams(){
        //Set Title
        examsTitle.setText("VERBALIZED EXAMS");

        //Clear the ExamModel List
        examsItem.clear();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<VerbalizedExamModel> verbalizedExams = student.getvExams();
        for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
            examsItem.add(new ExamItem(0, verbalizedExams.get(i)));
        }
    }

    //Page: Failed ExamModel
    private void failedExams(){
        //Set Title
        examsTitle.setText("FAILED EXAMS");

        //Clear the ExamModel List
        examsItem.clear();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<VerbalizedExamModel> failedExams = student.getfExams();
        for (int i = 0; failedExams != null && i < failedExams.size(); i++){
            examsItem.add(new ExamItem(0, failedExams.get(i)));
        }
    }

    //Page: Upcoming Exams
    private void bookExams(){
        //Set Title
        examsTitle.setText("BOOK UPCOMING EXAMS");

        //Clear the ExamModel List
        examsItem.clear();

        List<String> courseName = new ArrayList<>();
        List<CourseModel> courses = student.getCourses();
        for(int i = 0; i < courses.size(); i++) courseName.add(courses.get(i).getFullName());

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<BookExamModel> bookExams = ExamsDAO.getExams(courseName, false);
        for (int i = 0; bookExams != null && i < bookExams.size(); i++){
            examsItem.add(new ExamItem(2, bookExams.get(i)));
        }
    }

    //Page: Booked Exams
    private void bookedExams(){
        //Set Title
        examsTitle.setText("BOOKED EXAMS");

        //Clear the ExamModel List
        examsItem.clear();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<BookExamModel> leaveExams = student.getBookedExams();
        for (int i = 0; leaveExams != null && i < leaveExams.size(); i++){
            examsItem.add(new ExamItem(3, leaveExams.get(i)));
        }
    }

    @Override
    public void onBookBtnClick(int position) {
        List<BookExamModel> exams = student.getBookedExams();

        //Make the Connection inside the DB
        ExamsDAO.bookExam((BookExamModel) examsItem.get(position).getObject(), student.getId());

        //Adding the Booked Exam into the Student's Exams List
        exams.add((BookExamModel) examsItem.get(position).getObject());
        student.setBookedExams(exams);

        //Removing the Booked Exam from the List
        examsItem.remove(position);
        examAdapter.notifyItemRemoved(position);
    }

    @Override
    public void onLeaveBtnClick(int position) {
        List<BookExamModel> exams = student.getBookedExams();

        //Remove the Connection inside the DB
        ExamsDAO.removeBookedExam(exams.get(position).getId(), student.getId());

        //Remove the Booked Exam from Student's Attributes
        exams.remove(position);
        student.setBookedExams(exams);

        //Removing the Booked Exam from the List
        examsItem.remove(position);
        examAdapter.notifyItemRemoved(position);
    }
}