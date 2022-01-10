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
import com.example.unispark.controller.details.DetailsProfCommunication;
import com.example.unispark.controller.details.DetailsUniCommunication;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.controller.details.DetailsHomework;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class StudentHomeGUIController extends AppCompatActivity
implements UniCommunicationsAdapter.OnUniComClickListener,
ProfCommunicationsAdapter.OnProfComClickListener,
HomeworksAdapter.OnHomeworkBtnClickListener{
    //Attributes
    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //University Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter uniCommunicationsAdapter;
    List<UniversityCommunicationModel> uniCommunicationsItem;
    //Professor Communications
    RecyclerView rvProfCommunications;
    ProfCommunicationsAdapter profCommunicationsAdapter;
    List<ProfessorCommunicationModel> profCommunicationsItem;
    //Homeworks
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    List<HomeworkModel> homeworksItem;
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
        bottomNavigationView.setSelectedItemId(R.id.home);
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



        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Application Controller
        ShowUniCommunications uniCommunicationsAppController = new ShowUniCommunications();
        uniCommunicationsItem = uniCommunicationsAppController.setStudentCommunications(student);
        uniCommunicationsAdapter = new UniCommunicationsAdapter(uniCommunicationsItem, this);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Prof Communications
        rvProfCommunications =  findViewById(R.id.rv_prof_communications);
        //Application Controller
        ShowProfCommunications profCommunicationsAppController = new ShowProfCommunications();
        profCommunicationsItem = profCommunicationsAppController.setProfessorCommunications(student);
        profCommunicationsAdapter = new ProfCommunicationsAdapter(profCommunicationsItem, this);
        rvProfCommunications.setAdapter(profCommunicationsAdapter);



        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        //Application Controller
        ShowHomeworks homeworksAppController = new ShowHomeworks();
        homeworksItem = homeworksAppController.setStudentHomeworks(student);
        homeworksAdapter = new HomeworksAdapter(homeworksItem, this, "STUDENT");
        rvHomeworks.setAdapter(homeworksAdapter);
    }



    //On UniversityCommunications Click
    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsUniCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", uniCommunicationsItem.get(position));

        startActivity(intent);
    }

    //On ProfessorCommunications Click
    @Override
    public void onProfClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsProfCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", profCommunicationsItem.get(position));

        startActivity(intent);
    }

    //On Homework Click
    @Override
    public void onBtnClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsHomework.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", homeworksItem.get(position));
        intent.putExtra("StudentHomeGUIController", "StudentHome");

        startActivity(intent);
    }
}