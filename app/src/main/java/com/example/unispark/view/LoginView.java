package com.example.unispark.view;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;


import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.R;
import com.example.unispark.controller.guicontroller.LoginGuiController;
import com.google.android.material.textfield.TextInputLayout;

public class LoginView extends AppCompatActivity {


        //User Selector
        private String[] users = {"STUDENT","PROFESSOR","UNIVERSITY"};
        private AutoCompleteTextView autoCompleteTxt;
        private ArrayAdapter<String> adapterItems;
        private String userSelection;
        //Email & Password
        private TextInputLayout txtEmail;
        private TextInputLayout txtPassword;
        //Login Button
        private Button loginButton;


        private LoginGuiController loginGuiController;

        //Methods
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_generic_login);

            this.loginGuiController = new LoginGuiController();

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
                    String email = txtEmail.getEditText().getText().toString();
                    String password = txtPassword.getEditText().getText().toString();

                    loginGuiController.login(getApplicationContext(), userSelection, email, password);
                }
            });
        }



}
