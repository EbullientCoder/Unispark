package com.example.unispark.database.others.provaDB.exams;

import java.io.Serializable;

public class ExamModel implements Serializable {

    //Attributes
    private int id;
    private String name;
    private String year;
    private String date;
    private String CFU;


    //Methods
    //Constructor
    public ExamModel(int id, String name, String year, String date, String CFU) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.date = date;
        this.CFU = CFU;
    }


    //Getter
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getYear() {
        return year;
    }
    public String getDate() {
        return date;
    }
    public String getCFU() {
        return CFU;
    }


    //Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setCFU(String CFU) {
        this.CFU = CFU;
    }
}
