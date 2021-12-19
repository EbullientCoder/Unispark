package com.example.unispark.controller.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.controller.student.Home;
import com.example.unispark.R;

public class DetailsProfCommunication extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageButton btnGoBack;
    //Get Intent Extras
    Bundle extras;
    String shortName;
    int imageProf;
    String profName;
    String date;
    String type;
    String communication;
    //Display Parameters
    TextView txtShortName;
    ImageView imgProfProfile;
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
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(0, 0);
            }
        });


        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Parameters
        shortName = extras.getString("ShortName");
        imageProf = extras.getInt("ProfessorImage");
        profName = extras.getString("ProfessorName");
        date = extras.getString("Date");
        type = extras.getString("Type");
        communication = extras.getString("Communication");
        //Set Parameters
        txtShortName = findViewById(R.id.txt_prof_com_shortname);
        txtShortName.setText(shortName);
        imgProfProfile = findViewById(R.id.img_prof_com_image);
        imgProfProfile.setImageResource(imageProf);
        txtProfName = findViewById(R.id.txt_prof_com_name);
        txtProfName.setText(profName);
        txtDate = findViewById(R.id.txt_prof_com_date);
        txtDate.setText(date);
        txtType = findViewById(R.id.txt_prof_com_type);
        txtType.setText(type);
        txtCommunication = findViewById(R.id.txt_prof_com_communication);
        txtCommunication.setText(communication);
    }
}