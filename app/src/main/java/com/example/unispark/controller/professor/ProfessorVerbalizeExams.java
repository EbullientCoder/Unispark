package com.example.unispark.controller.professor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.bean.StudentBean;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;

import java.util.List;

public class ProfessorVerbalizeExams extends AppCompatActivity {

    //Attributes
    //Button: GoBack
    ImageView btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Exam Name
    TextView txtCourseName;
    //Exam Date
    TextView txtCourseDate;
    //Exam Grade

    //ExamModel
    RecyclerView rvStudents;
    List<StudentBean> studentsItem;
    //Model
    BookExamModel exam;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_verbalize_exams);

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
        exam = (BookExamModel) extras.getSerializable("Exam");


        //Exam Name - Date
        txtCourseName = findViewById(R.id.txt_verbalize_exam_shortname);
        txtCourseName.setText(exam.getName());
        txtCourseDate = findViewById(R.id.txt_verbalize_exam_date);
        txtCourseDate.setText(exam.getDate());


        //Students Recycler View
        rvStudents = findViewById(R.id.rv_signedStudents);
        studentsItem = ExamsDAO.getStudentsBookedExam(exam.getId());
    }
}