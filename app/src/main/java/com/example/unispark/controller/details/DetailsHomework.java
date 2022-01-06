package com.example.unispark.controller.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.model.HomeworkModel;

public class DetailsHomework extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageButton btnGoBack;
    //Get Intent Extras
    Bundle extras;
    HomeworkModel homework;
    //Set Interface Text
    TextView txtShortName;
    TextView txtTitle;
    TextView txtExpiration;
    TextView txtInstructions;
    TextView txtPoints;
    //Get StudentHomeGUIController
    String home;
    //Submit Button
    LinearLayout submitLayout;
    Button btnSubmit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_homework);

        //GoBack Button
        btnGoBack = findViewById(R.id.btn_detail_homework_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Submit Button
        btnSubmit = findViewById(R.id.btn_homework_detail_submit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "WORK IN PROGRESS", Toast.LENGTH_SHORT);
            }
        });


        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Text
        homework = (HomeworkModel) extras.getSerializable("Homework");
        //Set Text
        txtShortName = findViewById(R.id.txt_homework_detail_subject);
        txtShortName.setText(homework.getShortName());
        txtTitle = findViewById(R.id.txt_homework_detail_title);
        txtTitle.setText(homework.getTitle());
        txtTitle.setAllCaps(true);
        txtExpiration = findViewById(R.id.txt_homework_detail_expiration);
        txtExpiration.setText(homework.getExpiration());
        txtInstructions = findViewById(R.id.txt_homework_detail_instructions);
        txtInstructions.setText(homework.getInstructions());
        txtPoints = findViewById(R.id.txt_homework_detail_points);
        txtPoints.setText(homework.getPoints());
        //Get StudentHomeGUIController
        home = extras.getString("StudentHomeGUIController");

        //ProfessorHomework
        if(home.equals("ProfessorHome")){
            submitLayout = findViewById(R.id.submit_layout);
            submitLayout.setVisibility(View.INVISIBLE);
        }
    }
}