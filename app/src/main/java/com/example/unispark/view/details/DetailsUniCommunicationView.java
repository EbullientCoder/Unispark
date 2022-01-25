package com.example.unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.controller.guicontroller.details.DetailsUniCommunicationGuiController;

public class DetailsUniCommunicationView extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    private ImageButton btnGoBack;
    //Get Intent Extras
    //Display Parameters
    private ImageView imgComBackground;
    private TextView txtTitle;
    private TextView txtDate;
    private TextView txtCommunication;

    //Gui Controller
    private DetailsUniCommunicationGuiController detailsGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_uni_communication);

        this.detailsGuiController = new DetailsUniCommunicationGuiController(this, (BeanUniCommunication) getIntent().getExtras().getSerializable("Communication"));

        //GoBack Button
        this.btnGoBack = findViewById(R.id.btn_detail_uni_goback);
        this.btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Set Parameters
        this.imgComBackground = findViewById(R.id.img_uni_com_background);
        this.txtTitle = findViewById(R.id.txt_uni_com_title);
        this.txtDate = findViewById(R.id.txt_uni_com_date);
        this.txtCommunication = findViewById(R.id.txt_uni_com_text);

        //Gui Controller
        this.detailsGuiController.showDetails();
    }


    public void setImgComBackground(int content) {
        this.imgComBackground.setImageResource(content);
    }

    public void setTxtTitle(String content) {
        this.txtTitle.setText(content);
    }

    public void setTxtDate(String content) {
        this.txtDate.setText(content);
    }

    public void setTxtCommunication(String content) {
        this.txtCommunication.setText(content);
        this.txtCommunication.setMovementMethod(new ScrollingMovementMethod());
    }
}