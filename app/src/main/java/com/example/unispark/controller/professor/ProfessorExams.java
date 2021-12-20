package com.example.unispark.controller.professor;

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
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.controller.professor.fragment.AddCommunicationFragment;
import com.example.unispark.controller.professor.fragment.AddExamFragment;
import com.example.unispark.controller.professor.fragment.AddHomeworkFragment;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.exams.UpcomingExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfessorExams extends AppCompatActivity {
    //Attributes
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
    List<ExamItem> examsExamItem;
    //Model
    ProfessorModel professor;

    private static final String YEAR = "2020/2021";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_exams);

        //Getting User Object
        extras = getIntent().getExtras();
        professor = (ProfessorModel) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });

        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.professor_exams);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), professor));
                overridePendingTransition(0, 0);
                return true;
            }
        });


        //ExamModel List
        rvExams = findViewById(R.id.rv_professor_exams);
        examsExamItem = new ArrayList<>();
        //ExamModel Page Title
        examsTitle = findViewById(R.id.txt_professor_exams_title);
        //ExamModel Page Menu Buttons
        upcomingExams();

        rvExams.setAdapter(new ExamAdapter(examsExamItem));


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
                AddExamFragment fragment= new AddExamFragment(professor);
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
                AddHomeworkFragment fragment= new AddHomeworkFragment(professor);
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
                AddCommunicationFragment fragment= new AddCommunicationFragment(professor);
                fragment.show(getSupportFragmentManager(), "AddCommunication");
            }
        });



        //------------------------------------------------------------------------------------------
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



    //Page: Upcoming ExamModel
    private void upcomingExams(){
        //Set Title
        examsTitle.setText("UPCOMING EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        //Types: 0 = Verbalized ExamModel | 1 = Failed ExamModel | 2 = Reserve ExamModel | 3 = Professor UpcomingExamModel
        UpcomingExamModel uExam1 = new UpcomingExamModel(1,"Ing. del Software e prog. Web", YEAR, "11/02/2020", "12.0", "L3", "Didattica");
        UpcomingExamModel uExam2 = new UpcomingExamModel(2,"Ing. del Software e prog. Web II", YEAR, "20/02/2020", "9.0", "L3", "Didattica");
        UpcomingExamModel uExam3 = new UpcomingExamModel(3,"Ing. del Software e prog. Web", YEAR, "14/07/2020", "6.0", "L3", "Didattica");
        UpcomingExamModel uExam4 = new UpcomingExamModel(4,"Ing. del Software e prog. Web II", YEAR, "25/08/2020", "6.0", "L3", "Didattica");

        examsExamItem.add(new ExamItem(3, uExam1));
        examsExamItem.add(new ExamItem(3, uExam2));
        examsExamItem.add(new ExamItem(3, uExam3));
        examsExamItem.add(new ExamItem(3, uExam4));
    }
}