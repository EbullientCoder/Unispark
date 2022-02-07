package com.example.common.bean;

public class BeanErrorLessonAlreadyExists {

    private String lesson;
    private String day;
    private String hour;


    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getMessage() {
        return "Lesson ** " + lesson + " ** already exists on " + day + " at hour " + hour;
    }
}
