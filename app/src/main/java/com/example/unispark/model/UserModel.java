package com.example.unispark.model;


public abstract class UserModel {
    //Attributes
    private String email;
    private int profilePicture;


    //Methods
    //Constructor


    protected UserModel(String email, int profilePicture) {
        this.email = email;
        this.profilePicture = profilePicture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }
}
