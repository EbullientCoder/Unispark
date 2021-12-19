package com.example.unispark.model.exams;

public class ReserveExamModel {

    //Attributes
    private String examName;
    private String examYear;
    private String examDate;
    private String examCFU;

    //Methods
    //Constructor
    public ReserveExamModel(String examName, String examYear, String examDate, String examCFU) {
        this.examName = examName;
        this.examYear = examYear;
        this.examDate = examDate;
        this.examCFU = examCFU;
    }

    //Getter
    public String getExamName() {
        return examName;
    }
    public String getExamYear() {
        return examYear;
    }
    public String getExamDate() {
        return examDate;
    }
    public String getExamCFU() {
        return examCFU;
    }


    //Setter
    public void setExamName(String examName) {
        this.examName = examName;
    }
    public void setExamYear(String examYear) {
        this.examYear = examYear;
    }
    public void setExamDate(String examDate) {
        this.examDate = examDate;
    }
    public void setExamCFU(String examCFU) {
        this.examCFU = examCFU;
    }
}
