package com.example.unispark.exceptions;

public class CourseAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

    public CourseAlreadyExists(String message) {
        super(message);
    }
}
