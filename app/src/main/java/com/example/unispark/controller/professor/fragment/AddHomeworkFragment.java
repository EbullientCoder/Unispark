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

import java.time.OffsetDateTime;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.ProfessorModel;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddHomeworkFragment extends DialogFragment{
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Add Homework Button
    Button btnAddHomework;
    //Title
    TextInputLayout txtTitle;
    String title;
    //Instructions
    TextInputLayout txtInstructions;
    String instructions;
    //Date Picker
    TextView txtDisplayDate;
    ImageButton btnSelectDate;
    DatePickerDialog.OnDateSetListener dateListener;
    Calendar calendar;
    String date;
    //Points
    TextInputLayout txtPoints;
    String points;
    //Course Selector
    List<String> courses;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String courseSelection;
    //Professor Model
    ProfessorModel professor;
    List<CourseModel> coursesList;
    //Homework Model
    HomeworkModel homework;

    int i;



    //Methods
    //Constructor
    public AddHomeworkFragment(ProfessorModel professor) {
        //Getting Professor Object
        this.professor = professor;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_homework, container, false);
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

        autoCompleteTxt = rootView.findViewById(R.id.select_course);
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

        txtDisplayDate = rootView.findViewById(R.id.txt_selected_date);
        OffsetDateTime offset = OffsetDateTime.now();
        txtDisplayDate.setText(offset.getDayOfMonth() + " / " + offset.getMonthValue() + " / " + offset.getYear());

        btnSelectDate = rootView.findViewById(R.id.btn_select_date);
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



        //Creating the Homework & Adding it into the Database
        //Title
        txtTitle = rootView.findViewById(R.id.txt_add_homework_title);
        //Instructions
        txtInstructions = rootView.findViewById(R.id.txt_add_homework_instructions);
        //Points
        txtPoints = rootView.findViewById(R.id.txt_add_homework_points);

        //Add Homework
        btnAddHomework = rootView.findViewById(R.id.btn_add_homework_add);
        btnAddHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = txtTitle.getEditText().getText().toString();
                instructions = txtInstructions.getEditText().getText().toString();
                points = txtPoints.getEditText().getText().toString();

                //Homework Object
                homework = new HomeworkModel(
                        coursesList.get(i).getShortName(),
                        coursesList.get(i).getFullName(),
                        title,
                        date,
                        instructions,
                        points,
                        professor.getId());

                //Adding it into the DB
                HomeworkDAO.addHomework(homework);

                dismiss();
            }
        });


        return rootView;
    }
}
