package com.example.unispark.controller.guicontroller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.LinksAdapter;
import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanLink;
import com.example.unispark.bean.BeanProfessorDetails;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.professor.ShowFacultyProfessors;
import com.example.unispark.controller.applicationcontroller.links.AddLink;
import com.example.unispark.controller.applicationcontroller.links.DeleteLink;
import com.example.unispark.controller.applicationcontroller.links.ShowLinks;
import com.example.unispark.controller.guicontroller.menu.RightButtonMenu;
import com.example.unispark.controller.guicontroller.details.DetailsProfessorGUIController;
import com.example.unispark.controller.guicontroller.menu.BottomNavigationMenuGuiController;
import com.example.unispark.adapter.ProfessorsAdapter;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.LinkAlreadyExists;
import com.example.unispark.model.CourseModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class StudentLinksGUIController extends AppCompatActivity
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
    List<BeanProfessorDetails> beanProfessorDetailsList;
    //Link
    EditText txtAddLinkName;
    EditText txtAddLink;
    //Button Add Link
    ImageButton addButton;
    //Links
    RecyclerView rvLinks;
    LinksAdapter linkAdapter;
    List<BeanLink> beanLinkList;
    //Get Intent Extras
    Bundle extras;
    BeanLoggedStudent bStudent;


    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_links);

        //Getting User Object
        extras = getIntent().getExtras();
        bStudent = (BeanLoggedStudent) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightButtonMenu rightMenuAppController = new RightButtonMenu();

                //Serve un modo per determinare il giorno e la notte.
                rightMenuAppController.dayColor(getApplicationContext());
                rightMenuAppController.nightColor(getApplicationContext());
            }
        });



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
                //Menu Applicative Controller
                BottomNavigationMenuGuiController bottomMenuAppController = new BottomNavigationMenuGuiController();

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity(bStudent, getApplicationContext(), item.getItemId());
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });



        //Professors
        rvProfessors = findViewById(R.id.rv_professors);
        //Application Controller
        ShowFacultyProfessors facultyProfessorsAppController = new ShowFacultyProfessors();
        beanProfessorDetailsList = facultyProfessorsAppController.setFacultyProfessors(bStudent);
        professorsAdapter = new ProfessorsAdapter(beanProfessorDetailsList, this);
        rvProfessors.setAdapter(professorsAdapter);



        //StudentLinksGUIController
        rvLinks = findViewById(R.id.rv_links);
        //Application Controller
        ShowLinks linksAppController = new ShowLinks();
        beanLinkList = linksAppController.showLinks(bStudent);
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
                if(linkName.length() != 0 && link.length() != 0){

                    BeanLink newLink = new BeanLink(linkName, link);

                    //Application Controller
                    AddLink addLinksAppController = new AddLink();
                    try {
                        addLinksAppController.addLink(bStudent, newLink);
                        beanLinkList.add(newLink);
                        linkAdapter.notifyItemInserted(beanLinkList.size());
                    } catch (LinkAlreadyExists | GenericException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
                else Toast.makeText(getApplicationContext(), "EMPTY LINK", Toast.LENGTH_SHORT).show();
            }
        });
    }




    //Clickable Items Methods
    @Override
    public void onProfessorClick(int position) {
        Intent intent = new Intent(this, DetailsProfessorGUIController.class);
        intent.putExtra("Professor", beanProfessorDetailsList.get(position));

        startActivity(intent);
    }

    //On Link Click
    @Override
    public void onLinkClick(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    //On DeleteLink Click
    @Override
    public void onDelBtnClick(int position) {

        //Application Controller: Delete Link
        DeleteLink deleteLinkAppController = new DeleteLink();

        try {
            deleteLinkAppController.deleteLink(beanLinkList.get(position));
            //Removing link from the List
            beanLinkList.remove(position);
            linkAdapter.notifyItemRemoved(position);
        } catch (GenericException e) {
            e.printStackTrace();
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }


}