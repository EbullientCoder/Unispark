package unispark.view.mobileview.professor.fragment;

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
import unispark.engeneeringclasses.others.Session;


import unispark.controller.guicontroller.mobilecontroller.professor.AddCommunicationGuiController;
import com.google.android.material.textfield.TextInputLayout;

import java.time.OffsetDateTime;
import java.util.List;

public class AddProfCommunicationView extends DialogFragment{


    //Title
    private TextInputLayout txtType;
    //Instructions
    private TextInputLayout txtCommunication;
    //Course Selector

    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter adapterItems;


    private AddCommunicationGuiController communicationGuiController;


    String courseSelection = "";


    //Constructor
    public AddProfCommunicationView(Session session) {
        this.communicationGuiController = new AddCommunicationGuiController(session, this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add_communication, container, false);
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



        //Calendar
        OffsetDateTime offset = OffsetDateTime.now();
        String date = offset.getYear() + "-" + offset.getMonthValue() + "-" + offset.getDayOfMonth();


        //DropDown Selector
        this.autoCompleteTxt = rootView.findViewById(R.id.add_communication_select_course);
        //Gui Controller
        this.communicationGuiController.coursesNamesSelector();

        this.autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                courseSelection = (String)parent.getItemAtPosition(position);

                //Getting the selected Course position
                communicationGuiController.selectPosition(position);
            }
        });



        //Type
        this.txtType = rootView.findViewById(R.id.txt_add_communication_type);
        //Communication
        this.txtCommunication = rootView.findViewById(R.id.txt_add_communication_communication);



        //Add Communication
        Button btnAddCommunication;
        btnAddCommunication = rootView.findViewById(R.id.btn_add_communciation_add);
        btnAddCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String type = txtType.getEditText().getText().toString();
                String text = txtCommunication.getEditText().getText().toString();


                communicationGuiController.addCommunication(courseSelection, type, text, date);
            }
        });

        return rootView;
    }


    public void setMessage(String message){
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void setAdapterItems(List<String> coursesNames) {
        this.adapterItems.addAll(coursesNames);
        this.autoCompleteTxt.setAdapter(this.getAdapterItems());
    }

    public ArrayAdapter<String> getAdapterItems() {
        return adapterItems;
    }
}
