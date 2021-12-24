package com.example.unispark.controller.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.model.CourseModel;

public class DetailsCourse extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageView btnGoBack;
    //Get Intent Extras
    Bundle extras;
    CourseModel course;
    //Set Interface Text
    TextView txtShortName;
    TextView txtLongName;
    TextView txtAA;
    TextView txtCFU;
    TextView txtID;
    TextView txtFaculty;
    TextView txtSession;
    TextView txtLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course);

        //GoBack Button
        btnGoBack = findViewById(R.id.btn_detail_course_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Text
        course = (CourseModel) extras.getSerializable("Course");
        //Set Text
        txtShortName = findViewById(R.id.txt_course_short_name);
        txtShortName.setText(course.getShortName());
        txtLongName = findViewById(R.id.txt_course_full_name);
        txtLongName.setText(course.getFullName());
        txtAA = findViewById(R.id.txt_course_aa);
        txtAA.setText(course.getCourseYear());
        txtCFU = findViewById(R.id.txt_course_cfu);
        txtCFU.setText(course.getCfu());
        txtID = findViewById(R.id.txt_course_id);
        txtID.setText(course.getId());
        txtFaculty = findViewById(R.id.txt_course_prof1);
        txtFaculty.setText(course.getFaculty());
        txtSession = findViewById(R.id.txt_course_session);
        txtSession.setText(course.getSession());
        txtLink = findViewById(R.id.txt_course_link);
        txtLink.setText(course.getLink());
        //Clickable Link
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(course.getLink());
                startActivity(new Intent(Intent.ACTION_VIEW, uri));
            }
        });
    }
}