package com.example.unispark.provaDB;

import java.io.Serializable;

public class HomeworkModel implements Serializable {
    //Attributes
    private String shortName;
    private String fullname;
    private String title;
    private String expiration;
    private String instructions;
    private String points;
    private int trackProfessor;


    //Methods
    //Constructor
    public HomeworkModel(String shortName, String fullname, String title, String expiration, String instructions, String points, int trackProfessor) {
        this.shortName = shortName;
        this.fullname = fullname;
        this.title = title;
        this.expiration = expiration;
        this.instructions = instructions;
        this.points = points;
        this.trackProfessor = trackProfessor;
    }

    //Getter
    public String getShortName() {
        return shortName;
    }
    public String getFullname() {
        return fullname;
    }
    public String getTitle(){
        return title;
    }
    public String getExpiration() {
        return expiration;
    }
    public String getInstructions() {
        return instructions;
    }
    public String getPoints() {
        return points;
    }
    public int getTrackProfessor() {return trackProfessor; }

    //Setter
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setExpiration(String expiration) {
        this.expiration = expiration;
    }
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
    public void setPoints(String points) {
        this.points = points;
    }
    public void setTrackProfessor(int trackProfessor) {this.trackProfessor = trackProfessor; }
}
