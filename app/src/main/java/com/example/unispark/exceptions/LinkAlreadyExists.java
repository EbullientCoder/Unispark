package com.example.unispark.exceptions;

/**
 *
 * @author Andrea Lapiana
 *
 */

public class LinkAlreadyExists extends Exception{
    private static final long serialVersionUID = 1L;

    public String getMess(){
        return "Link already exists";
    }

}
