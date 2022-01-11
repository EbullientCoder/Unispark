package com.example.unispark.controller.guicontroller.details;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.SignedStudentsAdapter;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.controller.applicationcontroller.exams.ShowSignedToExamStudents;
import com.example.unispark.controller.applicationcontroller.exams.VerbalizeExam;
import com.example.unispark.model.exams.BookExamModel;

import java.util.List;

public class DetailsVerbalizeExamsGUIController extends AppCompatActivity
        implements SignedStudentsAdapter.OnAddBtnClickListener {

    //Button: GoBack
    ImageView btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Exam Data
    TextView txtCourseName;
    TextView txtCourseDate;
    //ExamModel
    RecyclerView rvStudents;
    SignedStudentsAdapter studentsAdapter;
    List<BeanStudentSignedToExam> studentsItem;
    //Exam Model
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
        //Application Controller
        ShowSignedToExamStudents bookedStudentAppController = new ShowSignedToExamStudents();
        studentsItem = bookedStudentAppController.showBookedStudents(exam);
        if(studentsItem.isEmpty()) Toast.makeText(getApplicationContext(), "NO STUDENTS SIGNED", Toast.LENGTH_SHORT).show();
        else{
            studentsAdapter = new SignedStudentsAdapter(studentsItem, this);
            rvStudents.setAdapter(studentsAdapter);
        }
    }

    @Override
    public void onAddBtnClick(int position, String result) {
        //Application Controller
        VerbalizeExam verbalizeExamAppController = new VerbalizeExam();
        boolean isValid = verbalizeExamAppController.verbalizeExam(exam, studentsItem.get(position), result);

        if (!isValid) Toast.makeText(getApplicationContext(), "Cannot verbalize: Exam has not taken place yet", Toast.LENGTH_SHORT).show();
        else{
            //Remove Verbalized Exam
            studentsItem.remove(position);
            studentsAdapter.notifyItemRemoved(position);

            if(result != null) Toast.makeText(getApplicationContext(), "Exam Verbalized", Toast.LENGTH_SHORT).show();
        }

    }
}