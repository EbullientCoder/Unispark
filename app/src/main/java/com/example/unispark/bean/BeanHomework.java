package com.example.unispark.bean;

import java.io.Serializable;

public class BeanHomework implements Serializable {

    //Attributes
    private String shortName;
    private String fullName;
    private String title;
    private String expiration;
    private String instructions;
    private String points;


    

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


    //Setter
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public boolean setTitle(String title) {
        //Return True and set Title if the string isn't empty
        if(emptyCheck(title)){
            this.title = title;
            return true;
        }
        else return false;
    }
    public boolean setExpiration(String expiration) {
        //Return True and set Expiration if the string isn't empty
        if(emptyCheck(expiration)){
            this.expiration = expiration;
            return true;
        }
        else return false;
    }
    public boolean setInstructions(String instructions) {
        //Return True and set Instructions if the string isn't empty
        if(emptyCheck(instructions)){
            this.instructions = instructions;
            return true;
        }
        else return false;
    }
    public boolean setPoints(String points) {
        //Return True and set Expiration if the string isn't empty
        if(emptyCheck(points)){
            this.points = points;
            return true;
        }
        else return false;
    }


    //Syntactic Validation
    private boolean emptyCheck(String text){
        return !text.isEmpty();

    }
}
