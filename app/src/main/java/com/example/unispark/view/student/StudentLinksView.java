package com.example.unispark.view.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.Session;
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
    private ImageButton menuButton;
    //Bottom Menu Elements
    private BottomNavigationView bottomNavigationView;
    //Professors
    private RecyclerView rvProfessors;
    private ProfessorsAdapter professorsAdapter;
    //Link
    private EditText txtAddLinkName;
    private EditText txtAddLink;
    //Button Add Link
    private ImageButton addButton;
    //Links
    private RecyclerView rvLinks;
    private LinksAdapter linkAdapter;



    //Gui controller
    private ManageLinksGuiController linksGuiController;



    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_links);

        this.linksGuiController = new ManageLinksGuiController((Session) getIntent().getExtras().getSerializable("session"), this);
        this.professorsAdapter = new ProfessorsAdapter(this);
        this.linkAdapter = new LinksAdapter(this, this);


        //Bottom Navigation Menu
        this.bottomNavigationView = findViewById(R.id.bottomMenuView);
        //Remove Menu View's background
        this.bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        this.bottomNavigationView.setItemIconTintList(null);
        //Set StudentHomeGUIController button
        this.bottomNavigationView.setSelectedItemId(R.id.links);
        //Click Listener
        this.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                //Gui controller
                linksGuiController.selectNextView(item.getItemId());
                overridePendingTransition(0,0);
                return true;
            }
        });



        //Professors
        this.rvProfessors = findViewById(R.id.rv_professors);
        //Gui Controller
        this.linksGuiController.showProfessorDetails();


        this.rvLinks = findViewById(R.id.rv_links);
        //Gui Controller
        this.linksGuiController.showLinks();


        //Add Link Button
        this.txtAddLinkName = findViewById(R.id.txt_input_link_name);
        this.txtAddLink = findViewById(R.id.txt_input_link);
        this.addButton = findViewById(R.id.btn_link_add);
        this.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkName = txtAddLinkName.getText().toString();
                String link = "https://" + txtAddLink.getText().toString();

                //Gui controller
                linksGuiController.addLink(linkName, link);
            }
        });
    }



    //Clickable Items Methods
    @Override
    public void onProfessorClick(int position) {
        this.linksGuiController.showProfessorDetails(position);
    }

    //On Link Click
    @Override
    public void onLinkClick(String url) {
        this.linksGuiController.goToLink(url);
    }

    //On DeleteLink Click
    @Override
    public void onDelBtnClick(int position) {
        //Gui Controller
        this.linksGuiController.removeLink(position);
    }






    public void setProfessorsAdapter(List<BeanProfessorDetails> beanProfessorDetails) {
        this.professorsAdapter.setBeanLoggedProfessorList(beanProfessorDetails);
        this.rvProfessors.setAdapter(this.professorsAdapter);
    }

    public void setLinkAdapter(List<BeanLink> beanLinks) {
        this.linkAdapter.setBeanLinkList(beanLinks);
        this.rvLinks.setAdapter(this.linkAdapter);
    }

    public void notifyDataChanged(int position, boolean isRemoved){
        if(isRemoved) this.linkAdapter.notifyItemRemoved(position);
        else this.linkAdapter.notifyItemInserted(position);


    }


    public void setErrorMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}