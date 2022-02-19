package unispark.view.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import android.widget.TextView;

import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;
import unispark.engeneeringclasses.bean.exams.BeanExam;
import unispark.controller.guicontroller.professor.ManageProfessorExamsGuiController;
import unispark.view.viewadapter.exams.ExamAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ProfessorExamsView extends AppCompatActivity
        implements ExamAdapter.OnViewExamClickListener{


    //Exams Page
    private RecyclerView rvExams;
    private ExamAdapter examAdapter;
    //Floating Button
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnExam;
    private TextView txtExam;
    private FloatingActionButton btnHomework;
    private TextView txtHomework;
    private FloatingActionButton btnCommunication;
    private TextView txtCommunication;



    //Gui Controller
    private ManageProfessorExamsGuiController examsGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_exams);

        this.examsGuiController = new ManageProfessorExamsGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.examAdapter = new ExamAdapter(this);


        //Bottom Navigation Menu
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.professor_bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.professor_exams);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                examsGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Exam List
        this.rvExams = findViewById(R.id.rv_professor_exams);


        //Gui Controller
        this.examsGuiController.showExams();


        this.btnAdd = findViewById(R.id.btn_add);
        this.btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                examsGuiController.expandButton();
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
                examsGuiController.showAddExam();
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
                examsGuiController.showAddHomework();
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
                examsGuiController.showAddCommunication();
            }
        });
    }



    //On ViewExam Button Click
    @Override
    public void onViewBtnClick(int position) {
        this.examsGuiController.showVerbalizeExam(position);
    }






    public void setExamAdapter(List<BeanExam> beanExamTypes) {
        this.examAdapter.setbExams(beanExamTypes);
        this.rvExams.setAdapter(this.getExamAdapter());

    }

    public ExamAdapter getExamAdapter() {
        return examAdapter;
    }

    public void setBtnExam() {
        this.btnExam.show();
    }
    public void unsSetBtnExam() {
        this.btnExam.hide();
    }

    public void setBtnAdd() {
        this.btnAdd.setRotation(45);
    }
    public void unSetBtnAdd() {
        this.btnAdd.setRotation(0);
    }

    public void setTxtExam() {
        this.txtExam.setVisibility(View.VISIBLE);
    }
    public void unSetTxtExam() {
        this.txtExam.setVisibility(View.GONE);
    }

    public void setBtnHomework() {
        this.btnHomework.show();
    }
    public void unSetBtnHomework() {
        this.btnHomework.hide();
    }

    public void setTxtHomework() {
        this.txtHomework.setVisibility(View.VISIBLE);
    }
    public void unSetTxtHomework() {
        this.txtHomework.setVisibility(View.GONE);
    }

    public void setBtnCommunication() {
        this.btnCommunication.show();
    }
    public void unSetBtnCommunication() {
        this.btnCommunication.hide();
    }

    public void setTxtCommunication() {
        this.txtCommunication.setVisibility(View.VISIBLE);
    }
    public void unSetTxtCommunication() {
        this.txtCommunication.setVisibility(View.GONE);
    }

    public void setExamType(int type){
        examAdapter.setExamType(type);
    }
}