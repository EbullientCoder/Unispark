package com.example.unispark.model;

import java.io.Serializable;

public abstract class UserModel implements Serializable {
    //Attributes
    private String email;
    private int profilePicture;


    //Methods
    //Constructor


    public UserModel(String email, int profilePicture) {
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public int getProfilePicture() {
        return profilePicture;
    }



    public void setEmail(String email) {
        this.email = email;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }
}
