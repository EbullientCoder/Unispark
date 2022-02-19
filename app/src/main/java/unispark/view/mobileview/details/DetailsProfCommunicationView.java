package unispark.view.mobileview.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.controller.guicontroller.details.DetailsProfCommunicationGuiController;


public class DetailsProfCommunicationView extends AppCompatActivity {
    //Attributes

    //Display Parameters
    private TextView txtShortName;
    private ImageView imgProfProfile;
    private TextView txtFullName;
    private TextView txtProfName;
    private TextView txtDate;
    private TextView txtType;
    private TextView txtCommunication;


    DetailsProfCommunicationGuiController detailsGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_prof_communication);

        this.detailsGuiController = new DetailsProfCommunicationGuiController(this, (BeanProfessorCommunication) getIntent().getExtras().getSerializable("Communication"));

        //GoBack Button
        ImageButton btnGoBack;
        btnGoBack = findViewById(R.id.btn_detail_prof_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Show
        txtShortName = findViewById(R.id.txt_prof_com_shortname);
        txtFullName = findViewById(R.id.txt_prof_com_fullname);
        imgProfProfile = findViewById(R.id.img_prof_com_image);
        txtProfName = findViewById(R.id.txt_prof_com_name);
        txtDate = findViewById(R.id.txt_prof_com_date);
        txtType = findViewById(R.id.txt_prof_com_type);
        txtCommunication = findViewById(R.id.txt_prof_com_communication);

        //Gui Controller
        this.detailsGuiController.showDetails();

    }


    public void setTxtShortName(String content) {
        txtShortName.setText(content);
    }

    public void setImgProfProfile(int content) {
        imgProfProfile.setImageResource(content);
    }

    public void setTxtFullName(String content) {
        txtFullName.setText(content);
    }

    public void setTxtProfName(String content) {
        txtProfName.setText(content);
    }

    public void setTxtDate(String content) {
        txtDate.setText(content);
    }

    public void setTxtType(String content) {
        txtType.setText(content);
    }

    public void setTxtCommunication(String content) {
        txtCommunication.setText(content);
    }
}