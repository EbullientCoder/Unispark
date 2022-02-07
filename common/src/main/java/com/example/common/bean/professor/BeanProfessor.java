package com.example.common.bean.professor;

import com.example.common.bean.login.BeanLoggedUser;

public class BeanProfessor extends BeanLoggedUser {

    //Attributes
    private String firstName;
    private String lastName;
    private String faculty;
    private String website;
    private int id;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getWebsite() {
        return website;
    }

    public int getId() {
        return id;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setId(int id) {
        this.id = id;
    }
}
