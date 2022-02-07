package com.example.common.exceptions;

public class CourseDoesNotExist extends Exception{

    private static final long serialVersionUID = 1L;

    public CourseDoesNotExist(String message) {
        super(message);
    }
}
