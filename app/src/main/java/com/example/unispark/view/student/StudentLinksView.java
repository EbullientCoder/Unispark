package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.student.ManageLinksGuiController;
import com.example.unispark.viewadapter.LinksAdapter;
import com.example.unispark.bean.BeanLink;
import com.example.unispark.bean.professor.BeanProfessorDetails;
import com.example.unispark.bean.student.BeanLoggedStudent;

import com.example.unispark.viewadapter.ProfessorsAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class StudentLinksView extends AppCompatActivity
        implements ProfessorsAdapter.OnProfessorClickListener,
        LinksAdapter.OnLinkClickListener,
        LinksAdapter.OnDelBtnClickListener {


    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Professors
    RecyclerView rvProfessors;
    ProfessorsAdapter professorsAdapter;
    //Link
    EditText txtAddLinkName;
    EditText txtAddLink;
    //Button Add Link
    ImageButton addButton;
    //Links
    RecyclerView rvLinks;
    LinksAdapter linkAdapter;
    //Get Intent Extras
    Bundle extras;

    //Bean
    BeanLoggedStudent bStudent;
    List<BeanLink> beanLinkList;
    List<BeanProfessorDetails> beanProfessorDetailsList;

    //Gui controller
    private ManageLinksGuiController linksGuiController;




    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_links);

        this.linksGuiController = new ManageLinksGuiController();



        //Getting User Object
        extras = getIntent().getExtras();
        bStudent = (BeanLoggedStudent) extras.getSerializable("UserObject");



        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        bottomNavigationView.setSelectedItemId(R.id.links);
        //Click Listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                //Menu Gui controller
                linksGuiController.selectNextView(bStudent, getApplicationContext(), item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Professors
        rvProfessors = findViewById(R.id.rv_professors);
        //Gui Controller
        beanProfessorDetailsList = linksGuiController.showProfessorDetails(bStudent);
        professorsAdapter = new ProfessorsAdapter(beanProfessorDetailsList, this);
        rvProfessors.setAdapter(professorsAdapter);



        //StudentLinksGUIController
        rvLinks = findViewById(R.id.rv_links);
        //Gui Controller
        beanLinkList = linksGuiController.showLinks(bStudent);
        linkAdapter = new LinksAdapter(beanLinkList, this, this);
        rvLinks.setAdapter(linkAdapter);



        //Add Link Button
        txtAddLinkName = findViewById(R.id.txt_input_link_name);
        txtAddLink = findViewById(R.id.txt_input_link);
        addButton = findViewById(R.id.btn_link_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkName = txtAddLinkName.getText().toString();
                String link = "https://" + txtAddLink.getText().toString();

                //Gui controller
                linksGuiController.addLink(getApplicationContext(), linkName, link, bStudent, beanLinkList, linkAdapter);
            }
        });
    }




    //Clickable Items Methods
    @Override
    public void onProfessorClick(int position) {
        linksGuiController.showProfessorDetails(getApplicationContext(), beanProfessorDetailsList.get(position));
    }

    //On Link Click
    @Override
    public void onLinkClick(String url) {
        linksGuiController.goToLink(getApplicationContext(), url);
    }

    //On DeleteLink Click
    @Override
    public void onDelBtnClick(int position) {

        //Gui Controller
        linksGuiController.removeLink(getApplicationContext(), beanLinkList, position, linkAdapter);
    }


}