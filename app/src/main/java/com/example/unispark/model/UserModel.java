package com.example.unispark.model;

import java.io.Serializable;

public class UserModel implements Serializable {
    //Attributes
    private String email;
    private String password;

    //Methods
    //Constructor
    public UserModel(){};

    public UserModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //Getter
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //Setter
    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
