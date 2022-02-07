package com.example.common.model.exams;

import java.io.Serializable;

public abstract class ExamModel implements Serializable{

    //Attributes
    private int id;
    private String name;
    private String year;
    private String date;
    private String cfu;


    //Methods
    //Constructor
    protected ExamModel(int id, String name, String year, String date, String cfu) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.date = date;
        this.cfu = cfu;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCfu() {
        return cfu;
    }

    public void setCfu(String cfu) {
        this.cfu = cfu;
    }
}
