package com.example.unispark.bean.professor;

import com.example.unispark.bean.login.BeanLoggedUser;

public class BeanLoggedProfessor extends BeanLoggedUser{

    //Attributes
    private String firstName;
    private String lastName;
    private int id;
    private String faculty;
    private String website;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getWebsite() {
        return website;
    }



    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setWebsite(String website) {
        this.website = website;
    }


}
