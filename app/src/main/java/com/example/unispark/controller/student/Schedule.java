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
    private List<ExamItem> lessonsExamItem;
    private LessonModel lesson0;
    private LessonModel lesson1;
    private LessonModel lesson2;
    private LessonModel lesson3;
    private LessonModel lesson4;
    private LessonModel lesson5;
    private LessonModel lesson6;
    private LessonModel lesson7;
    private LessonModel lesson8;
    private LessonModel lesson9;
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
        lessonsExamItem = new ArrayList<>();

        lessonsList(String.valueOf(offset.getDayOfWeek()));
    }

    //Select Lessons
    private void lessonsList(String day){
        switch(day){
            case "TUESDAY": tuesdayLessons();
                break;
            case "WEDNESDAY": wednesdayLessons();
                break;
            case "THURSDAY": thursdayLessons();
                break;
            case "FRIDAY": fridayLessons();
                break;
            case "SATURDAY": saturdayLessons();
                break;
            case "SUNDAY": sundayLessons();
                break;
            default:
                mondayLessons();
        }

        lessonsExamItem.add(new ExamItem(0, lesson0));
        lessonsExamItem.add(new ExamItem(0, lesson1));
        lessonsExamItem.add(new ExamItem(0, lesson2));
        lessonsExamItem.add(new ExamItem(0, lesson3));

        lessonsExamItem.add(new ExamItem(0, lesson4));
        lessonsExamItem.add(new ExamItem(0, lesson5));
        lessonsExamItem.add(new ExamItem(0, lesson6));
        lessonsExamItem.add(new ExamItem(0, lesson7));
        lessonsExamItem.add(new ExamItem(0, lesson8));
        lessonsExamItem.add(new ExamItem(0, lesson9));

        rvLessons.setAdapter(new LessonAdapter(lessonsExamItem));
    }

    //Monday
    public void mondayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "Ingegneria del Software e Progettazione Web");
        lesson3 = new LessonModel("12:30 - 13:15", "Ingegneria del Software e Progettazione Web");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "Ingegneria del Software e Progettazione Web");
        lesson6 = new LessonModel("15:00 - 15:45", "Ingegneria del Software e Progettazione Web");
        lesson7 = new LessonModel("16:00 - 16:45", "Automatica e Robotica Laboratorio");
        lesson8 = new LessonModel("17:00 - 17:45", "Automatica e Robotica Laboratorio");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }

    //Tuesday
    private void tuesdayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "");
        lesson3 = new LessonModel("12:30 - 13:15", "");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "");
        lesson6 = new LessonModel("15:00 - 15:45", "");
        lesson7 = new LessonModel("16:00 - 16:45", "");
        lesson8 = new LessonModel("17:00 - 17:45", "");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }

    //Wednesday
    private void wednesdayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "");
        lesson3 = new LessonModel("12:30 - 13:15", "");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "");
        lesson6 = new LessonModel("15:00 - 15:45", "");
        lesson7 = new LessonModel("16:00 - 16:45", "");
        lesson8 = new LessonModel("17:00 - 17:45", "");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }

    //Thursday
    private void thursdayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "");
        lesson3 = new LessonModel("12:30 - 13:15", "");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "");
        lesson6 = new LessonModel("15:00 - 15:45", "");
        lesson7 = new LessonModel("16:00 - 16:45", "");
        lesson8 = new LessonModel("17:00 - 17:45", "");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }

    //Friday
    private void fridayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "");
        lesson3 = new LessonModel("12:30 - 13:15", "");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "");
        lesson6 = new LessonModel("15:00 - 15:45", "");
        lesson7 = new LessonModel("16:00 - 16:45", "");
        lesson8 = new LessonModel("17:00 - 17:45", "");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }

    //Saturday
    private void saturdayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "");
        lesson3 = new LessonModel("12:30 - 13:15", "");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "");
        lesson6 = new LessonModel("15:00 - 15:45", "");
        lesson7 = new LessonModel("16:00 - 16:45", "");
        lesson8 = new LessonModel("17:00 - 17:45", "");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }

    //Sunday
    private void sundayLessons(){
        lesson0 = new LessonModel("09:30 - 10:15", "");
        lesson1 = new LessonModel("10:30 - 11:15", "");
        lesson2 = new LessonModel("11:30 - 12:15", "");
        lesson3 = new LessonModel("12:30 - 13:15", "");
        lesson4 = new LessonModel("", "");
        lesson5 = new LessonModel("14:00 - 14:45", "");
        lesson6 = new LessonModel("15:00 - 15:45", "");
        lesson7 = new LessonModel("16:00 - 16:45", "");
        lesson8 = new LessonModel("17:00 - 17:45", "");
        lesson9 = new LessonModel("18:00 - 18:45", "");
    }
}