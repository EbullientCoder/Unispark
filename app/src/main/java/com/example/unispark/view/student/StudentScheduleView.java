package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.student.ShowScheduleGuiController;
import com.example.unispark.viewadapter.LessonAdapter;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.time.OffsetDateTime;
import java.util.List;

public class StudentScheduleView extends AppCompatActivity{


    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Calendar
    TextView txtDay;
    TextView txtDate;
    //Lessons
    RecyclerView rvLessons;
    LessonAdapter lessonAdapter;
    //Get Intent Extras
    Bundle extras;

    //Bean
    BeanLoggedStudent bStudent;
    List<BeanLesson> lessonsItem;

    //Gui Controller
    ShowScheduleGuiController scheduleGuiController;


    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        this.scheduleGuiController = new ShowScheduleGuiController();

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
        bottomNavigationView.setSelectedItemId(R.id.schedule);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                scheduleGuiController.selectNextView(bStudent, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        txtDay = findViewById(R.id.txt_calendar_day);
        txtDay.setText(String.valueOf(offset.getDayOfWeek()));
        txtDate = findViewById(R.id.txt_calendar_date);
        txtDate.setText(offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth());


        //Lessons
        rvLessons = findViewById(R.id.rv_lessons);
        //Gui Controller
        lessonsItem = scheduleGuiController.showSchedule(bStudent,String.valueOf(offset.getDayOfWeek()));
        lessonAdapter = new LessonAdapter(lessonsItem, "STUDENT");
        rvLessons.setAdapter(lessonAdapter);
    }
}