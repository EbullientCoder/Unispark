package com.example.unispark.controller.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.controller.student.Home;
import com.example.unispark.R;

public class DetailsUniCommunication extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageButton btnGoBack;
    //Get Intent Extras
    Bundle extras;
    int imageCommunication;
    String title;
    String date;
    String communication;
    //Display Parameters
    ImageView imgComBackground;
    TextView txtTitle;
    TextView txtDate;
    TextView txtCommunication;
    //Get Context
    String home;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_uni_communication);


        //GoBack Button
        btnGoBack = findViewById(R.id.btn_detail_uni_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Parameters
        imageCommunication = extras.getInt("CommunicationImage");
        title = extras.getString("Title");
        date = extras.getString("Date");
        communication = extras.getString("Communication");
        //Set Parameters
        imgComBackground = findViewById(R.id.img_uni_com_background);
        imgComBackground.setImageResource(imageCommunication);
        txtTitle = findViewById(R.id.txt_uni_com_title);
        txtTitle.setText(title);
        txtDate = findViewById(R.id.txt_uni_com_date);
        txtDate.setText(date);
        txtCommunication = findViewById(R.id.txt_uni_com_text);
        txtCommunication.setText(communication);
        //Scrolling Communication
        txtCommunication.setMovementMethod(new ScrollingMovementMethod());
        //Home
        home = extras.getString("Home");
    }
}