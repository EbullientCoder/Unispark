package com.example.common.exceptions;

public class ExamAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

    public ExamAlreadyExists(String message) {
        super(message);
    }
}
