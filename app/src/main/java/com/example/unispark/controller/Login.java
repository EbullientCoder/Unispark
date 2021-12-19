package com.example.unispark.controller;

import static com.example.unispark.database.Password.getHash;

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
import com.example.unispark.controller.student.Links;
import com.example.unispark.database.DataBaseHelper;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.google.android.material.textfield.TextInputLayout;


public class Login extends AppCompatActivity {

    //Attributes
    //User Selector
    String[] items =  {"STUDENT","PROFESSOR","UNIVERSITY"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String userSelection;
    //Database: Email & Password
    TextInputLayout txtEmail;
    String email;
    TextInputLayout txtPassword;
    String password;
    //Database
    DataBaseHelper dataBaseUser;
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
        dataBaseUser = new DataBaseHelper(getApplicationContext());

        //DropDown Selector
        userSelection = "";
        autoCompleteTxt = findViewById(R.id.select_user);
        adapterItems = new ArrayAdapter<>(this,R.layout.item_container_user, items);
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

                if(!userSelection.equals("") && !email.equals("") && !password.equals("")){
                    if(dataBaseUser.login(userSelection, email, password)){
                        switch (userSelection){
                            case "STUDENT":
                                //StudentModel student =
                                startActivity(new Intent(getApplicationContext(), Home.class));
                            break;
                            case "PROFESSOR":
                                ProfessorModel professor = dataBaseUser.getProfessor(email);

                                Intent intent = new Intent(getApplicationContext(), ProfessorHome.class);
                                intent.putExtra("UserObject", professor);
                                startActivity(intent);
                            break;
                            //case "UNIVERSITY": startActivity(new Intent(getApplicationContext(), UniversityHome.class));
                            //break;
                        }
                        //PUSH
                    }
                    else Toast.makeText(getApplicationContext(), "WRONG CREDENTIALS", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getApplicationContext(), "SELECT USER", Toast.LENGTH_SHORT).show();

                overridePendingTransition(0, 0);
            }
        });
    }
}