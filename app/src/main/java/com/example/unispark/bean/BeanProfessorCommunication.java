package com.example.unispark.bean;

import java.io.Serializable;

public class BeanProfessorCommunication implements Serializable {

    //Attributes
    private int profilePhoto;
    private String shortCourseName;
    private String fullName;
    private String professorName;
    private String date;
    private String type;
    private String communication;


    //Methods
    //Constructor
    public BeanProfessorCommunication(int profilePhoto, String shortCourseName, String fullName, String professorName, String date, String type, String communication) {
        this.profilePhoto = profilePhoto;
        this.shortCourseName = shortCourseName;
        this.fullName = fullName;
        this.professorName = professorName;
        this.date = date;
        this.type = type;
        this.communication = communication;
    }


    //Getter
    public int getProfilePhoto() {
        return profilePhoto;
    }
    public String getShortCourseName() {
        return shortCourseName;
    }
    public String getFullName() {
        return fullName;
    }
    public String getProfessorName() {
        return professorName;
    }
    public String getDate() {
        return date;
    }
    public String getType() {
        return type;
    }
    public String getCommunication() {
        return communication;
    }


    //Setter
    public void setProfilePhoto(int profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    public void setShortCourseName(String shortCourseName) {
        this.shortCourseName = shortCourseName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setType(String type) {
        this.type = type;
    }
    public void setCommunication(String communication) {
        this.communication = communication;
    }
}
