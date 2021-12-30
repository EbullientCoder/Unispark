package com.example.unispark.provaDB.exams;

import com.example.unispark.model.exams.ExamModel;

public class VerbalizedExamModel extends ExamModel {

    //Attributes
    private String result;

    //Methods
    //Constructor
    public VerbalizedExamModel(int id, String name, String year, String date, String CFU, String result) {
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
