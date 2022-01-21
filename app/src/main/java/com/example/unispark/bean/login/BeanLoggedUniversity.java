package com.example.unispark.bean.login;

import java.util.List;

public class BeanLoggedUniversity extends BeanLoggedUser{

    //Attributes
    private String name;
    private String streetAddress;
    private List<String> faculties;



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
