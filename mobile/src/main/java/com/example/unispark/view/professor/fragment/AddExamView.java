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
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.Session;

import com.example.unispark.guicontroller.professor.AddExamGuiController;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.List;

public class AddExamView extends DialogFragment{


    //Course Selector
    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter adapterItems;

    //Date Picker
    private TextView txtDisplayDate;
    private DatePickerDialog.OnDateSetListener dateListener;
    //Hour
    private TextInputLayout txtHour;

    //Building
    private TextInputLayout txtBuilding;

    //Classroom
    private TextInputLayout txtClassroom;



    //Gui Controller
    private AddExamGuiController examGuiController;


    String courseSelection = "";
    String date = "";
    String classroom;
    String building;
    String hour;

    //Constructor
    public AddExamView(Session session) {
        this.examGuiController = new AddExamGuiController(session, this);
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_exam, container, false);
        getDialog().setTitle("Simple Dialog");

        this.adapterItems = new ArrayAdapter(this.getContext(), R.layout.item_container_item);

        //Dismiss Button
        ImageButton btnDismiss;
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        //Date Picker
        Calendar calendar;
        calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        //DropDown Selector
        this.autoCompleteTxt = rootView.findViewById(R.id.add_exam_select_course);
        //Gui Controller
        this.examGuiController.coursesNamesSelector();

        this.autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);
                //Getting the selected Course position
                examGuiController.selectPosition(position);
            }
        });


        this.txtDisplayDate = rootView.findViewById(R.id.txt_add_exam_selected_date);
        OffsetDateTime offset = OffsetDateTime.now();
        this.txtDisplayDate.setText(offset.getDayOfMonth() + "/" + offset.getMonthValue() + "/" + offset.getYear());

        ImageButton btnSelectDate;
        btnSelectDate = rootView.findViewById(R.id.btn_add_exam_select_date);


        this.dateListener = new DatePickerDialog.OnDateSetListener() {
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
                examGuiController.showDateDialog(year, month, day);
            }
        });



        //Creating the Exam
        //AA
        this.txtHour = rootView.findViewById(R.id.txt_add_exam_hour);
        //Building
        this.txtBuilding = rootView.findViewById(R.id.txt_add_exam_building);
        //Classroom
        this.txtClassroom = rootView.findViewById(R.id.txt_add_exam_classroom);

        //Add Exam
        Button btnAddExam;
        btnAddExam = rootView.findViewById(R.id.btn_add_exam_add);
        btnAddExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                hour = txtHour.getEditText().getText().toString();
                building = txtBuilding.getEditText().getText().toString();
                classroom = txtClassroom.getEditText().getText().toString();

                examGuiController.addExam(date, courseSelection, hour, building, classroom);
            }
        });

        return rootView;
    }




    public void setAdapterItems(List<String> coursesNames) {
        this.adapterItems.addAll(coursesNames);
        this.autoCompleteTxt.setAdapter(this.getAdapterItems());
    }


    public ArrayAdapter getAdapterItems() {
        return adapterItems;
    }



    public DatePickerDialog.OnDateSetListener getDateListener() {
        return dateListener;
    }

    public void setMessage(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
