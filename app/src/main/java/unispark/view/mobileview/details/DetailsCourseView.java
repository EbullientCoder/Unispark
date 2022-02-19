package unispark.view.mobileview.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.controller.guicontroller.details.DetailsCourseGuiController;

public class DetailsCourseView extends AppCompatActivity {
    //Attributes
    //Button: GoBack
    // Interface Text
    private TextView txtShortName;
    private TextView txtLongName;
    private TextView txtAA;
    private TextView txtCFU;
    private TextView txtID;
    private TextView txtFaculty;
    private TextView txtSession;
    private TextView txtLink;


    private DetailsCourseGuiController detailsGuiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course);

        this.detailsGuiController = new DetailsCourseGuiController(this, (BeanCourse) getIntent().getExtras().getSerializable("Course"));

        //GoBack Button
        ImageView btnGoBack;
        btnGoBack = findViewById(R.id.btn_detail_course_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



        //Show
        this.txtShortName = findViewById(R.id.txt_course_short_name);
        this.txtLongName = findViewById(R.id.txt_course_full_name);
        this.txtAA = findViewById(R.id.txt_course_aa);
        this.txtCFU = findViewById(R.id.txt_course_cfu);
        this.txtID = findViewById(R.id.txt_course_id);
        this.txtFaculty = findViewById(R.id.txt_course_prof1);
        this.txtSession = findViewById(R.id.txt_course_session);
        this.txtLink = findViewById(R.id.txt_course_link);

        //Gui Controller
        this.detailsGuiController.showDetails();
        //Clickable Link
        this.txtLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailsGuiController.goToLink();
            }
        });
    }

    public void setTxtShortName(String content) {
        this.txtShortName.setText(content);
    }

    public void setTxtLongName(String content) {
        this.txtLongName.setText(content);
    }

    public void setTxtAA(String content) {
        this.txtAA.setText(content);
    }

    public void setTxtCFU(String content) {
        this.txtCFU.setText(content);
    }

    public void setTxtID(String content) {
        this.txtID.setText(content);
    }

    public void setTxtFaculty(String content) {
        this.txtFaculty.setText(content);
    }

    public void setTxtSession(String content) {
        this.txtSession.setText(content);
    }

    public void setTxtLink(String content) {
        this.txtLink.setText(content);
    }
}