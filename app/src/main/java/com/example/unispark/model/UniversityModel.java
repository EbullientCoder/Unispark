package com.example.unispark.model;


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
