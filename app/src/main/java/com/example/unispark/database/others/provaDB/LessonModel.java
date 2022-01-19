package com.example.unispark.database.others.provaDB;

public class LessonModel {
    //Attributes
    private String lessonName;
    private String day;
    private String hour;




    //Methods
    //Constructor


    public LessonModel(String lessonName, String date, String hour) {
        this.lessonName = lessonName;
        this.day = date;
        this.hour = hour;
    }


    public String getLessonName() {
        return lessonName;
    }

    public String getDay() {
        return day;
    }

    public String getHour() {
        return hour;
    }



    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

}
