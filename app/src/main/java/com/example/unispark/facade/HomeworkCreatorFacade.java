package com.example.unispark.facade;

import com.example.unispark.model.HomeworkModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeworkCreatorFacade {

    private static HomeworkCreatorFacade instance=null;
    private HomeworkCreatorFacade()
    {

    }
    public static synchronized HomeworkCreatorFacade getInstance()
    {
        if(instance==null)
        {
            instance=new HomeworkCreatorFacade();
        }
        return instance;
    }

    public HomeworkModel createHomework(ResultSet rs) throws SQLException {
        //Homework attributes
        String shortName = rs.getString("shortname");
        String fullName = rs.getString("coursename");
        String title = rs.getString("title");
        String expiration = rs.getString("expiration");
        String  instructions = rs.getString("instructions");
        String points = rs.getString("points");
        int trackProfessor = rs.getInt("trackprofessor");

        //Create a new homework and add it to the homework list
        return new HomeworkModel(shortName, fullName, title, expiration, instructions, points, trackProfessor);
    }
}
