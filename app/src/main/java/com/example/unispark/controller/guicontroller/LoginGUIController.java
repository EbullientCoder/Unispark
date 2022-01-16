package com.example.unispark.controller.guicontroller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.R;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.bean.login.BeanLoggedUniversity;
import com.example.unispark.bean.login.BeanUser;
import com.example.unispark.controller.applicationcontroller.Login;
import com.example.unispark.controller.guicontroller.professor.ProfessorHomeGUIController;
import com.example.unispark.controller.guicontroller.student.StudentHomeGUIController;
import com.example.unispark.controller.guicontroller.university.UniversityHomeGUIController;
import com.example.unispark.exceptions.WrongUsernameOrPasswordException;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.UniversityModel;
import com.google.android.material.textfield.TextInputLayout;


public class LoginGUIController extends AppCompatActivity {

    //User Selector
    String[] users = {"STUDENT","PROFESSOR","UNIVERSITY"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;
    String userSelection;
    //Database: Email & Password
    TextInputLayout txtEmail;
    TextInputLayout txtPassword;
    //LoginGUIController Button
    Button loginButton;


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
        BeanUser user;
        Login loginAppController = new Login();
        Intent intent;

        String email = txtEmail.getEditText().getText().toString();
        String password = txtPassword.getEditText().getText().toString();

        //Checking User Credentials
        if (!userSelection.equals("") && !email.equals("") && !password.equals("")) {
            user = new BeanUser(email, password);

            switch (userSelection) {
                case "STUDENT":
                    //Get Student Model
                    BeanLoggedStudent student = null;
                    try {
                        student = loginAppController.studentLogin(user);
                        intent = new Intent(getApplicationContext(), StudentHomeGUIController.class);
                        intent.putExtra("UserObject", student);
                        startActivity(intent);
                    } catch (WrongUsernameOrPasswordException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "PROFESSOR":
                    //Get Professor Model
                    BeanLoggedProfessor professor = null;
                    try {
                        professor = loginAppController.professorLogin(user);
                        intent = new Intent(getApplicationContext(), ProfessorHomeGUIController.class);
                        intent.putExtra("UserObject", professor);
                        startActivity(intent);
                    } catch (WrongUsernameOrPasswordException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;

                case "UNIVERSITY":
                    //Get University Model
                    BeanLoggedUniversity university = null;
                    try {
                        university = loginAppController.universityLogin(user);
                        intent = new Intent(getApplicationContext(), UniversityHomeGUIController.class);
                        intent.putExtra("UserObject", university);
                        startActivity(intent);
                    } catch (WrongUsernameOrPasswordException e) {
                        e.printStackTrace();
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
        else Toast.makeText(getApplicationContext(), "All fields are required", Toast.LENGTH_SHORT).show();
    }
}