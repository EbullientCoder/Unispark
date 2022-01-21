package com.example.unispark.view.professor.fragment;

import android.app.DatePickerDialog;
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

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.professor.BeanLoggedProfessor;

import com.example.unispark.controller.guicontroller.professor.AddExamGuiController;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.List;

public class AddExamView extends DialogFragment{


    //Dismiss Button
    ImageButton btnDismiss;
    //Btn Add Exam
    Button btnAddExam;
    //Course Selector
    List<String> coursesNames;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String courseSelection = "";
    //Date Picker
    TextView txtDisplayDate;
    ImageButton btnSelectDate;
    DatePickerDialog.OnDateSetListener dateListener;
    Calendar calendar;
    String date = "";
    //Hour
    TextInputLayout txtHour;
    String hour;
    //Building
    TextInputLayout txtBuilding;
    String building;
    //Classroom
    TextInputLayout txtClassroom;
    String classroom;

    //Bean
    BeanLoggedProfessor bProfessor;
    List<BeanCourse> bCourses;

    int i;

    //Gui Controller
    private AddExamGuiController examGuiController;



    //Constructor
    public AddExamView(BeanLoggedProfessor bProfessor) {
        this.bProfessor = bProfessor;
        this.examGuiController = new AddExamGuiController();
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
        //Gui Controller
        bCourses = examGuiController.showCourses(bProfessor);
        coursesNames = examGuiController.getCoursesNames(bProfessor);


        autoCompleteTxt = rootView.findViewById(R.id.add_exam_select_course);
        adapterItems = new ArrayAdapter<>(getContext(), R.layout.item_container_item, coursesNames);
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


        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day) + " ";

                txtDisplayDate.setText(date);
            }
        };

        btnSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                examGuiController.showDateDialog(getContext(), dateListener, year, month, day);
            }
        });



        //Creating the Exam & Adding it into the Database
        //AA
        txtHour = rootView.findViewById(R.id.txt_add_exam_hour);
        //Building
        txtBuilding = rootView.findViewById(R.id.txt_add_exam_building);
        //Classroom
        txtClassroom = rootView.findViewById(R.id.txt_add_exam_classroom);

        //Add Exam
        btnAddExam = rootView.findViewById(R.id.btn_add_exam_add);
        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hour = txtHour.getEditText().getText().toString();
                building = txtBuilding.getEditText().getText().toString();
                classroom = txtClassroom.getEditText().getText().toString();

                examGuiController.addExam(getContext(), getDialog(), bProfessor, bCourses.get(i).getFullName(),
                        bCourses.get(i).getCourseYear(), date, courseSelection, hour, bCourses.get(i).getCfu(),
                        building, classroom);
            }
        });

        return rootView;
    }



}
