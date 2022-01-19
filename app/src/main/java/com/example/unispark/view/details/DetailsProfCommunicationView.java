package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.bean.BeanProfessorCommunication;


public class DetailsProfCommunicationView extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageButton btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Display Parameters
    TextView txtShortName;
    ImageView imgProfProfile;
    TextView txtFullName;
    TextView txtProfName;
    TextView txtDate;
    TextView txtType;
    TextView txtCommunication;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_prof_communication);

        //GoBack Button
        btnGoBack = findViewById(R.id.btn_detail_prof_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Parameters
        BeanProfessorCommunication communication = (BeanProfessorCommunication) extras.getSerializable("Communication");
        //Set Parameters
        txtShortName = findViewById(R.id.txt_prof_com_shortname);
        txtShortName.setText(communication.getShortCourseName());
        txtFullName = findViewById(R.id.txt_prof_com_fullname);
        txtFullName.setText(communication.getFullName());
        imgProfProfile = findViewById(R.id.img_prof_com_image);
        imgProfProfile.setImageResource(communication.getProfilePhoto());
        txtProfName = findViewById(R.id.txt_prof_com_name);
        txtProfName.setText(communication.getProfessorName());
        txtDate = findViewById(R.id.txt_prof_com_date);
        txtDate.setText(communication.getDate());
        txtType = findViewById(R.id.txt_prof_com_type);
        txtType.setText(communication.getType());
        txtCommunication = findViewById(R.id.txt_prof_com_communication);
        txtCommunication.setText(communication.getCommunication());
    }
}