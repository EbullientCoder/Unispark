package com.example.common.model;

public class LessonModel {
    //Attributes
    private String lessonName;
    private String day;
    private String hour;

    //Methods
    //Constructor


    public LessonModel(String lessonName, String day, String hour) {
        this.lessonName = lessonName;
        this.day = day;
        this.hour = hour;
    }


    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}

