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
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.controller.applicationcontroller.communications.ShowUniCommunications;
import com.example.unispark.controller.applicationcontroller.homeworks.ShowHomeworks;
import com.example.unispark.controller.guicontroller.menu.RightButtonMenu;
import com.example.unispark.controller.guicontroller.details.DetailsHomeworkGUIController;
import com.example.unispark.controller.guicontroller.details.DetailsUniCommunicationGUIController;
import com.example.unispark.controller.guicontroller.professor.fragment.AddProfCommunicationGUIController;
import com.example.unispark.controller.guicontroller.professor.fragment.AddExamGUIController;
import com.example.unispark.controller.guicontroller.professor.fragment.AddHomeworkGUIController;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorHomeGUIController extends AppCompatActivity implements
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
    List<BeanUniCommunication> beanUniCommunicationList;
    //Homeworks
    RecyclerView rvHomeworks;
    HomeworksAdapter homeworkAdapter;
    List<BeanHomework> beanHomeworkList;
    //Get Intent Extras
    Bundle extras;
    //Model
    BeanLoggedProfessor bProfessor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

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
        bottomNavigationView.setSelectedItemId(R.id.professor_home);
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
                AddExamGUIController fragment = new AddExamGUIController(bProfessor);
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
                AddHomeworkGUIController fragment= new AddHomeworkGUIController(bProfessor, beanHomeworkList, homeworkAdapter);
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



        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Application Controller
        ShowUniCommunications uniCommunicationsAppController = new ShowUniCommunications();
        beanUniCommunicationList = uniCommunicationsAppController.showProfessorCommunications(bProfessor);
        uniCommunicationsAdapter = new UniCommunicationsAdapter(beanUniCommunicationList, this);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);



        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        //Application Controller
        ShowHomeworks homeworksAppController = new ShowHomeworks();
        beanHomeworkList = homeworksAppController.getHomework(bProfessor);
        homeworkAdapter = new HomeworksAdapter(beanHomeworkList, this, "PROFESSOR");
        rvHomeworks.setAdapter(homeworkAdapter);
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




    //University Communication Click
    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(this, DetailsUniCommunicationGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", beanUniCommunicationList.get(position));

        startActivity(intent);
    }

    //Homework Button Click
    @Override
    public void onBtnClick(int position) {
        Intent intent = new Intent(this, DetailsHomeworkGUIController.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", beanHomeworkList.get(position));
        intent.putExtra("StudentHomeGUIController", "ProfessorHomeGUIController");
        startActivity(intent);
    }

}