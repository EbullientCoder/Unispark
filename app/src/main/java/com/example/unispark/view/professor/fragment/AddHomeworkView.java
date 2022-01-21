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

import java.time.OffsetDateTime;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.professor.AddHomeworkGuiController;
import com.example.unispark.viewadapter.HomeworksAdapter;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.login.BeanLoggedProfessor;

import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.List;

public class AddHomeworkView extends DialogFragment{


    //Dismiss Button
    ImageButton btnDismiss;
    //Add Homework Button
    Button btnAddHomework;
    //Title
    TextInputLayout txtTitle;
    //Instructions
    TextInputLayout txtInstructions;
    //Date Picker
    TextView txtDisplayDate;
    ImageButton btnSelectDate;
    DatePickerDialog.OnDateSetListener dateListener;
    Calendar calendar;
    String date = "";
    //Points
    TextInputLayout txtPoints;
    //Course Selector
    List<String> courses;
    String courseSelection = "";
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;


    //Bean
    BeanLoggedProfessor bProfessor;
    List<BeanCourse> bCourses;
    List<BeanHomework> bHomeworkList = null;
    HomeworksAdapter homeworksAdapter = null;


    int i;


    AddHomeworkGuiController homeworkGuiController;


    //Constructor
    public AddHomeworkView(BeanLoggedProfessor bProfessor){
        this.bProfessor = bProfessor;
        this.homeworkGuiController = new AddHomeworkGuiController();
    }

    public AddHomeworkView(BeanLoggedProfessor bProfessor, List<BeanHomework> bHomeworkList, HomeworksAdapter homeworksAdapter) {
        //Getting Professor Object
        this.bProfessor = bProfessor;
        this.bHomeworkList = bHomeworkList;
        this.homeworksAdapter = homeworksAdapter;
        this.homeworkGuiController = new AddHomeworkGuiController();
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
        //Gui controller
        bCourses = homeworkGuiController.showCourses(bProfessor);
        courses = homeworkGuiController.getCoursesNames(bProfessor);

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
                homeworkGuiController.showDateDialog(getContext(), dateListener, year, month, day);
            }
        });
        dateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);

                txtDisplayDate.setText(date);
            }
        };




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
                String title = txtTitle.getEditText().getText().toString();
                String instructions = txtInstructions.getEditText().getText().toString();
                String points = txtPoints.getEditText().getText().toString();

                homeworkGuiController.addHomework(getContext(), getDialog(), courseSelection, title, instructions,
                        points, bCourses.get(i).getShortName(), bCourses.get(i).getFullName(), date, bProfessor.getId(),
                        homeworksAdapter, bHomeworkList);
            }
        });


        return rootView;
    }
}
