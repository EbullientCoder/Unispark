package com.example.unispark.controller.guicontroller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.example.unispark.R;

import com.example.unispark.adapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.controller.applicationcontroller.menu.RightButtonMenu;
import com.example.unispark.controller.applicationcontroller.communications.ShowProfCommunications;
import com.example.unispark.controller.applicationcontroller.communications.ShowUniCommunications;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.controller.details.DetailsHomework;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentHomeGUIController extends AppCompatActivity{
    //Attributes
    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //University Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter uniCommunicationsAdapter;
    //Professor Communications
    RecyclerView rvProfCommunications;
    ProfCommunicationsAdapter profCommunicationsAdapter;
    //Homeworks
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;



    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightButtonMenu rightMenuAppController = new RightButtonMenu(getApplicationContext());

                //Serve un modo per determinare il giorno e la notte.
                rightMenuAppController.dayColor();
                rightMenuAppController.nightColor();
            }
        });



        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.home);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Menu Applicative Controller
                BottomNavigationMenu bottomMenuAppController = new BottomNavigationMenu(student, getApplicationContext(), item.getItemId());

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity();
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });



        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Application Controller
        ShowUniCommunications uniCommunicationsAppController = new ShowUniCommunications(student, getApplicationContext());
        uniCommunicationsAdapter = uniCommunicationsAppController.setCommunicationsAdapter();
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Prof Communications
        rvProfCommunications =  findViewById(R.id.rv_prof_communications);
        //Application Controller
        ShowProfCommunications profCommunicationsAppController = new ShowProfCommunications(student, getApplicationContext());
        profCommunicationsAdapter = profCommunicationsAppController.setCommunicationsAdapter();
        rvProfCommunications.setAdapter(profCommunicationsAdapter);



        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        //Application Controller
        ShowHomeworks homeworksAppController = new ShowHomeworks(student, getApplicationContext());
        homeworksAdapter = homeworksAppController.setStudentHomeworksAdapter();
        rvHomeworks.setAdapter(homeworksAdapter);
    }
}