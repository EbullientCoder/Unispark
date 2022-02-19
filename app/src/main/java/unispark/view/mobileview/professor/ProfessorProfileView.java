package unispark.view.mobileview.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;
import unispark.controller.guicontroller.professor.ManageProfessorProfileGuiController;
import unispark.view.mobileview.viewadapter.CoursesAdapter;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorProfileView extends AppCompatActivity
        implements CoursesAdapter.OnCourseClickListener{

    //Attributes

    //Floating Button
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnExam;
    private TextView txtExam;
    private FloatingActionButton btnHomework;
    private TextView txtHomework;
    private FloatingActionButton btnCommunication;
    private TextView txtCommunication;


    private ImageView imgProfImage;
    private TextView txtProfName;
    private TextView txtWebsite;
    //Courses
    private RecyclerView rvCourses;
    private CoursesAdapter coursesAdapter;



    //Gui Controller
    private ManageProfessorProfileGuiController profileGuiController;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_profile);

        this.profileGuiController = new ManageProfessorProfileGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.coursesAdapter = new CoursesAdapter(this, "PROFESSOR");



        //Bottom Navigation Menu
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.professor_profile);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                profileGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });


        this.btnAdd = findViewById(R.id.btn_add);
        this.btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                profileGuiController.expandButton();
            }
        });


        //Button: Add Exam
        this.txtExam = findViewById(R.id.txt_add_exam);
        this.txtExam.setVisibility(View.GONE);

        this.btnExam = findViewById(R.id.btn_add_exam);
        this.btnExam.setImageTintList(null);
        this.btnExam.setVisibility(View.GONE);
        this.btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.showAddExam();
            }
        });
        //Button: Add Homework
        this.txtHomework = findViewById(R.id.txt_add_homework);
        this.txtHomework.setVisibility(View.GONE);

        this.btnHomework = findViewById(R.id.btn_add_homework);
        this.btnHomework.setImageTintList(null);
        this.btnHomework.setVisibility(View.GONE);
        this.btnHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.showAddHomework();
            }
        });


        //Button: Add Communication
        this.txtCommunication = findViewById(R.id.txt_add_communication);
        this.txtCommunication.setVisibility(View.GONE);

        this.btnCommunication = findViewById(R.id.btn_add_communication);
        this.btnCommunication.setImageTintList(null);
        this.btnCommunication.setVisibility(View.GONE);
        this.btnCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.showAddCommunication();
            }
        });


        //Display Parameters
        this.imgProfImage = findViewById(R.id.img_professor_profile_image);
        this.txtProfName = findViewById(R.id.txt_professor_fullname);
        this.txtWebsite = findViewById(R.id.txt_professor_website);



        //Courses
        this.rvCourses = findViewById(R.id.rv_professor_courses);
        //Gui Controller
        this.profileGuiController.showCourses();


        //Clickable Website
        this.txtWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileGuiController.navigateToLink();
            }
        });

    }



    //Course Click
    @Override
    public void onCourseClick(int position) {
        this.profileGuiController.showCourseDetails(position);
    }




    public void setCoursesAdapter(List<BeanCourse> beanCourses) {
        this.coursesAdapter.setbCourses(beanCourses);
        this.rvCourses.setAdapter(this.getCoursesAdapter());
    }

    public CoursesAdapter getCoursesAdapter() {
        return coursesAdapter;
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





    public void setBtnExam() {
        this.btnExam.show();
    }


    public void setBtnAdd() {
        this.btnAdd.setRotation(45);
    }


    public void setTxtExam() {
        this.txtExam.setVisibility(View.VISIBLE);
    }


    public void setBtnHomework() {
        this.btnHomework.show();
    }


    public void unSetTxtExam() {
        this.txtExam.setVisibility(View.GONE);
    }


    public void setTxtHomework() {
        this.txtHomework.setVisibility(View.VISIBLE);
    }

    public void unSetBtnAdd() {
        this.btnAdd.setRotation(0);
    }

    public void setBtnCommunication() {
        this.btnCommunication.show();
    }


    public void unsSetBtnExam() {
        this.btnExam.hide();
    }

    public void setTxtCommunication() {
        this.txtCommunication.setVisibility(View.VISIBLE);
    }

    public void unSetTxtHomework() {
        this.txtHomework.setVisibility(View.GONE);
    }


    public void unSetTxtCommunication() {
        this.txtCommunication.setVisibility(View.GONE);
    }

    public void unSetBtnHomework() {
        this.btnHomework.hide();
    }


    public void unSetBtnCommunication() {
        this.btnCommunication.hide();
    }


}