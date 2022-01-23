package com.example.unispark.database.query;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryProfessor {

    //Professor table
    private static final String PROFESSORS_TABLE = "professors";
    private static final String FACULTY = "faculty";


    private QueryProfessor(){}

    //Look for professor marked by faculty
    public static ResultSet selectProfessorFaculty(Statement statement, String faculty) throws SQLException
    {
        String queryString = "SELECT * FROM " + PROFESSORS_TABLE + " WHERE " + FACULTY + " = '" + faculty + "';";
        return statement.executeQuery(queryString);
    }


}
