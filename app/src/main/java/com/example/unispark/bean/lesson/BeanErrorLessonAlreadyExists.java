package com.example.unispark.bean.lesson;

public class BeanErrorLessonAlreadyExists {

    private String lesson;
    private String day;
    private String hour;

    public BeanErrorLessonAlreadyExists(String lesson, String day, String hour) {
        this.lesson = lesson;
        this.day = day;
        this.hour = hour;
    }

    public String getMessage() {
        return "Lesson ** " + lesson + " ** already exists on " + day + " at hour " + hour;
    }
}
