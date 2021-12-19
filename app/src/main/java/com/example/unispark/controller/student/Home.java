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
import com.example.unispark.database.DataBaseHelper;
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

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity
        implements HomeworksAdapter.OnHomeworkBtnClickListener,
        ProfCommunicationsAdapter.OnProfComClickListener,
        UniCommunicationsAdapter.OnUniComClickListener {

    //Attributes
    //Database
    DataBaseHelper dataBaseHomework;
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
        //student = (StudentModel) extras.getSerializable("UserObject");
        student = null;


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


        //Prof Communications
        rvProfCommunications =  findViewById(R.id.rv_prof_communications);
        profCommunicationsItem = new ArrayList<>();

        ProfessorCommunicationModel pCom1 = new ProfessorCommunicationModel(R.drawable.courses_falessi, "ISPW", "DAVIDE FALESSI", "10/12/2021", "Exam Result", COMMUNICATION);
        ProfessorCommunicationModel pCom2 = new ProfessorCommunicationModel(R.drawable.courses_martinelli, "ARL", "FRANCESCO MARTINELLI", "20/03/2021", "Exam Result", COMMUNICATION);
        ProfessorCommunicationModel pCom3 = new ProfessorCommunicationModel(R.drawable.courses_carnevale, "CA","Daniele Carnevale", "07/04/2020", "Generic", COMMUNICATION);
        ProfessorCommunicationModel pCom4 = new ProfessorCommunicationModel(R.drawable.courses_lo_presti, "CE", "Francesco Lo Presti", "16/04/2020", "HomeworkModel", COMMUNICATION);

        profCommunicationsItem.add(pCom1);
        profCommunicationsItem.add(pCom2);
        profCommunicationsItem.add(pCom3);
        profCommunicationsItem.add(pCom4);

        rvProfCommunications.setAdapter(new ProfCommunicationsAdapter(profCommunicationsItem, this));


        //Homeworks
        rvHomeworks = findViewById(R.id.rv_homeworks);

        dataBaseHomework = new DataBaseHelper(getApplicationContext());
        homeworksItem = dataBaseHomework.getHomework();

        rvHomeworks.setAdapter(new HomeworksAdapter(homeworksItem, this, "STUDENT"));
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
        intent.putExtra("profID", id);
        intent.putExtra("Home", "StudentHome");
        startActivity(intent);
    }
    //Professor Communication Click
    @Override
    public void onProfClick(int professorProfile, String shortName, String profName, String date, String type, String communication) {
        Intent intent = new Intent(this, DetailsProfCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("ProfessorImage", professorProfile);
        intent.putExtra("ShortName", shortName);
        intent.putExtra("ProfessorName", profName);
        intent.putExtra("Date", date);
        intent.putExtra("Type", type);
        intent.putExtra("Communication", communication);
        startActivity(intent);
    }
    //University Communication Click
    @Override
    public void onUniClick(int comImage, String title, String date, String communication) {
        Intent intent = new Intent(this, DetailsUniCommunication.class);
        //Pass Items to the new Activity
        intent.putExtra("CommunicationImage", comImage);
        intent.putExtra("Title", title);
        intent.putExtra("Date", date);
        intent.putExtra("Communication", communication);
        intent.putExtra("Home", "StudentHome");
        startActivity(intent);
    }
}