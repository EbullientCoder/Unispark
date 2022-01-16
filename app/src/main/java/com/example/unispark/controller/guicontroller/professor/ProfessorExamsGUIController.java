package com.example.unispark.controller.guicontroller.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanExamType;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.exams.ShowExams;
import com.example.unispark.controller.guicontroller.menu.RightButtonMenu;
import com.example.unispark.controller.guicontroller.details.DetailsVerbalizeExamsGUIController;
import com.example.unispark.controller.guicontroller.professor.fragment.AddProfCommunicationGUIController;
import com.example.unispark.controller.guicontroller.professor.fragment.AddExamGUIController;
import com.example.unispark.controller.guicontroller.professor.fragment.AddHomeworkGUIController;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.exams.BookExamModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorExamsGUIController extends AppCompatActivity
        implements ExamAdapter.OnViewExamClickListener{


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
    //Menu ExamModel Page
    TextView examsTitle;
    //Get Intent Extras
    Bundle extras;
    //ExamModel
    RecyclerView rvExams;
    ExamAdapter examAdapter;
    List<BeanExamType> examsItem;
    //Model
    BeanLoggedProfessor bProfessor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_exams);

        //Getting User Object
        extras = getIntent().getExtras();
        bProfessor = (BeanLoggedProfessor) extras.getSerializable("UserObject");



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
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.professor_exams);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //Menu Applicative Controller
                BottomNavigationMenuGuiController bottomMenuAppController = new BottomNavigationMenuGuiController();

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity(bProfessor, getApplicationContext(), item.getItemId());
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });


        //ExamModel Page Title
        examsTitle = findViewById(R.id.txt_professor_exams_title);

        //ExamModel List
        rvExams = findViewById(R.id.rv_professor_exams);
        //Application Controller
        ShowExams showExamsAppController = new ShowExams();
        examsItem = showExamsAppController.assignedExams(bProfessor);
        examAdapter = new ExamAdapter(examsItem, this);
        rvExams.setAdapter(examAdapter);



        //Button: Add Homework - Communication
        isOpen = false;

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandButton();
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
                AddExamGUIController fragment= new AddExamGUIController(bProfessor);
                fragment.show(getSupportFragmentManager(), "AddExam");
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
                AddHomeworkGUIController fragment= new AddHomeworkGUIController(bProfessor);
                fragment.show(getSupportFragmentManager(), "AddHomework");
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
                AddProfCommunicationGUIController fragment= new AddProfCommunicationGUIController(bProfessor);
                fragment.show(getSupportFragmentManager(), "AddCommunication");
            }
        });
    }



    //Open Button
    public void expandButton(){
        if(!isOpen){
            //Show Buttons
            btnExam.show();
            btnCommunication.show();
            btnHomework.show();

            //Expand Floating Button
            txtExam.setVisibility(View.VISIBLE);
            txtCommunication.setVisibility(View.VISIBLE);
            txtHomework.setVisibility(View.VISIBLE);

            //Rotate
            btnAdd.setRotation(45);

            isOpen = true;
        }
        else{
            //Hide Buttons
            btnExam.hide();
            btnCommunication.hide();
            btnHomework.hide();

            //Expand Floating Button
            txtExam.setVisibility(View.GONE);
            txtCommunication.setVisibility(View.GONE);
            txtHomework.setVisibility(View.GONE);

            //Rotate
            btnAdd.setRotation(0);

            isOpen = false;
        }
    }




    //On ViewExam Button Click
    @Override
    public void onViewBtnClick(int position) {
        Intent intent = new Intent(getApplicationContext(), DetailsVerbalizeExamsGUIController.class);
        intent.putExtra("Exam", (BeanBookExam) examsItem.get(position).getBeanExamType());
        startActivity(intent);
    }
}