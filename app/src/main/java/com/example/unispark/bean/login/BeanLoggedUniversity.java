package com.example.unispark.bean.login;

import java.util.List;

public class BeanLoggedUniversity extends BeanLoggedUser{

    //Attributes
    String name;
    String streetAddress;
    private List<String> faculties;


    //Methods
    //Constructor
    public BeanLoggedUniversity(String name, int profilePicture, String streetAddress, List<String> faculties) {
        super(profilePicture);
        this.name = name;
        this.streetAddress = streetAddress;
        this.faculties = faculties;
    }


    //Getter
    public String getName() {
        return name;
    }
    public String getStreetAddress() {
        return streetAddress;
    }
    public List<String> getFaculties() {
        return faculties;
    }


    //Setter
    public void setName(String name) {
        this.name = name;
    }
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }
    public void setFaculties(List<String> faculties) {
        this.faculties = faculties;
    }


}
