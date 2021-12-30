package com.example.unispark.controller.professor.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddCommunicationFragment extends DialogFragment{
    //Attributes
    //Dismiss Button
    ImageButton btnDismiss;
    //Add Communication Button
    Button btnAddCommunication;
    //Title
    TextInputLayout txtType;
    String type;
    //Instructions
    TextInputLayout txtCommunication;
    String text;
    //Course Selector
    List<String> courses;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String courseSelection;
    //Professor Model
    ProfessorModel professor;
    List<CourseModel> coursesList;
    //Homework Model
    ProfessorCommunicationModel communication;

    int i;
    private static final String JOIN = "JOIN";



    //Methods
    //Constructor
    public AddCommunicationFragment(ProfessorModel professor) {
        //Getting Professor Object
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



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        String date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();

        //DropDown Selector
        coursesList = professor.getCourses();
        courses = new ArrayList<>(coursesList.size());
        for(i = 0; i < coursesList.size(); i++) courses.add(coursesList.get(i).getShortName());

        autoCompleteTxt = rootView.findViewById(R.id.add_communication_select_course);
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


        //Creating the Homework & Adding it into the Database
        //Type
        txtType = rootView.findViewById(R.id.txt_add_communication_type);
        //Communication
        txtCommunication = rootView.findViewById(R.id.txt_add_communication_communication);

        //Add Homework
        btnAddCommunication = rootView.findViewById(R.id.btn_add_communciation_add);
        btnAddCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = txtType.getEditText().getText().toString();
                text = txtCommunication.getEditText().getText().toString();

                //Communication Object
                communication = new ProfessorCommunicationModel(
                        professor.getImage(),
                        coursesList.get(i).getShortName(),
                        coursesList.get(i).getFullName(),
                        professor.getFirstName() + " " + professor.getLastName(),
                        date,
                        type,
                        text);

                //Adding it into the DB
                CommunicationsDAO.addProfessorCommunication(communication);

                dismiss();
            }
        });

        return rootView;
    }
}
