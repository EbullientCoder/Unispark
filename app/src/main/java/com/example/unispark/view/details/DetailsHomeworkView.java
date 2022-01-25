package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.controller.guicontroller.details.DetailsHomeworkGuiController;
import com.example.unispark.model.HomeworkModel;

public class DetailsHomeworkView extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    private ImageButton btnGoBack;
    //Set Interface Text
    private TextView txtShortName;
    private TextView txtTitle;
    private TextView txtExpiration;
    private TextView txtInstructions;
    private TextView txtPoints;
    //Submit Button
    private LinearLayout submitLayout;
    private Button btnSubmit;

    //Gui Controller
    private DetailsHomeworkGuiController detailsGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_homework);


        this.detailsGuiController = new DetailsHomeworkGuiController(this, (BeanHomework) getIntent().getExtras().getSerializable("Homework"));

        //GoBack Button
        this.btnGoBack = findViewById(R.id.btn_detail_homework_goback);
        this.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Submit Button
        this.btnSubmit = findViewById(R.id.btn_homework_detail_submit);
        this.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "WORK IN PROGRESS", Toast.LENGTH_SHORT);
            }
        });


        //Show
        this.txtShortName = findViewById(R.id.txt_homework_detail_subject);
        this.txtTitle = findViewById(R.id.txt_homework_detail_title);
        this.txtTitle.setAllCaps(true);
        this.txtExpiration = findViewById(R.id.txt_homework_detail_expiration);
        this.txtInstructions = findViewById(R.id.txt_homework_detail_instructions);
        this.txtPoints = findViewById(R.id.txt_homework_detail_points);

        //Gui Controller
        this.detailsGuiController.showDetails((String) getIntent().getExtras().getSerializable("HomeView"));

    }


    public void setTxtShortName(String content) {
        txtShortName.setText(content);
    }

    public void setTxtTitle(String content) {
        txtTitle.setText(content);
    }

    public void setTxtExpiration(String content) {
        txtExpiration.setText(content);
    }

    public void setTxtInstructions(String content) {
        txtInstructions.setText(content);
    }

    public void setTxtPoints(String content) {
        txtPoints.setText(content);
    }

    public void setSubmitLayout() {
        this.submitLayout = findViewById(R.id.submit_layout);
        this.submitLayout.setVisibility(View.INVISIBLE);
    }
}