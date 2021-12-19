package com.example.unispark.model.exams;

public class VerbalizedExamModel extends ExamModel{

    //Attributes
    private int result;

    //Methods
    //Constructor
    public VerbalizedExamModel(int id, String name, String year, String date, String CFU, int result) {
        super(id, name, year, date, CFU);
        this.result = result;
    }

    //Getter
    public int getResult() {
        return result;
    }


    //Setter
    public void setResult(int result) {
        this.result = result;
    }
}
