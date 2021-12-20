package com.example.unispark.controller.professor.fragment;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import java.time.OffsetDateTime;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;

import java.time.OffsetDateTime;
import java.util.Calendar;

public class AddHomeworkFragment extends DialogFragment{
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Title
    //Date Picker
    TextView txtDisplayDate;
    ImageButton btnSelectDate;
    DatePickerDialog.OnDateSetListener dateListener;
    Calendar calendar;


    //Methods
    //Constructor
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
                String date = Integer.toString(day) + " / " + Integer.toString(month) + " / " + Integer.toString(year);

                txtDisplayDate.setText(date);
            }
        };


        return rootView;
    }
}
