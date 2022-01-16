package com.example.unispark.controller.guicontroller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unispark.R;

import com.example.unispark.adapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.BeanProfCommunication;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.controller.applicationcontroller.communications.ShowProfCommunications;
import com.example.unispark.controller.applicationcontroller.communications.ShowUniCommunications;
import com.example.unispark.controller.guicontroller.details.DetailsProfCommunicationGUIController;
import com.example.unispark.controller.guicontroller.details.DetailsUniCommunicationGUIController;
import com.example.unispark.controller.guicontroller.details.DetailsHomeworkGUIController;
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentHomeGUIController extends AppCompatActivity
implements UniCommunicationsAdapter.OnUniComClickListener,
ProfCommunicationsAdapter.OnProfComClickListener,
HomeworksAdapter.OnHomeworkBtnClickListener{


    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //University Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter uniCommunicationsAdapter;
    List<BeanUniCommunication> beanUniCommunicationList;
    //Professor Communications
    RecyclerView rvProfCommunications;
    ProfCommunicationsAdapter profCommunicationsAdapter;
    List<BeanProfCommunication> beanProfCommunicationList;
    //Homeworks
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworksAdapter;
    List<BeanHomework> beanHomeworkList;
    //Get Intent Extras
    Bundle extras;
    BeanLoggedStudent bStudent;


    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        //Getting User Object
        extras = getIntent().getExtras();
        bStudent = (BeanLoggedStudent) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work In Progress", Toast.LENGTH_SHORT).show();
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
                BottomNavigationMenuGuiController bottomMenu = new BottomNavigationMenuGuiController();

                //Start Activity
                Intent intent = bottomMenu.nextActivity(bStudent, getApplicationContext(), item.getItemId());
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });



        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Application Controller
        ShowUniCommunications uniCommunicationsAppController = new ShowUniCommunications();
        beanUniCommunicationList = uniCommunicationsAppController.showStudentCommunications(bStudent);
        uniCommunicationsAdapter = new UniCommunicationsAdapter(beanUniCommunicationList, this);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Prof Communications
        rvProfCommunications =  findViewById(R.id.rv_prof_communications);
        //Application Controller
        ShowProfCommunications profCommunicationsAppController = new ShowProfCommunications();
        beanProfCommunicationList = profCommunicationsAppController.showProfessorCommunications(bStudent);
        profCommunicationsAdapter = new ProfCommunicationsAdapter(beanProfCommunicationList, this);
        rvProfCommunications.setAdapter(profCommunicationsAdapter);



        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        //Application Controller
        ShowHomeworks homeworksAppController = new ShowHomeworks();
        beanHomeworkList = homeworksAppController.getHomework(bStudent);
        homeworksAdapter = new HomeworksAdapter(beanHomeworkList, this, "STUDENT");
        rvHomeworks.setAdapter(homeworksAdapter);
    }



    //On UniversityCommunications Click
    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsUniCommunicationGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", beanUniCommunicationList.get(position));

        startActivity(intent);
    }




    //On ProfessorCommunications Click
    @Override
    public void onProfClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsProfCommunicationGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", beanProfCommunicationList.get(position));

        startActivity(intent);
    }

    //On Homework Click
    @Override
    public void onBtnClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsHomeworkGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", beanHomeworkList.get(position));
        intent.putExtra("StudentHomeGUIController", "StudentHome");

        startActivity(intent);
    }
}