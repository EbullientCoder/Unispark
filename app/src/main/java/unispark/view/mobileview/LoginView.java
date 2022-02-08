package unispark.view.mobileview;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.unispark.R;
import unispark.engeneeringclasses.others.Session;
import unispark.controller.guicontroller.mobilecontroller.LoginGuiController;
import com.google.android.material.textfield.TextInputLayout;

public class LoginView extends AppCompatActivity {

    //User Selector
    private AutoCompleteTextView autoCompleteTxt;
    private ArrayAdapter adapterItems;
    //Email & Password
    private TextInputLayout txtEmail;
    private TextInputLayout txtPassword;
    //LoginAppController Button



    //Gui Controller
    private LoginGuiController loginGuiController;


    String userSelection = "";


    //Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generic_login);


        Session session = (Session) getIntent().getExtras().getSerializable("session");
        loginGuiController = new LoginGuiController(session, this);
        this.adapterItems = new ArrayAdapter(this,R.layout.item_container_item);

        //Email and password
        this.txtEmail = findViewById(R.id.txt_input_email);
        this.txtPassword = findViewById(R.id.txt_input_password);

        //DropDown Selector

        this.autoCompleteTxt = findViewById(R.id.select_user);
        this.loginGuiController.showUserSelector();

        this.autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userSelection = (String)parent.getItemAtPosition(position);
            }
        });

        Button loginButton;

        loginButton = (Button) findViewById(R.id.btn_homework_detail_submit);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = txtEmail.getEditText().getText().toString();
                String password = txtPassword.getEditText().getText().toString();

                loginGuiController.login(userSelection, email, password);
            }
        });
    }



    public void setErrorMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void setAdapterItems(String[] users) {
        this.adapterItems.addAll(users);
        this.autoCompleteTxt.setAdapter(this.getAdapterItems());
    }



    public ArrayAdapter<String> getAdapterItems() {
        return adapterItems;
    }



}