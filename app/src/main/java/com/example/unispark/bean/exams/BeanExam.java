package com.example.unispark.bean.exams;

import java.io.Serializable;

public class BeanExam implements Serializable {

    private int id;
    private String name;
    private String year;
    private String date;
    private String CFU;



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

    public String getCFU() {
        return CFU;
    }

    public void setCFU(String CFU) {
        this.CFU = CFU;
    }
}
