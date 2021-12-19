package com.example.unispark.model.exams;

public class FailedExamModel {

    //Attributes
    private String examName;
    private String examYear;
    private String examDate;
    private String examCFU;
    private String examResult;

    //Methods
    //Constructor
    public FailedExamModel(String examName, String examYear, String examDate, String examCFU, String examResult) {
        this.examName = examName;
        this.examYear = examYear;
        this.examDate = examDate;
        this.examCFU = examCFU;
        this.examResult = examResult;
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
    public String getExamResult() {
        return examResult;
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
    public void setExamResult(String examResult) {
        this.examResult = examResult;
    }
}
