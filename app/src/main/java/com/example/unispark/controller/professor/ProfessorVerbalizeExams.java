package com.example.unispark.controller.professor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.SignedStudentsAdapter;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorVerbalizeExams extends AppCompatActivity implements SignedStudentsAdapter.OnAddBtnClickListener {

    //Attributes
    //Button: GoBack
    ImageView btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Exam Name
    TextView txtCourseName;
    //Exam Date
    TextView txtCourseDate;
    //ExamModel
    RecyclerView rvStudents;
    SignedStudentsAdapter studentsAdapter;
    List<BeanStudentSignedToExam> studentsItem;
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

        if(studentsItem == null) Toast.makeText(getApplicationContext(), "NO STUDENTS SIGNED", Toast.LENGTH_SHORT).show();
        else{
            studentsAdapter = new SignedStudentsAdapter(studentsItem, this);
            rvStudents.setAdapter(studentsAdapter);
        }
    }

    @Override
    public void onAddBtnClick(int position, String result) {
        //Create new Verbalized Exam
        VerbalizedExamModel vExam = new VerbalizedExamModel(exam.getId(), exam.getName(), exam.getDate(), exam.getDate(), exam.getCFU(), result);

        //Add Verbalized Exam to the DB
        boolean isValid = ExamsDAO.addExamGrade(vExam, studentsItem.get(position).getId());
        if (!isValid) Toast.makeText(getApplicationContext(), "Cannot verbalize: Exam has not taken place yet", Toast.LENGTH_SHORT).show();
        else{
            //Remove Verbalized Exam
            studentsItem.remove(position);
            studentsAdapter.notifyItemRemoved(position);

            if(result != null) Toast.makeText(getApplicationContext(), "Exam Verbalized", Toast.LENGTH_SHORT).show();
        }

    }
}