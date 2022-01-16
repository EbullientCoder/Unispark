package com.example.unispark.exceptions;

/**
 *
 * @author Emanuele Valzano
 *
 */


public class ExamException extends Exception{

    /*
     * The code represents the operation that caused the error managing exams
     * add exam -> 0
     * add exam grade -> 1
     * book exam -> 2
     */

    private final int code;
    private static final long serialVersionUID = 1L;

    public ExamException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}

