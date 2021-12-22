package com.example.unispark.model.communications;

public class UniversityCommunicationModel {

    //Attributes
    private int background;
    private String title;
    private String date;
    private String communication;
    private String faculty;


    //Methods
    //Constructor
    public UniversityCommunicationModel(int background, String title, String date, String communication, String faculty) {
        this.background = background;
        this.title = title;
        this.date = date;
        this.communication = communication;
        this.faculty = faculty;
    }

    //Getter
    public int getBackground() {
        return background;
    }
    public String getTitle() {
        return title;
    }
    public String getDate() {
        return date;
    }
    public String getCommunication() {
        return communication;
    }
    public String getFaculty() {
        return faculty;
    }

    //Setter
    public void setBackground(int background) {
        this.background = background;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCommunication(String communication) {
        this.communication = communication;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}

