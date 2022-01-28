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
import com.example.unispark.Session;
import com.example.unispark.controller.guicontroller.professor.ManageProfessorHomeGuiController;
import com.example.unispark.viewadapter.HomeworksAdapter;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorHomeView extends AppCompatActivity implements
        HomeworksAdapter.OnHomeworkBtnClickListener,
        UniCommunicationsAdapter.OnUniComClickListener{



    //Communications
    private RecyclerView rvUniCommunications;
    private UniCommunicationsAdapter uniCommunicationsAdapter;
    //Floating Button
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnExam;
    private TextView txtExam;
    private FloatingActionButton btnHomework;
    private TextView txtHomework;
    private FloatingActionButton btnCommunication;
    private TextView txtCommunication;
    //Homeworks
    private RecyclerView rvHomeworks;
    private HomeworksAdapter homeworkAdapter;



    //Gui Controller
    private ManageProfessorHomeGuiController professorHomeGuiController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);


        professorHomeGuiController = new ManageProfessorHomeGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        uniCommunicationsAdapter = new UniCommunicationsAdapter(this);
        homeworkAdapter = new HomeworksAdapter(this, "PROFESSOR");


        //Bottom Navigation Menu
        BottomNavigationView bottomNavigationView;
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
                professorHomeGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorHomeGuiController.expandButton();
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
                professorHomeGuiController.showAddExam();
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
                professorHomeGuiController.showAddHomework();
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
                professorHomeGuiController.showAddCommunication();
            }
        });



        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Gui Controller
        professorHomeGuiController.showUniCommunications();



        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        //Gui Controller
        professorHomeGuiController.showHomeworks();
    }

    //Interface to allow the opening of a Communication or a Homework.
    //University Communication Click
    @Override
    public void onUniClick(int position) {
        professorHomeGuiController.showDetailsCommunication(position);
    }

    //Homework Button Click
    @Override
    public void onBtnClick(int position) {
        professorHomeGuiController.showHomeworkDetails(position);
    }




    //Methods for the GUIController
    public void setUniCommunicationsAdapter(List<BeanUniCommunication> beanUniCommunications) {
        uniCommunicationsAdapter.setBeanUniCommunicationList(beanUniCommunications);
        rvUniCommunications.setAdapter(uniCommunicationsAdapter);
    }


    public void setHomeworkAdapter(List<BeanHomework> beanHomeworkList) {
        homeworkAdapter.setBeanHomeworkList(beanHomeworkList);
        rvHomeworks.setAdapter(homeworkAdapter);
    }

    //The Adapter is a private attributes of the view, so it can't be passed to the GUI controller
    //as a method's parameter. The GUI Controller must take it from this public getter of the VIEW
    public HomeworksAdapter getHomeworkAdapter() {
        return homeworkAdapter;
    }


    //Same thing can be said for these elements.
    //The gui controller can set and unset them from view's methods.
    public void setBtnExam() {
        btnExam.show();
    }

    public void setBtnAdd() {
        btnAdd.setRotation(45);
    }

    public void setTxtExam() {
        txtExam.setVisibility(View.VISIBLE);
    }

    public void setBtnHomework() {
        btnHomework.show();
    }

    public void setTxtHomework() {
        txtHomework.setVisibility(View.VISIBLE);
    }

    public void setBtnCommunication() {
        btnCommunication.show();
    }

    public void setTxtCommunication() {
        txtCommunication.setVisibility(View.VISIBLE);
    }


    public void unsSetBtnExam() {
        btnExam.hide();
    }

    public void unSetBtnAdd() {
        btnAdd.setRotation(0);
    }

    public void unSetTxtExam() {
        txtExam.setVisibility(View.GONE);
    }

    public void unSetBtnHomework() {
        btnHomework.hide();
    }

    public void unSetTxtHomework() {
        txtHomework.setVisibility(View.GONE);
    }

    public void unSetBtnCommunication() {
        btnCommunication.hide();
    }

    public void unSetTxtCommunication() {
        txtCommunication.setVisibility(View.GONE);
    }
}