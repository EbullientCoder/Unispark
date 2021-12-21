package com.example.unispark.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import com.example.unispark.R;
import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.controller.student.Home;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.database.dao.StudentDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {

    //Attributes
    //Database
    //DataBaseHelper dataBaseUser;
    //User Selector
    String[] users = {"STUDENT","PROFESSOR","UNIVERSITY"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String userSelection;
    //Database: Email & Password
    TextInputLayout txtEmail;
    String email;
    TextInputLayout txtPassword;
    String password;
    //Login Button
    Button loginButton;


    //Methods
    //Constructor
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_login);

        //Database
        txtEmail = findViewById(R.id.txt_input_email);
        txtPassword = findViewById(R.id.txt_input_password);
        //dataBaseUser = new DataBaseHelper(getApplicationContext());

        //DropDown Selector
        userSelection = "";
        autoCompleteTxt = findViewById(R.id.select_user);
        adapterItems = new ArrayAdapter<>(this,R.layout.item_container_item, users);
        autoCompleteTxt.setAdapter(adapterItems);
        autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userSelection = (String)parent.getItemAtPosition(position);
            }
        });

        //Click on Login Button
        loginButton = (Button) findViewById(R.id.btn_homework_detail_submit);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = txtEmail.getEditText().getText().toString();
                password = txtPassword.getEditText().getText().toString();

                if (!userSelection.equals("") && !email.equals("") && !password.equals("")) {
                    Intent intent;
                    switch (userSelection) {
                        case "STUDENT":
                            StudentModel student = StudentDAO.selectStudent(email, password);
                            intent = new Intent(getApplicationContext(), Home.class);
                            intent.putExtra("UserObject", student);
                            startActivity(intent);
                            break;
                        case "PROFESSOR":
                            ProfessorModel professor = ProfessorDAO.selectProfessor(email, password);
                            intent = new Intent(getApplicationContext(), ProfessorHome.class);
                            intent.putExtra("UserObject", professor);
                            startActivity(intent);
                            break;
                        //case "UNIVERSITY": startActivity(new Intent(getApplicationContext(), UniversityHome.class));
                        //break;

                    }
                    //overridePendingTransition(0, 0);
                }
            }

        });
    }
}