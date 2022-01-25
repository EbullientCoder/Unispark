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
import com.example.unispark.Session;
import com.example.unispark.controller.guicontroller.student.ShowScheduleGuiController;
import com.example.unispark.viewadapter.LessonAdapter;
import com.example.unispark.bean.BeanLesson;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class StudentScheduleView extends AppCompatActivity{


    //Menu
    private ImageButton menuButton;
    //Bottom Menu Elements
    private BottomNavigationView bottomNavigationView;
    //Calendar
    private TextView txtDay;
    private TextView txtDate;
    //Lessons
    private RecyclerView rvLessons;
    private LessonAdapter lessonAdapter;



    //Gui Controller
    private ShowScheduleGuiController scheduleGuiController;


    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        this.scheduleGuiController = new ShowScheduleGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.lessonAdapter = new LessonAdapter("STUDENT");


        //Bottom Navigation Menu
        this.bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        this.bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        this.bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        this.bottomNavigationView.setSelectedItemId(R.id.schedule);
        //Click Listener
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                scheduleGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });


        //Calendar

        this.txtDay = findViewById(R.id.txt_calendar_day);
        this.txtDate = findViewById(R.id.txt_calendar_date);

        //Lessons
        this.rvLessons = findViewById(R.id.rv_lessons);
        //Gui Controller
        this.scheduleGuiController.showSchedule();

    }





    public void setLessonAdapter(List<BeanLesson> beanLessons) {
        this.lessonAdapter.setLessonItem(beanLessons);
        this.rvLessons.setAdapter(this.lessonAdapter);
    }

    public void setTxtDay(String content) {
        this.txtDay.setText(content);
    }

    public void setTxtDate(String content) {
        this.txtDate.setText(content);
    }
}