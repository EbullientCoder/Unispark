package com.example.unispark.controller.guicontroller;

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
import com.example.unispark.controller.applicationcontroller.Login;
import com.example.unispark.controller.professor.ProfessorHome;
import com.example.unispark.controller.student.Home;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.database.dao.StudentDAO;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.UserModel;
import com.google.android.material.textfield.TextInputLayout;


public class LoginGUIController extends AppCompatActivity {

    //User Selector
    String[] users = {"STUDENT","PROFESSOR","UNIVERSITY"};
    private AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String userSelection;
    //Database: Email & Password
    private TextInputLayout txtEmail;
    String email;
    private TextInputLayout txtPassword;
    String password;
    //LoginGUIController Button
    private Button loginButton;


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_login);

        //Database
        txtEmail = findViewById(R.id.txt_input_email);
        txtPassword = findViewById(R.id.txt_input_password);

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

        loginButton = (Button) findViewById(R.id.btn_homework_detail_submit);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginMethod();
            }
        });
    }


    //LoginGUIController Method
    void loginMethod(){
        UserModel user = new UserModel();
        Login loginAppController = new Login(getApplicationContext());
        Intent intent;

        email = txtEmail.getEditText().getText().toString();
        password = txtPassword.getEditText().getText().toString();


        if (!userSelection.equals("") && !email.equals("") && !password.equals("")) {
            user.setEmail(email);
            user.setPassword(password);

            switch (userSelection) {
                case "STUDENT":
                    intent = loginAppController.studendLogin(user);
                    if(intent != null) startActivity(intent);

                    break;

                case "PROFESSOR":
                    intent = loginAppController.professorLogin(user);
                    if(intent != null) startActivity(intent);

                    break;

                case "UNIVERSITY":
                    intent = loginAppController.universityLogin(user);
                    if(intent != null) startActivity(intent);

                    break;
            }

        }
        else Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
    }
}