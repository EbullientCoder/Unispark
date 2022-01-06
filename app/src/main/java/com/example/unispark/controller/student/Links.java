package com.example.unispark.controller.student;

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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.LinksAdapter;
import com.example.unispark.controller.applicationcontroller.menu.RightButtonMenu;
import com.example.unispark.controller.details.DetailsProfessor;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.example.unispark.adapter.ProfessorsAdapter;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LinkModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Links extends AppCompatActivity
        implements ProfessorsAdapter.OnProfessorClickListener,
        LinksAdapter.OnLinkClickListener,
        LinksAdapter.OnDelBtnClickListener {

    //Attributes
    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Professors
    RecyclerView rvProfessors;
    List<ProfessorModel> professorsItem;
    //Links
    //Image
    ImageView zeroLinks;
    //Button Add
    ImageButton addButton;
    //Links
    RecyclerView rvLinks;
    List<LinkModel> linksExamItem;
    LinksAdapter linkAdapter;
    EditText txtAddLinkName;
    EditText txtAddLink;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;


    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_links);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");



        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RightButtonMenu rightMenuAppController = new RightButtonMenu(getApplicationContext());

                //Serve un modo per determinare il giorno e la notte.
                rightMenuAppController.dayColor();
                rightMenuAppController.nightColor();
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
                BottomNavigationMenu bottomMenuAppController = new BottomNavigationMenu(student, getApplicationContext(), item.getItemId());

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity();
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });



        //Professors
        rvProfessors = findViewById(R.id.rv_professors);
        professorsItem = ProfessorDAO.getFacultyProfessors(student.getFaculty());

        if (professorsItem == null){
            professorsItem = new ArrayList<>();
        }

        rvProfessors.setAdapter(new ProfessorsAdapter(professorsItem, this));


        //Image Links
        zeroLinks = findViewById(R.id.img_zero_items);

        //Links
        rvLinks = findViewById(R.id.rv_links);
        linksExamItem = StudentLinksDAO.getStudentLinks(student.getId());

        if (linksExamItem == null){
            linksExamItem = new ArrayList<>();
        }

        linkAdapter = new LinksAdapter(linksExamItem, this, this);
        rvLinks.setAdapter(linkAdapter);



        //Add Button
        txtAddLinkName = findViewById(R.id.txt_input_link_name);
        txtAddLink = findViewById(R.id.txt_input_link);
        addButton = findViewById(R.id.btn_link_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String linkName = txtAddLinkName.getText().toString();
                String link = txtAddLink.getText().toString();
                if(linkName.length() != 0 && link.length() != 0){
                    LinkModel link0 = new LinkModel(linkName, link);

                    //Adding the link into the DB
                    boolean isAdded = StudentLinksDAO.addStudentLink(link0, student.getId());
                    if(isAdded){
                        linksExamItem.add(link0);
                        linkAdapter.notifyItemInserted(linksExamItem.size());
                    }
                    else Toast.makeText(getApplicationContext(), "Link already exists", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "EMPTY LINK", Toast.LENGTH_SHORT).show();
            }
        });
    }


    //Clickable Items Methods
    @Override
    public void onProfessorClick(int profImageID, String firstname, String lastname, String website, List<CourseModel> courses) {
        Intent intent = new Intent(this, DetailsProfessor.class);
        intent.putExtra("ProfessorImage", profImageID);
        intent.putExtra("Firstname", firstname);
        intent.putExtra("Lastname", lastname);
        intent.putExtra("Website", website);
        intent.putExtra("Courses", (Serializable) courses);

        startActivity(intent);
    }

    @Override
    public void onLinkClick(String url) {
        Uri uri = Uri.parse(url);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }

    @Override
    public void onDelBtnClick(int position) {
        List<LinkModel> studentLinks = StudentLinksDAO.getStudentLinks(student.getId());

        //Remove the Connection inside the DB
        assert studentLinks != null;
        StudentLinksDAO.removeLink(studentLinks.get(position).getLinkName());

        //Removing link from the List
        linksExamItem.remove(position);
        linkAdapter.notifyItemRemoved(position);
    }


}