package com.example.unispark.controller.professor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;

import java.util.List;

public class AddCommunicationFragment extends DialogFragment{
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Model
    ProfessorModel professor;
    List<CourseModel> coursesList;

    private static final String JOIN = "JOIN";

    public AddCommunicationFragment(ProfessorModel professor) {
        this.professor = professor;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_communication, container, false);
        getDialog().setTitle("Simple Dialog");

        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return rootView;
    }
}
