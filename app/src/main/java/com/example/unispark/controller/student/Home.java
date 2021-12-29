package com.example.unispark.controller.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.adapter.communications.ProfCommunicationsAdapter;

import com.example.unispark.database.dao.CommunicationsDAO;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;
import com.example.unispark.adapter.communications.UniCommunicationsAdapter;
import com.example.unispark.controller.details.DetailsHomework;
import com.example.unispark.controller.details.DetailsProfCommunication;
import com.example.unispark.controller.details.DetailsUniCommunication;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.adapter.HomeworksAdapter;
import com.example.unispark.menu.BottomNavigationMenu;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements HomeworksAdapter.OnHomeworkBtnClickListener,
        ProfCommunicationsAdapter.OnProfComClickListener,
        UniCommunicationsAdapter.OnUniComClickListener {

    //Attributes
    //Database
    //DataBaseHelper dataBaseHomework;
    //Menu
    ImageButton menuButton;
    //Bottom Menu Elements
    BottomNavigationView bottomNavigationView;
    //Communications
    RecyclerView rvUniCommunications;
    RecyclerView rvProfCommunications;
    List<UniversityCommunicationModel> uniCommunicationsItem;
    List<ProfessorCommunicationModel> profCommunicationsItem;
    //Homeworks
    RecyclerView rvHomeworks;
    List<HomeworkModel> homeworksItem;
    //Get Intent Extras
    Bundle extras;
    StudentModel student;

    private static final String INSTRUCTIONS = "Halo è una serie di videogiochi sparatutto in prima persona di genere fantascienza militare creata da Bungie Studios e sviluppata dalla stessa Bungie (dal primo capitolo a Halo: Reach) e 343 Industries (dal quarto capitolo a Halo Infinite) e pubblicata da Xbox Game Studios (precedentemente Microsoft Game Studios). La serie conta sei capitoli principali, sei spin-off, un remake e una raccolta; al 2021 ha venduto oltre ottanta milioni di copie in tutto il mondo, incassando circa sei miliardi di dollari.";
    private static final String COMMUNICATION = "AVVISO PLC: Lunedi 13 dicembre esercitazione pratica (in Aula 4) con i PLC Siemens S7-1200. Si prevede di terminare l'esercitazione entro le ore 18. Se possibile portare un PC con installato il software TIA Portal e il simulatore PLCSIM, preferibilmente la versione 13 SP2 (vedere indicazioni in questo link: http://control.disp.uniroma2.it/fmartine/AutMan/TIAportal.html ), per la quale sono disponibili alcune licenze che verranno consegnate per il tempo necessario all'esercitazione. Scaricare sul proprio PC anche la dispensa sulla parte di programmazione dei PLC ( http://control.disp.uniroma2.it/fmartine/AutMan/parteIntroPiuPLC.pdf ) nonché questo file zip: http://control.disp.uniroma2.it/fmartine/AutMan/PLC/esempiPLC.zip , contenente alcuni esempi di programmazione dei PLC (gli stessi descritti nella dispensa). Si consiglia di mettere a punto il software prima della lezione verificando se si riesce a seguire i passi presentati nella 19esima videolezione dello scorso anno (in particolare nel video AM19a e nei primi 13 minuti del video AM19b), disponibile in fondo al link http://control.disp.uniroma2.it/fmartine/AutMan/videoLez.htm .";
    private static final String DATE = "10/02/2021";

    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_home);

        //Getting User Object
        extras = getIntent().getExtras();
        student = (StudentModel) extras.getSerializable("UserObject");

        //Menu
        menuButton = findViewById(R.id.btn_menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Work in Progress", Toast.LENGTH_SHORT).show();
            }
        });


        //Bottom Navigation Menu
        bottomNavigationView = findViewById(R.id.bottomMenuView);
        BottomNavigationMenu.visualSetting(bottomNavigationView, R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                startActivity(BottomNavigationMenu.functionalSetting(getApplicationContext(), item.getItemId(), student));
                overridePendingTransition(0, 0);
                return true;
            }
        });




        //------------------------------------------------------------------------------------------

        //Uni Communications
        rvUniCommunications = findViewById(R.id.rv_uni_communications);
        uniCommunicationsItem = CommunicationsDAO.getUniversityCommunications(student.getFaculty());

        rvUniCommunications.setAdapter(new UniCommunicationsAdapter(uniCommunicationsItem, this));


        //Prof Communications
        rvProfCommunications =  findViewById(R.id.rv_prof_communications);
        //Create a list of strings of short name courses (Create a method in the applicative controller)
        List<String> courseShortnames = new ArrayList<>();
        List<String> courseFullNames = new ArrayList<>();
        if(student.getCourses() == null){
            profCommunicationsItem = null;
        }
        else{
            for(int i = 0; i < student.getCourses().size(); i++)
            {
                courseShortnames.add(student.getCourses().get(i).getShortName());
                courseFullNames.add(student.getCourses().get(i).getFullName());
            }
            profCommunicationsItem = CommunicationsDAO.getAllCoursesCommunications(courseShortnames, courseFullNames);
        }

        rvProfCommunications.setAdapter(new ProfCommunicationsAdapter(profCommunicationsItem, this));


        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);
        homeworksItem = HomeworkDAO.getStudentHomework(student.getId());

        rvHomeworks.setAdapter(new HomeworksAdapter(homeworksItem, this, "STUDENT"));
    }


    //Homework Button Click
    @Override
    public void onBtnClick(int position) {
        Intent intent = new Intent(this, DetailsHomework.class);
        //Pass Items to the new Activity
        intent.putExtra("Homework", homeworksItem.get(position));
        intent.putExtra("Home", "StudentHome");
        startActivity(intent);
    }
    //Professor Communication Click
    @Override
    public void onProfClick(int position) {
        Intent intent = new Intent(this, DetailsProfCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", profCommunicationsItem.get(position));
        startActivity(intent);
    }
    //University Communication Click
    @Override
    public void onUniClick(int position) {
        Intent intent = new Intent(this, DetailsUniCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("Communication", uniCommunicationsItem.get(position));
        startActivity(intent);
    }
}