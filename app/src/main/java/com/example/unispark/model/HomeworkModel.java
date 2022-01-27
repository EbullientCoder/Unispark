package com.example.unispark.model;


public class HomeworkModel {
    //Attributes
    private String shortName;
    private String fullName;
    private String title;
    private String expiration;
    private String instructions;
    private String points;
    private int trackProfessor;


    //Methods
    //Constructor
    public HomeworkModel(String shortName, String fullName, String title, String expiration, String instructions, String points, int trackProfessor) {
        this.shortName = shortName;
        this.fullName = fullName;
        this.title = title;
        this.expiration = expiration;
        this.instructions = instructions;
        this.points = points;
        this.trackProfessor = trackProfessor;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public int getTrackProfessor() {
        return trackProfessor;
    }

    public void setTrackProfessor(int trackProfessor) {
        this.trackProfessor = trackProfessor;
    }
}
