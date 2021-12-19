package com.example.unispark.controller.professor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsHomework;
import com.example.unispark.controller.details.DetailsUniCommunication;
import com.example.unispark.controller.professor.fragment.AddCommunicationFragment;
import com.example.unispark.controller.professor.fragment.AddExamFragment;
import com.example.unispark.controller.professor.fragment.AddHomeworkFragment;
import com.example.unispark.database.DataBaseHelper;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ProfessorHome extends AppCompatActivity implements
        HomeworksAdapter.OnHomeworkBtnClickListener,
        UniCommunicationsAdapter.OnUniComClickListener{

    //Database
    DataBaseHelper dataBaseHomework;
    //Menu
    ImageButton menuButton;
    //Floating Button
    FloatingActionButton btnAdd;
    FloatingActionButton btnExam;
    TextView txtExam;
    FloatingActionButton btnHomework;
    TextView txtHomework;
    FloatingActionButton btnCommunication;
    TextView txtCommunication;
    Boolean isOpen;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Communications
    RecyclerView rvUniCommunications;
    List<UniversityCommunicationModel> uniCommunicationsItem;
    //Homeworks
    RecyclerView rvHomeworks;
    List<HomeworkModel> homeworksItem;

    private static final String INSTRUCTIONS = "Halo è una serie di videogiochi sparatutto in prima persona di genere fantascienza militare creata da Bungie Studios e sviluppata dalla stessa Bungie (dal primo capitolo a Halo: Reach) e 343 Industries (dal quarto capitolo a Halo Infinite) e pubblicata da Xbox Game Studios (precedentemente Microsoft Game Studios). La serie conta sei capitoli principali, sei spin-off, un remake e una raccolta; al 2021 ha venduto oltre ottanta milioni di copie in tutto il mondo, incassando circa sei miliardi di dollari.";
    private static final String COMMUNICATION = "AVVISO PLC: Lunedi 13 dicembre esercitazione pratica (in Aula 4) con i PLC Siemens S7-1200. Si prevede di terminare l'esercitazione entro le ore 18. Se possibile portare un PC con installato il software TIA Portal e il simulatore PLCSIM, preferibilmente la versione 13 SP2 (vedere indicazioni in questo link: http://control.disp.uniroma2.it/fmartine/AutMan/TIAportal.html ), per la quale sono disponibili alcune licenze che verranno consegnate per il tempo necessario all'esercitazione. Scaricare sul proprio PC anche la dispensa sulla parte di programmazione dei PLC ( http://control.disp.uniroma2.it/fmartine/AutMan/parteIntroPiuPLC.pdf ) nonché questo file zip: http://control.disp.uniroma2.it/fmartine/AutMan/PLC/esempiPLC.zip , contenente alcuni esempi di programmazione dei PLC (gli stessi descritti nella dispensa). Si consiglia di mettere a punto il software prima della lezione verificando se si riesce a seguire i passi presentati nella 19esima videolezione dello scorso anno (in particolare nel video AM19a e nei primi 13 minuti del video AM19b), disponibile in fondo al link http://control.disp.uniroma2.it/fmartine/AutMan/videoLez.htm .";
    private static final String DATE = "10/02/2021";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_professor_home);

        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });


        //Button: Add Homework - Communication
        isOpen = false;

        btnAdd = findViewById(R.id.btn_add);
        btnAdd.setImageTintList(ColorStateList.valueOf(Color.parseColor("#272b2f")));
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                expandButton();
            }
        });
        //Button: Add Exam
        txtExam = findViewById(R.id.txt_add_exam);
        txtExam.setVisibility(View.GONE);

        btnExam = findViewById(R.id.btn_add_exam);
        btnExam.setImageTintList(null);
        btnExam.setVisibility(View.GONE);
        btnExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddExamFragment fragment= new AddExamFragment();
                fragment.show(getSupportFragmentManager(), "AddExam");
            }
        });
        //Button: Add Homework
        txtHomework = findViewById(R.id.txt_add_homework);
        txtHomework.setVisibility(View.GONE);

        btnHomework = findViewById(R.id.btn_add_homework);
        btnHomework.setImageTintList(null);
        btnHomework.setVisibility(View.GONE);
        btnHomework.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddHomeworkFragment fragment= new AddHomeworkFragment();
                fragment.show(getSupportFragmentManager(), "AddHomework");
            }
        });
        //Button: Add Communication
        txtCommunication = findViewById(R.id.txt_add_communication);
        txtCommunication.setVisibility(View.GONE);

        btnCommunication = findViewById(R.id.btn_add_communication);
        btnCommunication.setImageTintList(null);
        btnCommunication.setVisibility(View.GONE);
        btnCommunication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddCommunicationFragment fragment= new AddCommunicationFragment();
                fragment.show(getSupportFragmentManager(), "AddCommunication");
            }
        });


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        MenuVisualSetting(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                //MenuFunctionalSetting(item.getItemId());
                startActivity(MenuFunctionalSetting(item.getItemId()));
                return true;
            }
        });



        //------------------------------------------------------------------------------------------

        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        uniCommunicationsItem = new ArrayList<>();

        UniversityCommunicationModel uCom1 = new UniversityCommunicationModel(R.drawable.rettorato, "Nuovo Edificio", DATE, COMMUNICATION);
        UniversityCommunicationModel uCom2 = new UniversityCommunicationModel(R.drawable.formula_uno, "Garage", DATE, COMMUNICATION);
        UniversityCommunicationModel uCom3 = new UniversityCommunicationModel(R.drawable.schedule, "Orari Scolastici", DATE, COMMUNICATION);
        UniversityCommunicationModel uCom4 = new UniversityCommunicationModel(R.drawable.green_pass, "Green Pass", DATE, COMMUNICATION);
        UniversityCommunicationModel uCom5 = new UniversityCommunicationModel(R.drawable.drone, "Gara Droni", DATE, COMMUNICATION);
        UniversityCommunicationModel uCom6 = new UniversityCommunicationModel(R.drawable.blank_img, "PROVA", DATE, COMMUNICATION);

        uniCommunicationsItem.add(uCom1);
        uniCommunicationsItem.add(uCom2);
        uniCommunicationsItem.add(uCom3);
        uniCommunicationsItem.add(uCom4);
        uniCommunicationsItem.add(uCom5);
        uniCommunicationsItem.add(uCom6);
        uniCommunicationsItem.add(uCom6);
        uniCommunicationsItem.add(uCom6);

        rvUniCommunications.setAdapter(new UniCommunicationsAdapter(uniCommunicationsItem, this));


        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);

        dataBaseHomework = new DataBaseHelper(getApplicationContext());

        ProfessorModel professorModel;

        Bundle extras = getIntent().getExtras();
        professorModel = (ProfessorModel) extras.getSerializable("professor");

        homeworksItem = dataBaseHomework.getAssignedHomework(professorModel);

        rvHomeworks.setAdapter(new HomeworksAdapter(homeworksItem, this, "PROFESSOR"));
    }



    //Open Button
    public void expandButton(){
        if(!isOpen){
            //Show Buttons
            btnExam.show();
            btnCommunication.show();
            btnHomework.show();

            //Expand Floating Button
            txtExam.setVisibility(View.VISIBLE);
            txtCommunication.setVisibility(View.VISIBLE);
            txtHomework.setVisibility(View.VISIBLE);

            //Rotate
            btnAdd.setRotation(45);

            isOpen = true;
        }
        else{
            //Hide Buttons
            btnExam.hide();
            btnCommunication.hide();
            btnHomework.hide();

            //Expand Floating Button
            txtExam.setVisibility(View.GONE);
            txtCommunication.setVisibility(View.GONE);
            txtHomework.setVisibility(View.GONE);

            //Rotate
            btnAdd.setRotation(0);

            isOpen = false;
        }
    }


    //Homework Button Click
    @Override
    public void onBtnClick(String shortName, String title, String course, String expiration, String instructions, String points, int id) {
        Intent intent = new Intent(this, DetailsHomework.class);
        //Pass Items to the new Activity
        intent.putExtra("ShortName", shortName);
        intent.putExtra("Course", course);
        intent.putExtra("Title", title);
        intent.putExtra("Expiration", expiration);
        intent.putExtra("Instructions", instructions);
        intent.putExtra("Points", points);
        intent.putExtra("profID",id);
        intent.putExtra("Home", "ProfessorHome");
        startActivity(intent);
    }
    //Professor Communication Click
    @Override
    public void onUniClick(int comImage, String title, String date, String communication) {
        Intent intent = new Intent(this, DetailsUniCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("CommunicationImage", comImage);
        intent.putExtra("Title", title);
        intent.putExtra("Date", date);
        intent.putExtra("Communication", communication);
        intent.putExtra("Home", "ProfessorHome");
        startActivity(intent);
    }

    //BottomNavigationMenu
    public void MenuVisualSetting(int id){
        //Remove Menu View's background
        bottomNavigationView.setBackground(null);
        //Remove Menu View's icons tint
        bottomNavigationView.setItemIconTintList(null);
        //Set Home button
        bottomNavigationView.setSelectedItemId(id);
    }
    public Intent MenuFunctionalSetting(int id){
        switch (id){
            case R.id.profile: return new Intent(getApplicationContext(), ProfessorHome.class);
            case R.id.exams: return new Intent(getApplicationContext(), ProfessorHome.class);
            default: return new Intent(getApplicationContext(), ProfessorHome.class);
        }
    }
}