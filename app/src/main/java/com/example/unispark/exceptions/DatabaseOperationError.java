package com.example.unispark.exceptions;

/**
 *
 * @author Andrea Lapiana
 *
 */


public class DatabaseOperationError extends Exception {

    /*
     * The code represents the operation that caused the error
     * during the DB interaction
     *
     * Insertion -> 0
     * Removal -> 1
     */

    private final int code;
    private static final long serialVersionUID = 1L;

    public DatabaseOperationError(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }






}
