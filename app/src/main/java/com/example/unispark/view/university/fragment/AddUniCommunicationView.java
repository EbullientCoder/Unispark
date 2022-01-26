package com.example.unispark.view.university.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.example.unispark.R;
import com.example.unispark.Session;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.controller.guicontroller.university.AddCommunicationGuiController;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.List;

public class AddUniCommunicationView extends DialogFragment {


    //Dismiss Button
    private ImageButton btnDismiss;
    //Add Communication Button
    private Button btnAddCommunication;
    //Add Photo Button
    private ImageButton btnPhoto;
    //Title
    private TextInputLayout txtTitle;
    //Instructions
    private TextInputLayout txtCommunication;
    //Faculty Selector
    List<String> faculties;
    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter adapterItems;
    private UniCommunicationsAdapter communicationsAdapter;




    //Gui Controller
    private AddCommunicationGuiController communicationGuiController;



    String date = "";
    String text;
    String facultySelection = "";
    String title;


    //Constructor
    public AddUniCommunicationView(Session session, List<BeanUniCommunication> beanUniCommunicationList,
                                   UniCommunicationsAdapter communicationsAdapter) {

        this.communicationsAdapter = communicationsAdapter;
        this.communicationGuiController = new AddCommunicationGuiController(session, beanUniCommunicationList, this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_university_communication, container, false);
        getDialog().setTitle("Simple Dialog");


        this.adapterItems = new ArrayAdapter(getContext(), R.layout.item_container_item);

        //Dismiss Button
        this.btnDismiss = rootView.findViewById(R.id.btn_goback);
        this.btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });



        //Button: Add Photo
        this.btnPhoto = rootView.findViewById(R.id.btn_add_uni_communication_photo);
        this.btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                communicationGuiController.showAddMedia();
            }
        });



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();

        //DropDown Selector
        this.autoCompleteTxt = rootView.findViewById(R.id.add_uni_communication_select_faculty);
        this.communicationGuiController.showFaculties();
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                facultySelection = (String)parent.getItemAtPosition(position);

            }
        });




        //Type
        this.txtTitle = rootView.findViewById(R.id.txt_add_uni_communication_title);
        //Communication
        this.txtCommunication = rootView.findViewById(R.id.txt_add_uni_communication_communication);

        //Add Communication
        this.btnAddCommunication = rootView.findViewById(R.id.btn_add_uni_communication_add);
        this.btnAddCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                title = txtTitle.getEditText().getText().toString();
                text = txtCommunication.getEditText().getText().toString();

                communicationGuiController.addCommunication(title, text, facultySelection, date);
            }
        });

        return rootView;
    }



    public void setAdapterItems(List<String> faculties) {
        this.adapterItems.addAll(faculties);
        this.autoCompleteTxt.setAdapter(this.getAdapterItems());
    }



    public ArrayAdapter getAdapterItems() {
        return adapterItems;
    }

    public void setMessage(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void notifyDataChanged(){
        this.communicationsAdapter.notifyDataSetChanged();
    }
}