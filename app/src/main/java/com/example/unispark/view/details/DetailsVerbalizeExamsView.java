package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.professor.VerbalizeExamGuiController;
import com.example.unispark.viewadapter.SignedStudentsAdapter;
import com.example.unispark.bean.exam.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;

import java.util.List;

public class DetailsVerbalizeExamsView extends AppCompatActivity
        implements SignedStudentsAdapter.OnAddBtnClickListener {

    //Button: GoBack
    ImageView btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Exam Data
    TextView txtCourseName;
    TextView txtCourseDate;
    RecyclerView rvStudents;
    SignedStudentsAdapter studentsAdapter;

    //Bean
    BeanBookExam beanBookExam;
    List<BeanStudentSignedToExam> studentsItem;


    //Gui Controller
    private VerbalizeExamGuiController verbalizeExamGuiController;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_verbalize_exams);


        this.verbalizeExamGuiController = new VerbalizeExamGuiController();

        //GoBack Button
        btnGoBack = findViewById(R.id.btn_verbalize_exam_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Text
        beanBookExam = (BeanBookExam) extras.getSerializable("Exam");



        //Exam Name - Date
        txtCourseName = findViewById(R.id.txt_verbalize_exam_shortname);
        txtCourseName.setText(beanBookExam.getName());
        txtCourseDate = findViewById(R.id.txt_verbalize_exam_date);
        txtCourseDate.setText(beanBookExam.getDate());



        //Students Recycler View
        rvStudents = findViewById(R.id.rv_signedStudents);
        //Gui Controller
        studentsItem = verbalizeExamGuiController.showStudents(getApplicationContext(), beanBookExam);
        studentsAdapter = new SignedStudentsAdapter(studentsItem, this);
        rvStudents.setAdapter(studentsAdapter);

    }

    @Override
    public void onAddBtnClick(int position, String result) {
        verbalizeExamGuiController.verbalizeExam(getApplicationContext(), result, beanBookExam, studentsItem, position, studentsAdapter);

    }

}