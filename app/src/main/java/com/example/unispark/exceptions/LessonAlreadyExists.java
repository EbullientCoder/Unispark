package com.example.unispark.exceptions;

import com.example.unispark.bean.BeanErrorLessonAlreadyExists;

/**
 *
 * @author Emanuele Valzano
 *
 */

public class LessonAlreadyExists extends Exception{

    private static final long serialVersionUID = 1L;
    private final String lessonName;
    private final String day;
    private final String hour;

    public LessonAlreadyExists(String lessonName, String day, String hour) {
        this.lessonName = lessonName;
        this.day = day;
        this.hour = hour;
    }

    public BeanErrorLessonAlreadyExists getMess() {
        return new BeanErrorLessonAlreadyExists(lessonName, day, hour);
    }
}
