package com.example.unispark;

import com.example.common.bean.login.BeanLoggedUser;

import java.io.Serializable;

public class Session implements Serializable {

    private BeanLoggedUser user;


    public Session(){
        this.user = null;
    }

    public BeanLoggedUser getUser() {
        return user;
    }

    public void setUser(BeanLoggedUser user) {
        this.user = user;
    }
}
