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
    private String message;
    private static final long serialVersionUID = 1L;

    public ExamException(int code) {

        this.code = code;
        if (code == 0){
            this.message = "Exam already exists";
        }
        else if (code == 1){
            this.message = "Exam has not yet occured";
        }
        else if (code == 2){
            this.message = "Exam verbalized, cannot book";
        }
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

