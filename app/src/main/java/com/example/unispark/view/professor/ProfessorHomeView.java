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


        this.professorHomeGuiController = new ManageProfessorHomeGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.uniCommunicationsAdapter = new UniCommunicationsAdapter(this);
        this.homeworkAdapter = new HomeworksAdapter(this, "PROFESSOR");


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




        this.btnAdd = findViewById(R.id.btn_add);
        this.btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorHomeGuiController.expandButton();
            }
        });
        //Button: Add Exam
        this.txtExam = findViewById(R.id.txt_add_exam);
        this.txtExam.setVisibility(View.GONE);

        this.btnExam = findViewById(R.id.btn_add_exam);
        this.btnExam.setImageTintList(null);
        this.btnExam.setVisibility(View.GONE);
        this.btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorHomeGuiController.showAddExam();
            }
        });

        //Button: Add Homework
        this.txtHomework = findViewById(R.id.txt_add_homework);
        this.txtHomework.setVisibility(View.GONE);

        this.btnHomework = findViewById(R.id.btn_add_homework);
        this.btnHomework.setImageTintList(null);
        this.btnHomework.setVisibility(View.GONE);
        this.btnHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorHomeGuiController.showAddHomework();
            }
        });
        //Button: Add Communication
        this.txtCommunication = findViewById(R.id.txt_add_communication);
        this.txtCommunication.setVisibility(View.GONE);

        this.btnCommunication = findViewById(R.id.btn_add_communication);
        this.btnCommunication.setImageTintList(null);
        this.btnCommunication.setVisibility(View.GONE);
        this.btnCommunication.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //Gui Controller
                professorHomeGuiController.showAddCommunication();
            }
        });



        //Uni Communications
        this.rvUniCommunications = findViewById(R.id.rv_uni_communications);
        //Gui Controller
        this.professorHomeGuiController.showUniCommunications();




        //Homeworks
        this.rvHomeworks = findViewById(R.id.rv_homeworks);
        //Gui Controller
        this.professorHomeGuiController.showHomeworks();


    }

    //University Communication Click
    @Override
    public void onUniClick(int position) {
        this.professorHomeGuiController.showDetailsCommunication(position);
    }

    //Homework Button Click
    @Override
    public void onBtnClick(int position) {

        professorHomeGuiController.showHomeworkDetails(position);
    }







    public void setUniCommunicationsAdapter(List<BeanUniCommunication> beanUniCommunications) {
        this.uniCommunicationsAdapter.setBeanUniCommunicationList(beanUniCommunications);
        this.rvUniCommunications.setAdapter(this.uniCommunicationsAdapter);
    }


    public void setHomeworkAdapter(List<BeanHomework> beanHomeworkList) {
        this.homeworkAdapter.setBeanHomeworkList(beanHomeworkList);
        this.rvHomeworks.setAdapter(this.homeworkAdapter);
    }



    public void setBtnExam() {
        this.btnExam.show();
    }

    public void setBtnAdd() {
        this.btnAdd.setRotation(45);
    }

    public void setTxtExam() {
        this.txtExam.setVisibility(View.VISIBLE);
    }

    public void setBtnHomework() {
        this.btnHomework.show();
    }

    public void setTxtHomework() {
        this.txtHomework.setVisibility(View.VISIBLE);
    }

    public void setBtnCommunication() {
        this.btnCommunication.show();
    }

    public void setTxtCommunication() {
        this.txtCommunication.setVisibility(View.VISIBLE);
    }


    public void unsSetBtnExam() {
        this.btnExam.hide();
    }

    public void unSetBtnAdd() {
        this.btnAdd.setRotation(0);
    }

    public void unSetTxtExam() {
        this.txtExam.setVisibility(View.GONE);
    }

    public void unSetBtnHomework() {
        this.btnHomework.hide();
    }

    public void unSetTxtHomework() {
        this.txtHomework.setVisibility(View.GONE);
    }

    public void unSetBtnCommunication() {
        this.btnCommunication.hide();
    }

    public void unSetTxtCommunication() {
        this.txtCommunication.setVisibility(View.GONE);
    }


    public UniCommunicationsAdapter getUniCommunicationsAdapter() {
        return uniCommunicationsAdapter;
    }

    public HomeworksAdapter getHomeworkAdapter() {
        return homeworkAdapter;
    }
}