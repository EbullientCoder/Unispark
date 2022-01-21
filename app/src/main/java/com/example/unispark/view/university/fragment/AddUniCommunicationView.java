package com.example.unispark.view.university.fragment;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.university.AddCommunicationGuiController;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.communication.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedUniversity;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.List;

public class AddUniCommunicationView extends DialogFragment {


    //Dismiss Button
    ImageButton btnDismiss;
    //Add Communication Button
    Button btnAddCommunication;
    //Add Photo Button
    ImageButton btnPhoto;
    //Title
    TextInputLayout txtTitle;
    String title;
    //Instructions
    TextInputLayout txtCommunication;
    String text;
    String date = "";
    //Faculty Selector
    List<String> faculties;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String facultySelection = "";
    UniCommunicationsAdapter communicationsAdapter;


    //Bean
    BeanLoggedUniversity bUniversity;
    BeanUniCommunication beanUniCommunication;
    List<BeanUniCommunication> beanUniCommunicationList;


    int i;


    //Gui Controller
    private AddCommunicationGuiController communicationGuiController;


    //Constructor
    public AddUniCommunicationView(BeanLoggedUniversity bUniversity,
                                   List<BeanUniCommunication> beanUniCommunicationList,
                                   UniCommunicationsAdapter communicationsAdapter) {
        //Getting Professor Object
        this.bUniversity = bUniversity;
        this.beanUniCommunicationList = beanUniCommunicationList;
        this.communicationsAdapter = communicationsAdapter;
        this.communicationGuiController = new AddCommunicationGuiController();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_university_communication, container, false);
        getDialog().setTitle("Simple Dialog");

        //Dismiss Button
        btnDismiss = rootView.findViewById(R.id.btn_goback);
        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //Button: Add Photo
        btnPhoto = rootView.findViewById(R.id.btn_add_uni_communication_photo);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                communicationGuiController.showAddMedia(getActivity());
            }
        });



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();

        //DropDown Selector
        faculties = bUniversity.getFaculties();
        //faculties.add("All");
        autoCompleteTxt = rootView.findViewById(R.id.add_uni_communication_select_faculty);
        adapterItems = new ArrayAdapter<>(getContext(), R.layout.item_container_item, faculties);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                facultySelection = (String)parent.getItemAtPosition(position);

                //Getting the selected Course position
                i = position;
            }
        });




        //Type
        txtTitle = rootView.findViewById(R.id.txt_add_uni_communication_title);
        //Communication
        txtCommunication = rootView.findViewById(R.id.txt_add_uni_communication_communication);

        //Add Communication
        btnAddCommunication = rootView.findViewById(R.id.btn_add_uni_communication_add);
        btnAddCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = txtTitle.getEditText().getText().toString();
                text = txtCommunication.getEditText().getText().toString();

                communicationGuiController.addCommunication(getContext(), getDialog(), title, text, facultySelection, date, beanUniCommunicationList,
                        communicationsAdapter);
            }
        });

        return rootView;
    }



}