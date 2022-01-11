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
import android.widget.ImageView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.LinksAdapter;
import com.example.unispark.controller.applicationcontroller.ShowFacultyProfessors;
import com.example.unispark.controller.applicationcontroller.links.AddLinks;
import com.example.unispark.controller.applicationcontroller.links.ShowLinks;
import com.example.unispark.controller.applicationcontroller.menu.RightButtonMenu;
import com.example.unispark.controller.details.DetailsProfessor;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.controller.applicationcontroller.menu.BottomNavigationMenu;
import com.example.unispark.adapter.ProfessorsAdapter;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LinkModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.List;

public class StudentLinksGUIController extends AppCompatActivity
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
    ProfessorsAdapter professorsAdapter;
    List<ProfessorModel> professorsItem;
    //Links
    RecyclerView rvLinks;
    LinksAdapter linkAdapter;
    List<LinkModel> linksItem;
    //Button Add
    ImageButton addButton;
    //Link
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
                BottomNavigationMenu bottomMenuAppController = new BottomNavigationMenu();

                //Start Activity
                Intent intent = bottomMenuAppController.nextActivity(student, getApplicationContext(), item.getItemId());
                startActivity(intent);
                overridePendingTransition(0,0);

                return true;
            }
        });



        //Professors
        rvProfessors = findViewById(R.id.rv_professors);
        //Application Controller
        ShowFacultyProfessors facultyProfessorsAppController = new ShowFacultyProfessors();
        professorsItem = facultyProfessorsAppController.setFacultyProfessors(student);
        professorsAdapter = new ProfessorsAdapter(professorsItem, this);
        rvProfessors.setAdapter(professorsAdapter);



        //StudentLinksGUIController
        rvLinks = findViewById(R.id.rv_links);
        //Application Controller
        ShowLinks linksAppController = new ShowLinks();
        linksItem = linksAppController.setLinks(student);
        linkAdapter = new LinksAdapter(linksItem, this, this);
        rvLinks.setAdapter(linkAdapter);



        //Add Link Button
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

                    //Application Controller
                    AddLinks addLinksAppController = new AddLinks();
                    boolean isAdded = addLinksAppController.addLink(student, link0);
                    if(isAdded){
                        linksItem.add(link0);
                        linkAdapter.notifyItemInserted(linksItem.size());
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
        linksItem.remove(position);
        linkAdapter.notifyItemRemoved(position);
    }


}