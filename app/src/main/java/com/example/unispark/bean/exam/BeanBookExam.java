package com.example.unispark.bean.exam;

public class BeanBookExam extends BeanExam {

    private String classroom;
    private String building;



    public BeanBookExam(int id, String name, String year, String date, String CFU, String classroom, String building) {
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
