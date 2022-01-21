package com.example.unispark.bean.exams;

public class BeanBookExam extends BeanExam {

    private String classroom;
    private String building;




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
