package unispark.mobile.view.student;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unispark.R;

import unispark.mobile.Session;
import unispark.mobile.guicontroller.student.ManageStudentHomeGuiController;
import unispark.mobile.viewadapter.communications.ProfCommunicationsAdapter;
import unispark.mobile.viewadapter.communications.UniCommunicationsAdapter;
import unispark.engeneeringclasses.bean.BeanHomework;
import unispark.engeneeringclasses.bean.communications.BeanProfessorCommunication;
import unispark.engeneeringclasses.bean.communications.BeanUniCommunication;
import unispark.mobile.viewadapter.HomeworksAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentHomeView extends AppCompatActivity
        implements UniCommunicationsAdapter.OnUniComClickListener,
        ProfCommunicationsAdapter.OnProfComClickListener,
        HomeworksAdapter.OnHomeworkBtnClickListener{


        //University Communications
        private RecyclerView rvUniCommunications;
        private UniCommunicationsAdapter uniCommunicationsAdapter;
        //Professor Communications
        private RecyclerView rvProfCommunications;
        private ProfCommunicationsAdapter profCommunicationsAdapter;
        //Homeworks
        private RecyclerView rvHomeworks;
        private HomeworksAdapter homeworksAdapter;



        //Gui controller
        private ManageStudentHomeGuiController homeGuiController;


        //Constructor
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_student_home);

                Session session = (Session) getIntent().getExtras().getSerializable("session");

                this.homeGuiController = new ManageStudentHomeGuiController(session, this);
                //View Adapters
                this.uniCommunicationsAdapter = new UniCommunicationsAdapter(this);
                this.profCommunicationsAdapter = new ProfCommunicationsAdapter(this);
                this.homeworksAdapter = new HomeworksAdapter(this, "STUDENT");


                //Menu
                ImageButton menuButton;
                menuButton = findViewById(R.id.btn_menu);
                menuButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Work In Progress", Toast.LENGTH_SHORT).show();
                        }
                });

                //Bottom Navigation Menu
                BottomNavigationView bottomNavigationView;
                bottomNavigationView = findViewById(R.id.bottomMenuView);
                //Remove Menu View's background
                bottomNavigationView.setBackground(null);
                //Remove Menu View's icons tint
                bottomNavigationView.setItemIconTintList(null);
                //Set StudentHomeGUIController button
                bottomNavigationView.setSelectedItemId(R.id.home);
                //Click Listener
                bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                                //Gui Controller
                                homeGuiController.selectNextView(item.getItemId());
                                overridePendingTransition(0,0);
                                return true;
                        }
                });


                //Uni Communications
                this.rvUniCommunications = findViewById(R.id.rv_uni_communications);
                //Gui Controller(Show Communications)
                this.homeGuiController.showUniCommunications();


                //Prof Communications
                this.rvProfCommunications =  findViewById(R.id.rv_prof_communications);
                //Gui Controller(show Professors communications)
                this.homeGuiController.showProfessorCommunications();



                //Homeworks
                this.rvHomeworks = findViewById(R.id.rv_homeworks);
                //Gui Controller(Show homework)
                this.homeGuiController.showHomeworks();
        }


        //On UniversityCommunications Click
        @Override
        public void onUniClick(int position) {

               this.homeGuiController.showDetailsUniCommunication(position);
        }


        //On ProfessorCommunications Click
        @Override
        public void onProfClick(int position) {

                this.homeGuiController.showDetailsProfCommunication(position);
        }

        //On Homework Click
        @Override
        public void onBtnClick(int position) {
                this.homeGuiController.showHomeworkDetails(position);
        }




        //Getters and Setters

        public void setUniCommunicationsAdapter(List<BeanUniCommunication> beanUniCommunications) {
                this.uniCommunicationsAdapter.setBeanUniCommunicationList(beanUniCommunications);
                this.rvUniCommunications.setAdapter(this.uniCommunicationsAdapter);
        }



        public void setProfCommunicationsAdapter(List<BeanProfessorCommunication> beanProfessorCommunications) {
                this.profCommunicationsAdapter.setBeanProfCommunicationList(beanProfessorCommunications);
                this.rvProfCommunications.setAdapter(this.profCommunicationsAdapter);
        }



        public void setHomeworksAdapter(List<BeanHomework> beanHomeworks) {
                this.homeworksAdapter.setBeanHomeworkList(beanHomeworks);
                this.rvHomeworks.setAdapter(this.homeworksAdapter);
        }


}