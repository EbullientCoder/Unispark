package unispark.view.details;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.unispark.R;
import unispark.engeneeringclasses.bean.professor.BeanProfessorDetails;
import unispark.controller.guicontroller.details.DetailsProfessorGuiController;



public class DetailsProfessorView extends AppCompatActivity {
    //Attributes
    //Set Interface Text
    private ImageView imgProfImage;
    private TextView txtProfName;
    private TextView txtWebsite;
    private TextView txtCourse1;
    private TextView txtLink1;
    private TextView txtCourse2;
    private TextView txtLink2;
    private TextView txtCourse3;
    private TextView txtLink3;

    private DetailsProfessorGuiController professorDetailsGuiController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_professor);


        this.professorDetailsGuiController = new DetailsProfessorGuiController(this, (BeanProfessorDetails) getIntent().getExtras().getSerializable("Professor"));

        //GoBack Button
        ImageView btnGoBack;
        btnGoBack = findViewById(R.id.btn_detail_professor_goback);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        //Display Parameters
        this.imgProfImage = findViewById(R.id.img_professor_detail_image);
        this.txtProfName = findViewById(R.id.txt_professor_detail_fullname);
        this.txtWebsite = findViewById(R.id.txt_professor_detail_website);

        //Gui Controller
        this.professorDetailsGuiController.showDetails();

        //Clickable Website
        this.txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorDetailsGuiController.goToLink();
            }
        });


        this.txtCourse1 = findViewById(R.id.txt_professor_detail_course1);
        this.txtLink1 = findViewById(R.id.txt_professor_detail_link1);
        this.professorDetailsGuiController.showCourseFirstLink();

        //Clickable Link1
        this.txtLink1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                professorDetailsGuiController.selectFirstLink();
            }
        });


        //Course 2
        this.txtCourse2 = findViewById(R.id.txt_professor_detail_course2);
        this.txtLink2 = findViewById(R.id.txt_professor_detail_link2);

        if(this.professorDetailsGuiController.showCourseSecondLink()){

            //Clickable Link2
            this.txtLink2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    professorDetailsGuiController.selectSecondLink();
                }
            });
        }


        //Course 3
        this.txtCourse3 = findViewById(R.id.txt_professor_detail_course3);
        this.txtLink3 = findViewById(R.id.txt_professor_detail_link3);
        if(this.professorDetailsGuiController.showCourseThirdLink()){

            //Clickable Link3
            this.txtLink3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    professorDetailsGuiController.selectThirdLink();
                }
            });
        }
    }





    public void setImgProfImage(int content) {
        this.imgProfImage.setImageResource(content);
    }

    public void setTxtProfName(String content) {
        this.txtProfName.setText(content);
    }

    public void setTxtWebsite(String content) {
        this.txtWebsite.setText(content);
    }

    public void setTxtCourse1(String content) {
        this.txtCourse1.setText(content);
    }

    public void setTxtLink1(String content) {
        this.txtLink1.setText(content);
    }


    public void setTxtCourse2(String content) {
        this.txtCourse2.setText(content);
    }

    public void setTxtLink2(String content) {
        this.txtLink2.setText(content);
    }

    public void setTxtCourse3(String content) {
        this.txtCourse3.setText(content);
    }

    public void setTxtLink3(String content) {
        this.txtLink3.setText(content);
    }
}