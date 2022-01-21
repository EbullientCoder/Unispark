package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.unispark.R;
import com.example.unispark.bean.course.BeanCourse;
import com.example.unispark.bean.BeanProfessorDetails;
import com.example.unispark.controller.guicontroller.DetailsGuiController;


import java.util.List;

public class DetailsProfessorView extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageView btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Set Interface Text
    ImageView imgProfImage;
    TextView txtProfName;
    TextView txtWebsite;
    TextView txtCourse1;
    TextView txtLink1;
    TextView txtCourse2;
    TextView txtLink2;
    TextView txtCourse3;
    TextView txtLink3;

    private DetailsGuiController professorDetailsGuiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_professor);

        this.professorDetailsGuiController = new DetailsGuiController();



        //GoBack Button
        btnGoBack = findViewById(R.id.btn_detail_professor_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //Get Intent Extras Data
        extras = getIntent().getExtras();
        //Get Parameters
        BeanProfessorDetails professor = (BeanProfessorDetails) extras.getSerializable("Professor");

        int imageID = professor.getProfilePicture();
        String firstname = professor.getFirstName();
        String lastname = professor.getLastName();
        String website = professor.getWebsite();
        List<BeanCourse> courses = professor.getCourses();



        //Display Parameters
        imgProfImage = findViewById(R.id.img_professor_detail_image);
        imgProfImage.setImageResource(imageID);
        txtProfName = findViewById(R.id.txt_professor_detail_fullname);
        txtProfName.setText(firstname + ' ' + lastname);
        txtWebsite = findViewById(R.id.txt_professor_detail_website);
        txtWebsite.setText(website);
        //Clickable Website
        txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorDetailsGuiController.goToLink(getApplicationContext(), website);
            }
        });
        txtCourse1 = findViewById(R.id.txt_professor_detail_course1);
        txtCourse1.setText(courses.get(0).getFullName());
        txtLink1 = findViewById(R.id.txt_professor_detail_link1);
        txtLink1.setText(courses.get(0).getLink());
        //Clickable Link1
        txtLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorDetailsGuiController.goToLink(getApplicationContext(), courses.get(0).getLink());
            }
        });
        //Course 2
        txtCourse2 = findViewById(R.id.txt_professor_detail_course2);
        txtLink2 = findViewById(R.id.txt_professor_detail_link2);
        if(courses.size() == 2){
            txtCourse2.setText(courses.get(1).getFullName());
            txtLink2.setText(courses.get(1).getLink());
            //Clickable Link2
            txtLink2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    professorDetailsGuiController.goToLink(getApplicationContext(), courses.get(1).getLink());
                }
            });
        }
        else{
            txtCourse2.setText("//");
            txtLink2.setText("//");
        }
        //Course 3
        txtCourse3 = findViewById(R.id.txt_professor_detail_course3);
        txtLink3 = findViewById(R.id.txt_professor_detail_link3);
        if(courses.size() == 3){
            txtCourse3.setText(courses.get(2).getFullName());
            txtLink3.setText(courses.get(2).getLink());
            //Clickable Link3
            txtLink3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    professorDetailsGuiController.goToLink(getApplicationContext(), courses.get(2).getLink());
                }
            });
        }
        else{
            txtCourse3.setText("//");
            txtLink3.setText("//");
        }
    }
}