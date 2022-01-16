package com.example.unispark.bean.login;

import java.io.Serializable;

public class BeanLoggedUser implements Serializable {

    int profilePicture;

    public BeanLoggedUser(int profilePicture) {
        this.profilePicture = profilePicture;
    }



    public int getProfilePicture() {
        return profilePicture;
    }




    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }
}
