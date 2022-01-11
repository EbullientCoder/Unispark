package com.example.unispark.controller.guicontroller.university.fragment;

import android.content.Intent;
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
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.controller.applicationcontroller.communications.AddUniCommunication;
import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.model.UniversityModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.List;

public class AddUniCommunicationGUIController extends DialogFragment {


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
    //Faculty Selector
    List<String> faculties;
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String facultySelection;
    //University Model
    UniversityModel university;
    //Homework Model
    UniversityCommunicationModel communication;
    List<UniversityCommunicationModel> uniCommunicationsItem;
    UniCommunicationsAdapter communicationsAdapter;
    int i;


    //Constructor
    public AddUniCommunicationGUIController(UniversityModel university,
                                            List<UniversityCommunicationModel> uniCommunicationsItem,
                                            UniCommunicationsAdapter communicationsAdapter) {
        //Getting Professor Object
        this.university = university;
        this.uniCommunicationsItem = uniCommunicationsItem;
        this.communicationsAdapter = communicationsAdapter;
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
                Intent i = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                final int ACTIVITY_SELECT_IMAGE = 1234;
                startActivityForResult(i, ACTIVITY_SELECT_IMAGE);
            }
        });



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        String date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();

        //DropDown Selector
        faculties = university.getFaculties();
        faculties.add("All");
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



        //Creating the Communication & Adding it into the Database
        //Type
        txtTitle = rootView.findViewById(R.id.txt_add_uni_communication_title);
        //Communication
        txtCommunication = rootView.findViewById(R.id.txt_add_uni_communication_communication);

        //Add Homework
        btnAddCommunication = rootView.findViewById(R.id.btn_add_uni_communication_add);
        btnAddCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = txtTitle.getEditText().getText().toString();
                text = txtCommunication.getEditText().getText().toString();

                //Communication Object
                communication = new UniversityCommunicationModel(R.drawable.blank_img,
                        title,
                        date,
                        text,
                        facultySelection);

                //Application Controller
                AddUniCommunication addCommunicationAppController = new AddUniCommunication();
                addCommunicationAppController.addCommunication(communication);

                //Notify the Communications Adapter
                uniCommunicationsItem.add(0, communication);
                communicationsAdapter.notifyDataSetChanged();

                dismiss();
            }
        });

        return rootView;
    }
}