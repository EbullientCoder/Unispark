package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.FailedExamModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Exams extends AppCompatActivity {

    //Attributes
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //ExamModel
    RecyclerView rvExams;
    List<ExamItem> examsExamItem;
    //Menu ExamModel Page
    ImageButton btnPageRight;
    ImageButton btnPageLeft;
    TextView examsTitle;
    int page;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;

    private static final String YEAR = "2020/2021";


    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_exams);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.exams);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), student));
                overridePendingTransition(0, 0);
                return true;
            }
        });


        //ExamModel List
        rvExams = findViewById(R.id.rv_exams);
        examsExamItem = new ArrayList<>();
        //ExamModel Page Title
        examsTitle = findViewById(R.id.txt_exams_title);
        //ExamModel Page Menu Buttons
        btnPageRight = (ImageButton) findViewById(R.id.btn_exams_next);
        btnPageLeft = (ImageButton) findViewById(R.id.btn_exams_previusly);
        page = 0;
        //Right Click
        btnPageRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page++;

                pageMenu();
            }
        });
        //Left Click
        btnPageLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                page--;

                pageMenu();
            }
        });

        pageMenu();
    }


    //Page Menu
    private void pageMenu(){
        //Edit the page index
        if(page > 2 || page < -2) page = 0;

        //Select the Page
        if(page == 0) verbalizedExams();
        if(page == 1 || page == -1) failedExams();
        if(page == 2 || page == -2) reserveExams();

        rvExams.setAdapter(new ExamAdapter(examsExamItem));
    }



    //Page: Verbalized ExamModel
    private void verbalizedExams(){
        //Set Title
        examsTitle.setText("VERBALIZED EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        //Types: 0 = Verbalized ExamModel | 1 = Failed ExamModel | 2 = Accept ExamModel | 3 = Reserve ExamModel
        VerbalizedExamModel vExam1 = new VerbalizedExamModel(1,"Analisi 1", YEAR, "11/02/2020", "12.0", 24);
        VerbalizedExamModel vExam2 = new VerbalizedExamModel(2,"Fondamenti di Informatica", YEAR, "20/02/2020", "9.0", 30);
        VerbalizedExamModel vExam3 = new VerbalizedExamModel(3,"Fisica 1", YEAR, "14/07/2020", "6.0", 19);
        VerbalizedExamModel vExam4 = new VerbalizedExamModel(4,"Fondamenti di Automatica", YEAR, "25/08/2020", "6.0", 30);
        VerbalizedExamModel vExam5 = new VerbalizedExamModel(5,"Calcolatori Elettronici", YEAR, "17/02/2021", "9.0", 27);
        VerbalizedExamModel vExam6 = new VerbalizedExamModel(6,"Ingegneria degli Algoritmi", YEAR, "28/02/2021", "6.0", 18);
        VerbalizedExamModel vExam7 = new VerbalizedExamModel(7,"Elettrotecnica", YEAR, "21/03/2021", "6.0", 26);
        VerbalizedExamModel vExam8 = new VerbalizedExamModel(8,"Analisi 2", YEAR, "27/01/2021", "6.0", 21);
        VerbalizedExamModel vExam9 = new VerbalizedExamModel(9,"Fondamenti di Telecomunicazioni", YEAR, "14/07/2021", "9.0", 25);
        VerbalizedExamModel vExam10 = new VerbalizedExamModel(10,"Sistemi Operativi", YEAR, "20/07/2021", "9.0", 25);
        VerbalizedExamModel vExam11 = new VerbalizedExamModel(11,"Fondamenti di Controlli", YEAR, "12/03/2021", "9.0", 22);

        examsExamItem.add(new ExamItem(0, vExam1));
        examsExamItem.add(new ExamItem(0, vExam2));
        examsExamItem.add(new ExamItem(0, vExam3));
        examsExamItem.add(new ExamItem(0, vExam4));
        examsExamItem.add(new ExamItem(0, vExam5));
        examsExamItem.add(new ExamItem(0, vExam6));
        examsExamItem.add(new ExamItem(0, vExam7));
        examsExamItem.add(new ExamItem(0, vExam8));
        examsExamItem.add(new ExamItem(0, vExam9));
        examsExamItem.add(new ExamItem(0, vExam10));
        examsExamItem.add(new ExamItem(0, vExam11));
    }

    //Page: Failed ExamModel
    private void failedExams(){
        //Set Title
        examsTitle.setText("FAILED EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        FailedExamModel fExam1 = new FailedExamModel(1,"Probabilita' e Statistica", YEAR, "07/03/2020", "9.0", "failed");
        FailedExamModel fExam2 = new FailedExamModel(2,"Fondamenti di Controlli", YEAR, "11/07/2021", "9.0", "absent");
        FailedExamModel fExam3 = new FailedExamModel(3,"Ingegneria degli Algoritmi", YEAR, "13/07/2021", "6.0", "retired");

        examsExamItem.add(new ExamItem(1, fExam1));
        examsExamItem.add(new ExamItem(1, fExam2));
        examsExamItem.add(new ExamItem(1, fExam3));
    }


    //Page: Reserve ExamModel
    private void reserveExams(){
        //Set Title
        examsTitle.setText("BOOK EXAMS");

        //Clear the ExamModel List
        examsExamItem.clear();

        BookExamModel rExam1 = new BookExamModel(5,"Esame di Prova", "2021/2022", "01/02/2022", "12.0", "L4", "Didattica");

        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
        examsExamItem.add(new ExamItem(2, rExam1));
    }
}