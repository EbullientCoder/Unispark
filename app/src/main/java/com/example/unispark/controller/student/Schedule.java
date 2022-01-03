package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.R;
import com.example.unispark.adapter.LessonAdapter;
import com.example.unispark.database.dao.LessonsDAO;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.LessonModel;
import com.example.unispark.model.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule extends AppCompatActivity{

    //Attributes
    //Calendar
    TextView txtDay;
    TextView txtDate;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Lessons
    private RecyclerView rvLessons;
    private List<LessonModel> lessonsExamItem;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;


    //Methods
    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        txtDay = findViewById(R.id.txt_calendar_day);
        txtDay.setText(String.valueOf(offset.getDayOfWeek()));
        txtDate = findViewById(R.id.txt_calendar_date);
        txtDate.setText(offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth());


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.schedule);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), student));
                overridePendingTransition(0, 0);
                return true;
            }
        });


        //Lessons
        rvLessons = findViewById(R.id.rv_lessons);
        lessonsExamItem = null;

        lessonsList(String.valueOf(offset.getDayOfWeek()));
    }

    //Select Lessons
    private void lessonsList(String day){
        switch(day){
            case "TUESDAY": lessonsExamItem = LessonsDAO.getLessons("TUESDAY", student.getCourses());
                break;
            case "WEDNESDAY": lessonsExamItem = LessonsDAO.getLessons("WEDNESDAY", student.getCourses());
                break;
            case "THURSDAY": lessonsExamItem = LessonsDAO.getLessons("THURSDAY", student.getCourses());
                break;
            case "FRIDAY": lessonsExamItem = LessonsDAO.getLessons("FRIDAY", student.getCourses());
                break;
            case "SATURDAY": lessonsExamItem = LessonsDAO.getLessons("SATURDAY", student.getCourses());
                break;
            case "SUNDAY": lessonsExamItem = LessonsDAO.getLessons("SUNDAY", student.getCourses());
                break;
            default:
                lessonsExamItem = LessonsDAO.getLessons("MONDAY", student.getCourses());
        }

        if(lessonsExamItem != null) rvLessons.setAdapter(new LessonAdapter(lessonsExamItem));

    }

}