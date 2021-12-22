package com.example.unispark.model.communications;

public class ProfessorCommunicationModel {

    //Attributes
    private int profilePhoto;
    private String shortCourseName;
    private String professorName;
    private String date;
    private String type;
    private String communication;


    //Methods
    //Constructor
    public ProfessorCommunicationModel(int profilePhoto, String shortCourseName, String professorName, String date, String type, String communication) {
        this.profilePhoto = profilePhoto;
        this.shortCourseName = shortCourseName;
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

