package unispark.mobile.view.student;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.unispark.R;
import unispark.mobile.Session;
import unispark.mobile.guicontroller.student.ShowScheduleGuiController;
import unispark.engeneeringclasses.bean.BeanLesson;
import unispark.mobile.viewadapter.LessonAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.List;

public class StudentScheduleView extends AppCompatActivity{


    //Calendar
    private TextView txtDay;
    private TextView txtDate;
    //Lessons
    private RecyclerView rvLessons;
    private LessonAdapter lessonAdapter;



    //Gui Controller
    private ShowScheduleGuiController scheduleGuiController;


    //Constructor
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_schedule);

        this.scheduleGuiController = new ShowScheduleGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.lessonAdapter = new LessonAdapter("STUDENT");


        //Bottom Navigation Menu
        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.schedule);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui Controller
                scheduleGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });


        //Calendar

        this.txtDay = findViewById(R.id.txt_calendar_day);
        this.txtDate = findViewById(R.id.txt_calendar_date);

        //Lessons
        this.rvLessons = findViewById(R.id.rv_lessons);
        //Gui Controller
        this.scheduleGuiController.showSchedule();

    }





    public void setLessonAdapter(List<BeanLesson> beanLessons) {
        this.lessonAdapter.setLessonItem(beanLessons);
        this.rvLessons.setAdapter(this.lessonAdapter);
    }

    public void setTxtDay(String content) {
        this.txtDay.setText(content);
    }

    public void setTxtDate(String content) {
        this.txtDate.setText(content);
    }
}