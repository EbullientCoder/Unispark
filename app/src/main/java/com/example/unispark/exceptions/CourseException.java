package com.example.unispark.exceptions;

/**
 *
 * @author Emanuele Valzano
 *
 */


public class CourseException extends Exception{


    /*
     * The code represents the operation that caused the error managing courses
     * add course -> 0
     * leave course -> 1
     */

    private final int code;
    private static final long serialVersionUID = 1L;

    public CourseException(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}