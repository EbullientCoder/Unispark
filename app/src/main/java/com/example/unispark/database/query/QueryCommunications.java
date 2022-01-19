package com.example.unispark.database.query;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryCommunications {

    //Communications tables (University and Professors)
    public static final String UNI_COMMUNICATIONS = "universitycommunications";
    public static final String FACULTY = "faculty";
    public static final String PROF_COMMUNICATIONS = "professorcommunications";
    public static final String SHORTNAME = "shortname";


    private QueryCommunications(){}


    public static void insertCommunication(Statement stmt, int image, String title, String date, String communication, String faculty) throws SQLException {
        stmt.executeUpdate("INSERT INTO universitycommunications(image, title, date, communication, faculty) VALUES(" +image+ ", '" +title+ "', '" +date+ "', '" +communication+ "', '" +faculty+ "')");
    }



    public static void insertCommunication(Statement stmt, String shortName, String date, String title, String communication) throws SQLException {
        stmt.executeUpdate("INSERT INTO professorcommunications(shortname, date, title, communication) VALUES('" +shortName+ "', '" +date+ "', '" +title+ "', '" +communication+ "')");
    }

    //Look for University communications marked by facultyName
    public static ResultSet selectFacultyCommunications(Statement statement, String facultyName) throws SQLException
    {
        String queryString;
        if(facultyName.equals("all")) queryString = "SELECT * FROM " + UNI_COMMUNICATIONS + ";";
        else queryString = "SELECT * FROM " + UNI_COMMUNICATIONS + " WHERE " + FACULTY + " = '" + facultyName + "' OR " + FACULTY + " = 'All';";

        return statement.executeQuery(queryString);
    }


    //Look for Professor communications marked by courseShortName
    public static ResultSet selectCourseCommunications(Statement statement, String courseShortName) throws SQLException
    {
        String queryString = "SELECT *  FROM professorcommunications INNER JOIN courses ON professorcommunications.shortname = courses.shortname " +
                "INNER JOIN professors ON courses.trackprofessor = professors.professorID WHERE professorcommunications.shortname = '"+courseShortName+"';";
        return statement.executeQuery(queryString);
    }
}
