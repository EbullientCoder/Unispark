package com.example.unispark.provaDB;


import java.util.List;

public class UniversityModel extends UserModel {
    //Attributes
    private String name;
    private int image;
    private String address;

    //Methods
    //Constructor
    public UniversityModel(String email, String password, int image, String name, String address) {
        super(email, password);
        this.image = image;
        this.name = name;
        this.address = address;
    }


    //Getter
    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getImage() {
        return image;
    }

    //Setter
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
