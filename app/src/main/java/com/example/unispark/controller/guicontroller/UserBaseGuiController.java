package com.example.unispark.controller.guicontroller;

import com.example.unispark.Session;

public class UserBaseGuiController {

    protected Session session;

    protected UserBaseGuiController(Session session) {
        this.session = session;
    }

    public Session getSession(){
        return session;
    }
}
