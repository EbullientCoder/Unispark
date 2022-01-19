package com.example.unispark.database.others.provaDB.exams;

import com.example.unispark.model.exams.ExamModel;

public class BookExamModel extends ExamModel {
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


    //Getter
    public String getClassroom() {
        return classroom;
    }
    public String getBuilding() {
        return building;
    }


    //Setter
    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
    public void setBuilding(String building) {
        this.building = building;
    }
}
