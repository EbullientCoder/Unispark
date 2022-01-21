package com.example.unispark.database.query;

import static com.example.unispark.database.others.Password.getHash;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryLogin {
    // Students, professors and University tables
    private static final String STUDENTS_TABLE = "students";
    private static final String PROFESSORS_TABLE = "professors";
    private static final String UNI_TABLE = "university";

    private QueryLogin(){}

    //Look for a student account in the database, marked by email and password
    public static ResultSet loginStudent(Statement statement, String email, String password) throws SQLException
    {

        String queryString;
        queryString = "SELECT * FROM " + STUDENTS_TABLE + " where email = '" + email
                + "' AND password = '" + getHash(password) + "';";

        return statement.executeQuery(queryString);
    }



    //Look for a professor account in the database, marked by email and password
    public static ResultSet loginProfessor(Statement statement, String email, String password) throws SQLException
    {

        String queryString;
        queryString = "SELECT * FROM " + PROFESSORS_TABLE + " where email = '" + email
                + "' AND password = '" + getHash(password) + "';";
        return statement.executeQuery(queryString);
    }


    //Look for a University account in the database, marked by email and password
    public static ResultSet loginUniversity(Statement statement, String email, String password) throws SQLException {

        String queryString = "SELECT * FROM " + UNI_TABLE + " where email = '" + email
                + "' AND password = '" + getHash(password) + "';";
        return statement.executeQuery(queryString);
    }










}
