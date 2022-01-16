package com.example.unispark.controller.guicontroller.student;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.adapter.LessonAdapter;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.schedule.ShowSchedule;
import com.example.unispark.controller.guicontroller.menu.RightButtonMenu;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.time.OffsetDateTime;
import java.util.List;

public class StudentScheduleGUIController extends AppCompatActivity{


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
    List<BeanLesson> lessonsItem;
    //Get Intent Extras
    Bundle extras;
    BeanLoggedStudent bStudent;


    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

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
        bottomNavigationView.setSelectedItemId(R.id.schedule);
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



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        txtDay = findViewById(R.id.txt_calendar_day);
        txtDay.setText(String.valueOf(offset.getDayOfWeek()));
        txtDate = findViewById(R.id.txt_calendar_date);
        txtDate.setText(offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth());



        //Lessons
        rvLessons = findViewById(R.id.rv_lessons);
        //Application Controller
        ShowSchedule showScheduleAppController = new ShowSchedule();
        lessonsItem = showScheduleAppController.getLessons(bStudent,String.valueOf(offset.getDayOfWeek()));
        lessonAdapter = new LessonAdapter(lessonsItem, "STUDENT");
        rvLessons.setAdapter(lessonAdapter);
    }
}