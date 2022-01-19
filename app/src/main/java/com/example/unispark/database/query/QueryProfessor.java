package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryProfessor {

    //Professor table
    public static final String PROFESSORS_TABLE = "professors";
    public static final String PROF_ID = "professorID";
    public static final String TRACK_PROFESSOR = "trackprofessor";
    public static final String IMAGE = "image";
    public static final String COURSE_TABLE = "courses";
    public static final String SHORTNAME = "shortname";
    public static final String FIRSTNAME = "firstname";
    public static final String LASTNAME = "lastname";
    public static final String FACULTY = "faculty";


    private QueryProfessor(){}

    //Look for professor's id tracked by courseShortName
    public static ResultSet getProfessorId(Statement statement, String courseShortName) throws SQLException
    {
        String queryString = "SELECT " + TRACK_PROFESSOR + " FROM " + COURSE_TABLE + " WHERE " + SHORTNAME + " = '" + courseShortName + "';";
        return statement.executeQuery(queryString);
    }

    //Look for professor's profile imageID marked by professorId
    public static ResultSet getProfessorProfileImage(Statement statement, int professorId) throws SQLException
    {
        String queryString = "SELECT " + IMAGE + " FROM " + PROFESSORS_TABLE + " WHERE " + PROF_ID + " = " + professorId + ";";
        return statement.executeQuery(queryString);
    }

    //Look for professor's Name marked by professorId
    public static ResultSet getProfessorName(Statement statement, int professorId) throws SQLException
    {
        String queryString = "SELECT " + FIRSTNAME + ", " + LASTNAME + " FROM " + PROFESSORS_TABLE + " WHERE " + PROF_ID + " = " + professorId + ";";
        return statement.executeQuery(queryString);
    }

    //Look for professor marked by faculty
    public static ResultSet selectProfessorFaculty(Statement statement, String faculty) throws SQLException
    {
        String queryString = "SELECT * FROM " + PROFESSORS_TABLE + " WHERE " + FACULTY + " = '" + faculty + "';";
        return statement.executeQuery(queryString);
    }


}
