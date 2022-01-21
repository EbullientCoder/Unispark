package com.example.unispark.bean.homework;

import java.io.Serializable;

public class BeanHomework implements Serializable {

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
    public BeanHomework(String shortName, String fullName, String title, String expiration, String instructions, String points, int trackProfessor) {
        this.shortName = shortName;
        this.fullName = fullName;
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
    public String getFullName() {
        return fullName;
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
    public void setFullName(String fullName) {
        this.fullName = fullName;
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
