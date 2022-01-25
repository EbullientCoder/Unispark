package com.example.unispark.view.student;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unispark.R;

import com.example.unispark.Session;
import com.example.unispark.controller.guicontroller.student.ManageStudentHomeGuiController;
import com.example.unispark.viewadapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.viewadapter.HomeworksAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentHomeView extends AppCompatActivity
        implements UniCommunicationsAdapter.OnUniComClickListener,
        ProfCommunicationsAdapter.OnProfComClickListener,
        HomeworksAdapter.OnHomeworkBtnClickListener{

        //Menu
        private ImageButton menuButton;
        //Bottom Menu Elements
        private BottomNavigationView bottomNavigationView;
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
                this.menuButton = findViewById(R.id.btn_menu);
                this.menuButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Work In Progress", Toast.LENGTH_SHORT).show();
                        }
                });

                //Bottom Navigation Menu
                this.bottomNavigationView = findViewById(R.id.bottomMenuView);
                //Remove Menu View's background
                this.bottomNavigationView.setBackground(null);
                //Remove Menu View's icons tint
                this.bottomNavigationView.setItemIconTintList(null);
                //Set StudentHomeGUIController button
                this.bottomNavigationView.setSelectedItemId(R.id.home);
                //Click Listener
                this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
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