package com.example.unispark.model.communications;

public class UniversityCommunicationModel {

    //Attributes
    private int background;
    private String title;
    private String date;
    private String communication;


    //Methods
    //Constructor
    public UniversityCommunicationModel(int background, String title, String date, String communication) {
        this.background = background;
        this.title = title;
        this.date = date;
        this.communication = communication;
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
}

