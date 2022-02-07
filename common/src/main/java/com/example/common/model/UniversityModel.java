package com.example.common.model;


import java.util.List;

public class UniversityModel extends UserModel {
    //Attributes
    String name;
    String streetAddress;
    private List<String> faculties;


    //Methods
    //Constructor
    public UniversityModel(String name, String email, int profilePicture, String streetAddress, List<String> faculties) {
        super(email, profilePicture);
        this.name = name;
        this.streetAddress = streetAddress;
        this.faculties = faculties;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public List<String> getFaculties() {
        return faculties;
    }

    public void setFaculties(List<String> faculties) {
        this.faculties = faculties;
    }
}
