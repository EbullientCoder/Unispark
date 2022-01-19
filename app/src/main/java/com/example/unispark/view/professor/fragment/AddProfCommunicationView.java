package com.example.unispark.view.professor.fragment;

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
import com.example.unispark.bean.BeanProfessorCommunication;
import com.example.unispark.bean.login.BeanLoggedProfessor;

import com.example.unispark.controller.guicontroller.professor.AddCommunicationGuiController;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.List;

public class AddProfCommunicationView extends DialogFragment{


    //Dismiss Button
    ImageButton btnDismiss;
    //Add Communication Button
    Button btnAddCommunication;
    //Title
    TextInputLayout txtType;
    //Instructions
    TextInputLayout txtCommunication;
    //Course Selector
    List<String> courses;
    String courseSelection = "";
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;


    //Bean
    BeanLoggedProfessor bProfessor;
    BeanProfessorCommunication bCommunication;
    int i;


    private AddCommunicationGuiController communicationGuiController;


    //Constructor
    public AddProfCommunicationView(BeanLoggedProfessor bProfessor) {
        //Getting Professor Object
        this.bProfessor = bProfessor;
        this.communicationGuiController = new AddCommunicationGuiController();
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
        //Gui Controller
        courses = communicationGuiController.getCoursesNames(bProfessor);


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


        //Creating the Communication & Adding it into the Database
        //Type
        txtType = rootView.findViewById(R.id.txt_add_communication_type);
        //Communication
        txtCommunication = rootView.findViewById(R.id.txt_add_communication_communication);



        //Add Communication
        btnAddCommunication = rootView.findViewById(R.id.btn_add_communciation_add);
        btnAddCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = txtType.getEditText().getText().toString();
                String text = txtCommunication.getEditText().getText().toString();


                communicationGuiController.addCommunication(getContext(), getDialog(), courseSelection,
                        type, text, bProfessor.getProfilePicture(), bProfessor.getCourses().get(i).getShortName(),
                        bProfessor.getCourses().get(i).getFullName(), bProfessor.getFirstName(),
                        bProfessor.getLastName(), date);
            }
        });

        return rootView;
    }
}
