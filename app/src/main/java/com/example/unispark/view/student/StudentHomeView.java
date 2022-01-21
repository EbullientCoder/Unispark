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

import com.example.unispark.controller.guicontroller.student.ManageStudentHomeGuiController;
import com.example.unispark.viewadapter.communications.ProfCommunicationsAdapter;
import com.example.unispark.viewadapter.communications.UniCommunicationsAdapter;
import com.example.unispark.bean.BeanHomework;
import com.example.unispark.bean.communications.BeanProfessorCommunication;
import com.example.unispark.bean.communications.BeanUniCommunication;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.viewadapter.HomeworksAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentHomeView extends AppCompatActivity
        implements UniCommunicationsAdapter.OnUniComClickListener,
        ProfCommunicationsAdapter.OnProfComClickListener,
        HomeworksAdapter.OnHomeworkBtnClickListener{


        //Menu
        ImageButton menuButton;
        //Bottom Menu Elements
        BottomNavigationView bottomNavigationView;
        //University Communications
        RecyclerView rvUniCommunications;
        UniCommunicationsAdapter uniCommunicationsAdapter;
        //Professor Communications
        RecyclerView rvProfCommunications;
        ProfCommunicationsAdapter profCommunicationsAdapter;
        //Homeworks
        RecyclerView rvHomeworks;
        HomeworksAdapter homeworksAdapter;

        //Get Intent Extras
        Bundle extras;

        //Bean
        BeanLoggedStudent bStudent;
        List<BeanUniCommunication> beanUniCommunications;
        List<BeanProfessorCommunication> beanProfessorCommunications;
        List<BeanHomework> beanHomeworks;

        //Gui controller
        private ManageStudentHomeGuiController homeGuiController;


        //Constructor
        @Override
        protected void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_student_home);

                this.homeGuiController = new ManageStudentHomeGuiController();

                //Getting User Bean
                extras = getIntent().getExtras();
                bStudent = (BeanLoggedStudent) extras.getSerializable("UserObject");

                //Menu
                menuButton = findViewById(R.id.btn_menu);
                menuButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                                Toast.makeText(getApplicationContext(), "Work In Progress", Toast.LENGTH_SHORT).show();
                        }
                });

                //Bottom Navigation Menu
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

                                //Menu Gui Controller
                                homeGuiController.selectNextView(bStudent, getApplicationContext(), item.getItemId());
                                overridePendingTransition(0,0);
                                return true;
                        }
                });


                //Uni Communications
                rvUniCommunications = findViewById(R.id.rv_uni_communications);
                //Gui Controller
                beanUniCommunications = homeGuiController.getUniCommunications(bStudent);
                uniCommunicationsAdapter = new UniCommunicationsAdapter(beanUniCommunications, this);
                rvUniCommunications.setAdapter(uniCommunicationsAdapter);



                //Prof Communications
                rvProfCommunications =  findViewById(R.id.rv_prof_communications);
                //Gui Controller
                beanProfessorCommunications = homeGuiController.getProfessorsCommunications(bStudent);
                profCommunicationsAdapter = new ProfCommunicationsAdapter(beanProfessorCommunications, this);
                rvProfCommunications.setAdapter(profCommunicationsAdapter);



                //Homeworks
                rvHomeworks = findViewById(R.id.rv_homeworks);
                //Gui Controller
                beanHomeworks = homeGuiController.getHomeworks(bStudent);
                homeworksAdapter = new HomeworksAdapter(beanHomeworks, this, "STUDENT");
                rvHomeworks.setAdapter(homeworksAdapter);
        }


        //On UniversityCommunications Click
        @Override
        public void onUniClick(int position) {

                homeGuiController.showDetailsCommunication(getApplicationContext(), beanUniCommunications.get(position));
        }


        //On ProfessorCommunications Click
        @Override
        public void onProfClick(int position) {
                homeGuiController.showDetailsCommunication(getApplicationContext(), beanProfessorCommunications.get(position));
        }

        //On Homework Click
        @Override
        public void onBtnClick(int position) {
                homeGuiController.showHomeworkDetails(getApplicationContext(), beanHomeworks.get(position));
        }


}