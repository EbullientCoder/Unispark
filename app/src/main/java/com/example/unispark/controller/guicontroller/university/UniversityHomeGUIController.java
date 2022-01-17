package com.example.unispark.controller.guicontroller.university;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.LessonAdapter;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanLesson;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedUniversity;
import com.example.unispark.controller.applicationcontroller.communications.ShowUniCommunications;

import com.example.unispark.controller.applicationcontroller.course.MenageCourses;
import com.example.unispark.controller.applicationcontroller.schedule.DeleteLesson;
import com.example.unispark.controller.applicationcontroller.schedule.GetScheduleUniversity;
import com.example.unispark.controller.guicontroller.details.DetailsUniCommunicationGUIController;
import com.example.unispark.controller.guicontroller.university.fragment.AddScheduleGUIController;
import com.example.unispark.controller.guicontroller.university.fragment.AddUniCommunicationGUIController;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UniversityHomeGUIController extends AppCompatActivity implements
        UniCommunicationsAdapter.OnUniComClickListener,
        LessonAdapter.OnDelBtnClickListener {

    //Menu
    ImageButton menuButton;
    //Next Course
    ImageButton btnNextCourse;
    //Floating Button
    FloatingActionButton btnAdd;
    FloatingActionButton btnCommunication;
    TextView txtCommunication;
    FloatingActionButton btnSchedule;
    TextView txtSchedule;
    Boolean isOpen;
    //Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter uniCommunicationsAdapter;
    List<BeanUniCommunication> beanUniCommunicationList;
    //Schedules
    TextView txtScheduleTitle;
    RecyclerView rvSchedules;
    LessonAdapter lessonAdapter;
    List<BeanLesson> bLessons;
    //Get Intent Extras
    Bundle extras;
    //Model
    BeanLoggedUniversity bUniversity;

    int index = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_home);

        //Getting User Object
        extras = getIntent().getExtras();
        bUniversity = (BeanLoggedUniversity) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });



        //Floating Button
        isOpen = false;

        btnAdd = findViewById(R.id.btn_uni_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandButton();
            }
        });
        //Button: Add Communication
        txtCommunication = findViewById(R.id.txt_add_uni_communication);
        txtCommunication.setVisibility(View.GONE);

        btnCommunication = findViewById(R.id.btn_add_uni_communication);
        btnCommunication.setImageTintList(null);
        btnCommunication.setVisibility(View.GONE);
        btnCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddUniCommunicationGUIController fragment = new AddUniCommunicationGUIController(bUniversity, beanUniCommunicationList, uniCommunicationsAdapter);
                fragment.show(getSupportFragmentManager(), "AddUniCommunication");
            }
        });
        //Button: Add StudentScheduleGUIController
        txtSchedule = findViewById(R.id.txt_add_schedule);
        txtSchedule.setVisibility(View.GONE);

        btnSchedule = findViewById(R.id.btn_add_schedule);
        btnSchedule.setImageTintList(null);
        btnSchedule.setVisibility(View.GONE);
        btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddScheduleGUIController fragment = new AddScheduleGUIController(bUniversity, lessonAdapter, bLessons);
                fragment.show(getSupportFragmentManager(), "AddSchedule");
            }
        });



        //Assigned Communications
        rvUniCommunications = findViewById(R.id.rv_assigned_communications);
        //Application Controller
        ShowUniCommunications uniCommunicationsAppController = new ShowUniCommunications();
        beanUniCommunicationList = uniCommunicationsAppController.showUniversityCommunications();
        uniCommunicationsAdapter = new UniCommunicationsAdapter(beanUniCommunicationList, this);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Schedules
        txtScheduleTitle = findViewById(R.id.txt_schedule_day);
        rvSchedules = findViewById(R.id.rv_schedules);
        //Button: Next Course
        btnNextCourse = findViewById(R.id.btn_course_next);
        btnNextCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(index == 4) index = 0;
                else index++;

                switch (index){
                    case 0: showSchedule("MONDAY");
                    break;
                    case 1: showSchedule("TUESDAY");
                    break;
                    case 2: showSchedule("WEDNESDAY");
                    break;
                    case 3: showSchedule("THURSDAY");
                    break;
                    case 4: showSchedule("FRIDAY");
                    break;
                }
            }
        });
        showSchedule("MONDAY");
    }


    //Open Button
    public void expandButton(){
        if(!isOpen){
            //Show Buttons
            btnCommunication.show();
            btnSchedule.show();

            //Expand Floating Button
            txtCommunication.setVisibility(View.VISIBLE);
            txtSchedule.setVisibility(View.VISIBLE);

            //Rotate
            btnAdd.setRotation(45);

            isOpen = true;
        }
        else{
            //Hide Buttons
            btnCommunication.hide();
            btnSchedule.hide();

            //Hide text
            txtCommunication.setVisibility(View.GONE);
            txtSchedule.setVisibility(View.GONE);

            //Rotate
            btnAdd.setRotation(0);

            isOpen = false;
        }
    }


    //Show StudentScheduleGUIController
    private void showSchedule(String day){
        //Set Course's StudentScheduleGUIController
        txtScheduleTitle.setText("SCHEDULE: " + day);

        //Lessons
        //Application Controller: Get Courses
        MenageCourses getCoursesAppController = new MenageCourses();
        List<BeanCourse> courses = getCoursesAppController.getFacultyCourses(bUniversity.getFaculties());
        //Application Controller: Get Lessons
        GetScheduleUniversity getScheduleUniversityAppController = new GetScheduleUniversity();
        bLessons = getScheduleUniversityAppController.getLessons(day, courses);
        lessonAdapter = new LessonAdapter(bLessons, this, "UNIVERSITY");

        //Set adapter
        rvSchedules.setAdapter(lessonAdapter);
    }




    //On UniversityCommunications Click
    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(this, DetailsUniCommunicationGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", beanUniCommunicationList.get(position));

        startActivity(intent);
    }

    //On DeleteLesson Button Click
    @Override
    public void onDelBtnClick(int position) {
        //Application Controller
        DeleteLesson deleteLessonAppController = new DeleteLesson();
        try {
            deleteLessonAppController.deleteLesson(bLessons.get(position));
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        //Show Removed StudentScheduleGUIController
        bLessons.remove(position);
        lessonAdapter.notifyItemRemoved(position);
        rvSchedules.setAdapter(lessonAdapter);
    }
}