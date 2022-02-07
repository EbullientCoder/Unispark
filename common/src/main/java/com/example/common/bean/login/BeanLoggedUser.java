package com.example.common.bean.login;

import java.io.Serializable;

public class BeanLoggedUser implements Serializable {

    private int profilePicture;


    public int getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }
}
