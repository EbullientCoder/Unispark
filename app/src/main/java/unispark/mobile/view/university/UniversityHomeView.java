package unispark.mobile.view.university;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.unispark.R;
import unispark.mobile.Session;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.mobile.guicontroller.university.ManageUniHomeGuiController;
import unispark.mobile.viewadapter.LessonAdapter;
import unispark.mobile.viewadapter.communications.UniCommunicationsAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class UniversityHomeView extends AppCompatActivity implements
        UniCommunicationsAdapter.OnUniComClickListener,
        LessonAdapter.OnDelBtnClickListener {


    //Floating Button
    private FloatingActionButton btnAdd;
    private FloatingActionButton btnCommunication;
    private TextView txtCommunication;
    private FloatingActionButton btnSchedule;
    private TextView txtSchedule;
    //Communications
    private RecyclerView rvUniCommunications;
    private UniCommunicationsAdapter uniCommunicationsAdapter;

    //Schedules
    private TextView txtScheduleTitle;
    private RecyclerView rvSchedules;
    private LessonAdapter lessonAdapter;





    //Gui Controller
    private ManageUniHomeGuiController uniHomeGuiController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_university_home);

        this.uniHomeGuiController = new ManageUniHomeGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.uniCommunicationsAdapter = new UniCommunicationsAdapter(this);
        this.lessonAdapter = new LessonAdapter(this, "UNIVERSITY");


        //Menu
        ImageButton menuButton;
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });



        this.btnAdd = findViewById(R.id.btn_uni_add);
        this.btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        this.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uniHomeGuiController.expandButton();
            }
        });


        //Button: Add Communication
        this.txtCommunication = findViewById(R.id.txt_add_uni_communication);
        this.txtCommunication.setVisibility(View.GONE);

        this.btnCommunication = findViewById(R.id.btn_add_uni_communication);
        this.btnCommunication.setImageTintList(null);
        this.btnCommunication.setVisibility(View.GONE);
        this.btnCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uniHomeGuiController.showAddCommunication();
            }
        });

        //Button: Add StudentScheduleGUIController
        this.txtSchedule = findViewById(R.id.txt_add_schedule);
        this.txtSchedule.setVisibility(View.GONE);

        this.btnSchedule = findViewById(R.id.btn_add_schedule);
        this.btnSchedule.setImageTintList(null);
        this.btnSchedule.setVisibility(View.GONE);
        this.btnSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uniHomeGuiController.showAddSchedule();
            }
        });



        //Assigned Communications
        this.rvUniCommunications = findViewById(R.id.rv_assigned_communications);
        //Gui Controller
        this.uniHomeGuiController.showCommunications();

        //Schedules
        this.txtScheduleTitle = findViewById(R.id.txt_schedule_day);
        this.rvSchedules = findViewById(R.id.rv_schedules);

        //Gui Controller
        this.uniHomeGuiController.showSchedule();

        //Button: Next Course
        ImageButton btnNextCourse;
        btnNextCourse = findViewById(R.id.btn_course_next);
        btnNextCourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                uniHomeGuiController.correctIndex();
                uniHomeGuiController.showSchedule();
            }
        });

    }



    //On UniversityCommunications Click
    @Override
    public void onUniClick(int position) {

        this.uniHomeGuiController.showDetailsCommunication(position);
    }


    //On DeleteLesson Button Click
    @Override
    public void onDelBtnClick(int position) {

        this.uniHomeGuiController.deleteLesson(position);
    }




    public UniCommunicationsAdapter getUniCommunicationsAdapter() {
        return uniCommunicationsAdapter;
    }



    public void setBtnAdd() {
        this.btnAdd.setRotation(45);
    }

    public void setBtnCommunication() {
        this.btnCommunication.show();
    }

    public void setTxtCommunication() {
        this.txtCommunication.setVisibility(View.VISIBLE);
    }

    public void setBtnSchedule() {
        this.btnSchedule.show();
    }



    public void setUniCommunicationsAdapter(List<BeanUniCommunication> beanUniCommunications) {
        this.uniCommunicationsAdapter.setBeanUniCommunicationList(beanUniCommunications);
        this.rvUniCommunications.setAdapter(this.getUniCommunicationsAdapter());
    }

    public void setTxtSchedule() {
        this.txtSchedule.setVisibility(View.VISIBLE);
    }

    public void setLessonAdapter(List<BeanLesson> beanLessons) {
        this.lessonAdapter.setLessonItem(beanLessons);
        this.rvSchedules.setAdapter(this.getLessonAdapter());
    }

    public LessonAdapter getLessonAdapter() {
        return lessonAdapter;
    }


    public void unSetBtnAdd() {
        this.btnAdd.setRotation(0);
    }

    public void unSetBtnCommunication() {
        this.btnCommunication.hide();
    }

    public void unSetTxtCommunication() {
        this.txtCommunication.setVisibility(View.GONE);
    }

    public void unSetBtnSchedule() {
        this.btnSchedule.hide();
    }

    public void unSetTxtSchedule() {
        this.txtSchedule.setVisibility(View.GONE);
    }


    public void setTxtScheduleTitle(String content) {
        this.txtScheduleTitle.setText(content);
    }

    public void notifyDataChanged(int position){
        this.lessonAdapter.notifyItemRemoved(position);
        this.rvSchedules.setAdapter(this.getLessonAdapter());
    }


    public void setMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}