package com.example.unispark.bean;

public class BeanVerbalizeExam extends BeanExam{

    //Attributes
    private String result;

    //Methods
    //Constructor
    public BeanVerbalizeExam(int id, String name, String year, String date, String CFU, String result) {
        super(id, name, year, date, CFU);
        this.result = result;
    }

    //Getter
    public String getResult() {
        return result;
    }


    //Setter
    public void setResult(String result) {
        this.result = result;
    }
}
