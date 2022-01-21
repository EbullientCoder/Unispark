package com.example.unispark.model.exams;


public class BookExamModel extends ExamModel{
    //Attributes
    private String classroom;
    private String building;


    //Methods
    //Constructor
    public BookExamModel(int id, String name, String year, String date, String CFU, String classroom, String building) {
        super(id, name, year, date, CFU);
        this.classroom = classroom;
        this.building = building;
    }


    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }
}
