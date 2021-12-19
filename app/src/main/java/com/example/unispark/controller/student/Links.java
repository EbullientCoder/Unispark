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

import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.R;
import com.example.unispark.adapter.LinksAdapter;
import com.example.unispark.controller.details.DetailsProfessor;
import com.example.unispark.menu.BottomNavigationMenu;
import com.example.unispark.adapter.ProfessorsAdapter;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LinkModel;
import com.example.unispark.model.ProfessorModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Links extends AppCompatActivity
        implements ProfessorsAdapter.OnProfessorClickListener,
        LinksAdapter.OnLinkClickListener,
        LinksAdapter.OnDelBtnClickListener {

    //Attributes
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
    List<ExamItem> linksExamItem;
    LinksAdapter linkAdapter;
    EditText txtAddLinkName;
    EditText txtAddLink;


    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_links);

        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.links);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId()));
                overridePendingTransition(0, 0);
                return true;
            }
        });


        //Professors
        rvProfessors = findViewById(R.id.rv_professors);
        professorsItem = new ArrayList<>();

        ProfessorModel prof1 = new ProfessorModel("codiocannn", "ciaofrocio",  -1, "Davide", "Falessi", "https://www.google.com", R.drawable.courses_falessi, createCourses());
        ProfessorModel prof2 = new ProfessorModel("codiocannn", "ciaofrocio",  -1, "Francesco", "Martinelli", "https://www.google.com", R.drawable.courses_martinelli, createCourses());
        ProfessorModel prof3 = new ProfessorModel("codiocannn", "ciaofrocio",  -1, "Francesco", "Lo Presti", "https://www.google.com", R.drawable.courses_lo_presti, createCourses());
        ProfessorModel prof4 = new ProfessorModel("codiocannn", "ciaofrocio",  -1, "Daniele", "Carnevale", "https://www.google.com", R.drawable.courses_carnevale, createCourses());

        professorsItem.add(prof1);
        professorsItem.add(prof2);
        professorsItem.add(prof3);
        professorsItem.add(prof4);
        professorsItem.add(prof1);
        professorsItem.add(prof1);

        rvProfessors.setAdapter(new ProfessorsAdapter(professorsItem, this));


        //Image Links
        zeroLinks = findViewById(R.id.img_zero_items);

        //Links
        rvLinks = findViewById(R.id.rv_links);
        linksExamItem = new ArrayList<>();
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
                    linksExamItem.add(new ExamItem(0, link0));

                    linkAdapter.notifyItemInserted(linksExamItem.size());
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
        linksExamItem.remove(position);
        linkAdapter.notifyItemRemoved(position);
    }


    //Create Course Models Method
    public static List<CourseModel> createCourses(){
        List<CourseModel> list = new ArrayList<>();

        CourseModel course = new CourseModel();
        CourseModel course1 = new CourseModel();
        CourseModel course2 = new CourseModel();

        course.setFullName("CONTROLLI AUTOMATICI");
        course.setLink("Link CA");
        course1.setFullName("GEOMETRIA");
        course1.setLink("Link GEO");
        course2.setFullName("//");
        course2.setLink("//");

        list.add(course);
        list.add(course1);
        list.add(course2);

        return list;
    }
}