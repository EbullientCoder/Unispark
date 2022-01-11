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


import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.controller.applicationcontroller.exams.BookExam;
import com.example.unispark.controller.applicationcontroller.exams.LeaveExam;
import com.example.unispark.controller.applicationcontroller.exams.ShowExams;
import com.example.unispark.controller.applicationcontroller.menu.RightButtonMenu;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
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
    List<ExamItem> examsItem;
    int page;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;


    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



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
                BottomNavigationMenu bottomMenuAppController = new BottomNavigationMenu();

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity(student, getApplicationContext(), item.getItemId());
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
            examsItem = studentExamsAppController.verbalizedExams(student);
        }
        if(page == 1 || page == -3) {
            //Set Title
            examsTitle.setText("FAILED EXAMS");
            //Exams Item
            examsItem = studentExamsAppController.failedExams(student);
        }
        if(page == 2 || page == -2) {
            //Set Title
            examsTitle.setText("BOOK UPCOMING EXAMS");
            //Exams Item
            examsItem = studentExamsAppController.bookExams(student);

        }
        if(page == 3 || page == -1) {
            //Set Title
            examsTitle.setText("BOOKED EXAMS");
            //Exams Item
            examsItem = studentExamsAppController.bookedExams(student);
        }

        examAdapter = new ExamAdapter(examsItem, this, this);
        rvExams.setAdapter(examAdapter);
    }




    //On BookExam Click
    @Override
    public void onBookBtnClick(int position) {
        List<BookExamModel> exams = student.getBookedExams();
        if (exams == null) exams = new ArrayList<>();

        //Application Controller
        BookExam bookExamAppController = new BookExam();
        boolean isBooked = bookExamAppController.bookExam(student, (BookExamModel) examsItem.get(position).getObject());
        if (isBooked){
            //Adding the Booked Exam into the Student's StudentExamsGUIController List
            exams.add((BookExamModel) examsItem.get(position).getObject());
            student.setBookedExams(exams);

            //Removing the Booked Exam from the List
            examsItem.remove(position);
            examAdapter.notifyItemRemoved(position);
        }
        else Toast.makeText(getApplicationContext(), "Cannot Book: Exam already verbalized", Toast.LENGTH_SHORT).show();
    }

    //On LeaveExam Click
    @Override
    public void onLeaveBtnClick(int position) {
        List<BookExamModel> exams = student.getBookedExams();

        //Application Controller
        LeaveExam leaveExamAppController = new LeaveExam();
        leaveExamAppController.removeExam(student, exams.get(position));

        //Remove the Booked Exam from Student's Attributes
        exams.remove(position);
        student.setBookedExams(exams);

        //Removing the Booked Exam from the List
        examsItem.remove(position);
        examAdapter.notifyItemRemoved(position);
    }
}