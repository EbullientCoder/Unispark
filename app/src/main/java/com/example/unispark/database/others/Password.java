package com.example.unispark.database.others;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String getHash (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder sb = new StringBuilder();

            for (byte b : resultByteArray) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static boolean checkPassword(String password, String storedPassword){

        String tempHash = getHash(password);
        if(tempHash.equals(storedPassword)){
            return true;
        }
        else{
            return false;
        }
    }

}