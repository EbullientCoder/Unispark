package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.controller.guicontroller.DetailsGuiController;
import com.example.unispark.model.CourseModel;

public class DetailsCourseView extends AppCompatActivity {
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

    private DetailsGuiController detailsCourseGuiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course);

        this.detailsCourseGuiController = new DetailsGuiController();

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
        BeanCourse bCourse = (BeanCourse) extras.getSerializable("Course");
        //Set Text
        txtShortName = findViewById(R.id.txt_course_short_name);
        txtShortName.setText(bCourse.getShortName());
        txtLongName = findViewById(R.id.txt_course_full_name);
        txtLongName.setText(bCourse.getFullName());
        txtAA = findViewById(R.id.txt_course_aa);
        txtAA.setText(bCourse.getCourseYear());
        txtCFU = findViewById(R.id.txt_course_cfu);
        txtCFU.setText(bCourse.getCfu());
        txtID = findViewById(R.id.txt_course_id);
        txtID.setText(bCourse.getId());
        txtFaculty = findViewById(R.id.txt_course_prof1);
        txtFaculty.setText(bCourse.getFaculty());
        txtSession = findViewById(R.id.txt_course_session);
        txtSession.setText(bCourse.getSession());
        txtLink = findViewById(R.id.txt_course_link);
        txtLink.setText(bCourse.getLink());
        //Clickable Link
        txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsCourseGuiController.goToLink(getApplicationContext(), bCourse.getLink());
            }
        });
    }
}