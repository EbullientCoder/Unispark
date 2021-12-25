package com.example.unispark.controller.professor.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.exams.ExamModel;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddExamFragment extends DialogFragment{
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Btn Add Exam
    Button btnAddExam;
    //Course Selector
    List<String> courses;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String courseSelection;
    //Date Picker
    TextView txtDisplayDate;
    ImageButton btnSelectDate;
    DatePickerDialog.OnDateSetListener dateListener;
    Calendar calendar;
    String date;
    //AA
    TextInputLayout txtAA;
    String aa;
    //CFU
    TextInputLayout txtCFU;
    String cfu;
    //Building
    TextInputLayout txtBuilding;
    String building;
    //Classroom
    TextInputLayout txtClassroom;
    String classroom;
    //Instructions
    TextInputLayout txtInstructions;
    String instructions;
    //Model
    ProfessorModel professor;
    List<CourseModel> coursesList;
    //Exam Model
    ExamModel exam;

    int i;



    //Methods
    //Constructor
    public AddExamFragment(ProfessorModel professor) {
        this.professor = professor;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_exam, container, false);
        getDialog().setTitle("Simple Dialog");

        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //DropDown Selector
        coursesList = professor.getCourses();
        courses = new ArrayList<>(coursesList.size());
        for(i = 0; i < coursesList.size(); i++) courses.add(coursesList.get(i).getShortName());

        autoCompleteTxt = rootView.findViewById(R.id.add_exam_select_course);
        adapterItems = new ArrayAdapter<>(getContext(), R.layout.item_container_item, courses);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);

                //Getting the selected Course position
                i = position;
            }
        });

        //Date Picker
        calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        txtDisplayDate = rootView.findViewById(R.id.txt_add_exam_selected_date);
        OffsetDateTime offset = OffsetDateTime.now();
        txtDisplayDate.setText(offset.getDayOfMonth() + "/" + offset.getMonthValue() + "/" + offset.getYear());

        btnSelectDate = rootView.findViewById(R.id.btn_add_exam_select_date);
        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateListener,
                        year,
                        month,
                        day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date = Integer.toString(day) + "/" + Integer.toString(month) + "/" + Integer.toString(year);

                txtDisplayDate.setText(date);
            }
        };



        //Creating the Exam & Adding it into the Database
        //AA
        txtAA = rootView.findViewById(R.id.txt_add_exam_AA);
        //CFU
        txtCFU = rootView.findViewById(R.id.txt_add_exam_cfu);
        //Building
        txtBuilding = rootView.findViewById(R.id.txt_add_exam_building);
        //Classroom
        txtClassroom = rootView.findViewById(R.id.txt_add_exam_classroom);
        //Instructions
        txtInstructions = rootView.findViewById(R.id.txt_add_exam_instructions);

        //Add Exam
        btnAddExam = rootView.findViewById(R.id.btn_add_exam_add);
        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aa = txtAA.getEditText().getText().toString();
                cfu = txtCFU.getEditText().getText().toString();
                building = txtBuilding.getEditText().getText().toString();
                classroom = txtClassroom.getEditText().getText().toString();
                instructions = txtInstructions.getEditText().getText().toString();

                //Exam Object
                //exam = new ExamModel();

                Toast.makeText(getContext(),
                        "Exam:\n" +
                        "AA: " + aa + "\n" +
                        "CFU: " + cfu + "\n" +
                        "Building: " + building + "\n" +
                        "Classroom: " + classroom + "\n" +
                        "Instructions: " + instructions, Toast.LENGTH_LONG).show();

                //Adding it into the DB
                //ExamsDAO.addExam(exam);

                dismiss();
            }
        });


        return rootView;
    }
}
