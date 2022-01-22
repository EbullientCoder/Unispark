package com.example.unispark.database.dao;

import com.example.unispark.database.MySqlConnect;
import com.example.unispark.database.query.QueryFaculties;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FacultyDAO {



    private FacultyDAO(){}



    public static List<String> getUniversityFaculties() throws SQLException {

        List<String> faculties = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryFaculties.selectFaculties(statement);

            if (rs.first()) {
                do{
                    faculties.add(rs.getString("faculty"));
                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return faculties;
    }
}
