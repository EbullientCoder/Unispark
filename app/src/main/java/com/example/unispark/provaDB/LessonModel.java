package com.example.unispark.provaDB;

public class LessonModel {
    //Attributes
    private String time;
    private String lesson;


    //Methods
    //Constructor
    public LessonModel(String time, String lesson) {
        this.time = time;
        this.lesson = lesson;
    }

    //Getter
    public String getTime() {
        return time;
    }
    public String getLesson() {
        return lesson;
    }

    //Setter
    public void setTime(String time) {
        this.time = time;
    }
    public void setLesson(String lesson) {
        this.lesson = lesson;
    }
}
