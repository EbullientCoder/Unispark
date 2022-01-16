package com.example.unispark.exceptions;

public class ExamAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

    public ExamAlreadyExists(String message) {
        super(message);
    }
}
