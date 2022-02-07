package com.example.unispark.exceptions;

import androidx.annotation.Nullable;

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
    private final String message;
    private static final long serialVersionUID = 1L;

    public ExamException(int code) {

        this.code = code;

        String mess = "";
        if (code == 0){
            mess = "Exam already exists";
        }
        else if (code == 1){
            mess = "Exam has not yet occured";
        }
        else if (code == 2){
            mess = "Exam verbalized, cannot book";
        }
        this.message = mess;
    }

    public int getCode() {
        return code;
    }

    @Nullable
    @Override
    public String getMessage() {
        return message;
    }
}

