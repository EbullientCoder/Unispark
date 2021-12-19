package com.example.unispark.model.exams;

public class FailedExamModel extends ExamModel{

    //Attributes
    private String result;

    //Methods
    //Constructor
    public FailedExamModel(int id, String name, String year, String date, String CFU, String result) {
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
