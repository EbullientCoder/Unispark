package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.bean.BeanUniCommunication;
import com.example.unispark.model.communications.UniversityCommunicationModel;

public class DetailsUniCommunicationView extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    ImageButton btnGoBack;
    //Get Intent Extras
    Bundle extras;
    //Display Parameters
    ImageView imgComBackground;
    TextView txtTitle;
    TextView txtDate;
    TextView txtCommunication;


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
        BeanUniCommunication communication = (BeanUniCommunication) extras.getSerializable("Communication");
        //Set Parameters
        imgComBackground = findViewById(R.id.img_uni_com_background);
        imgComBackground.setImageResource(communication.getBackground());
        txtTitle = findViewById(R.id.txt_uni_com_title);
        txtTitle.setText(communication.getTitle());
        txtDate = findViewById(R.id.txt_uni_com_date);
        txtDate.setText(communication.getDate());
        txtCommunication = findViewById(R.id.txt_uni_com_text);
        txtCommunication.setText(communication.getCommunication());
        //Scrolling Communication
        txtCommunication.setMovementMethod(new ScrollingMovementMethod());
    }
}