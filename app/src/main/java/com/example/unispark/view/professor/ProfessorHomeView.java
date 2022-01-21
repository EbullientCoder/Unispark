package com.example.unispark.view.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.professor.ManageProfessorHomeGuiController;
import com.example.unispark.viewadapter.HomeworksAdapter;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorHomeView extends AppCompatActivity implements
        HomeworksAdapter.OnHomeworkBtnClickListener,
        UniCommunicationsAdapter.OnUniComClickListener{

    //Menu
    ImageButton menuButton;
    //Floating Button
    FloatingActionButton btnAdd;
    FloatingActionButton btnExam;
    TextView txtExam;
    FloatingActionButton btnHomework;
    TextView txtHomework;
    FloatingActionButton btnCommunication;
    TextView txtCommunication;
    Boolean isOpen;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Communications
    RecyclerView rvUniCommunications;
    UniCommunicationsAdapter uniCommunicationsAdapter;

    //Homeworks
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworkAdapter;

    //Get Intent Extras
    Bundle extras;


    //Bean
    BeanLoggedProfessor bProfessor;
    List<BeanHomework> beanHomeworkList;
    List<BeanUniCommunication> beanUniCommunicationList;


    //Gui Controller
    private ManageProfessorHomeGuiController professorHomeGuiController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

        //Getting User Object
        extras = getIntent().getExtras();
        bProfessor = (BeanLoggedProfessor) extras.getSerializable("UserObject");


        this.professorHomeGuiController = new ManageProfessorHomeGuiController();


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.professor_home);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Menu Gui Controller
                professorHomeGuiController.selectNextView(bProfessor, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Button: Add Homework - Communication
        isOpen = false;

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOpen = professorHomeGuiController.expandButton(isOpen, btnExam, btnCommunication, btnHomework,
                txtExam, txtCommunication, txtHomework, btnAdd);
            }
        });
        //Button: Add Exam
        txtExam = findViewById(R.id.txt_add_exam);
        txtExam.setVisibility(View.GONE);

        btnExam = findViewById(R.id.btn_add_exam);
        btnExam.setImageTintList(null);
        btnExam.setVisibility(View.GONE);
        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorHomeGuiController.showAddExam(getSupportFragmentManager(), bProfessor);
            }
        });

        //Button: Add Homework
        txtHomework = findViewById(R.id.txt_add_homework);
        txtHomework.setVisibility(View.GONE);

        btnHomework = findViewById(R.id.btn_add_homework);
        btnHomework.setImageTintList(null);
        btnHomework.setVisibility(View.GONE);
        btnHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorHomeGuiController.showAddHomework(getSupportFragmentManager(), bProfessor, beanHomeworkList, homeworkAdapter);
            }
        });
        //Button: Add Communication
        txtCommunication = findViewById(R.id.txt_add_communication);
        txtCommunication.setVisibility(View.GONE);

        btnCommunication = findViewById(R.id.btn_add_communication);
        btnCommunication.setImageTintList(null);
        btnCommunication.setVisibility(View.GONE);
        btnCommunication.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Gui Controller
                professorHomeGuiController.showAddCommunication(getSupportFragmentManager(), bProfessor);
            }
        });



        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Gui Controller
        beanUniCommunicationList = professorHomeGuiController.showUniCommunications(bProfessor);
        uniCommunicationsAdapter = new UniCommunicationsAdapter(beanUniCommunicationList, this);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        //Gui Controller
        beanHomeworkList = professorHomeGuiController.showHomeworks(bProfessor);
        homeworkAdapter = new HomeworksAdapter(beanHomeworkList, this, "PROFESSOR");
        rvHomeworks.setAdapter(homeworkAdapter);
    }

    //University Communication Click
    @Override
    public void onUniClick(int position) {
        professorHomeGuiController.showDetailsCommunication(getApplicationContext(), beanUniCommunicationList.get(position));
    }

    //Homework Button Click
    @Override
    public void onBtnClick(int position) {

        professorHomeGuiController.showHomeworkDetails(getApplicationContext(), beanHomeworkList.get(position));
    }

}