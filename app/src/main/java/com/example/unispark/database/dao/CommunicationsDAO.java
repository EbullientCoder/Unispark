package com.example.unispark.database.dao;

import com.example.unispark.database.others.MySqlConnect;
import com.example.unispark.database.query.QueryCommunications;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.model.communications.ProfessorCommunicationModel;
import com.example.unispark.model.communications.UniversityCommunicationModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommunicationsDAO {

    private CommunicationsDAO(){}

    public static void addUniversityCommunication(UniversityCommunicationModel communication) throws SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


            QueryCommunications.insertCommunication(statement, communication.getBackground(), communication.getTitle(),
                    communication.getDate(), communication.getCommunication(), communication.getFaculty());


        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }


    public static void addProfessorCommunication(ProfessorCommunicationModel communication) throws SQLException, CourseDoesNotExist {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCourse.selectCourseName(statement,communication.getFullName());
            if (!rs.first()){
                throw new CourseDoesNotExist("Cannot add communication, course does not exist");
            }


            QueryCommunications.insertCommunication(statement, communication.getShortCourseName(), communication.getDate(), communication.getType(), communication.getCommunication());

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }


    //Look for University communications marked by faculty
    public static List<UniversityCommunicationModel> getUniversityCommunications(String facultyName) throws SQLException {
        List<UniversityCommunicationModel> communicationsList = new ArrayList<>();
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCommunications.selectFacultyCommunications(statement, facultyName);

            if (rs.first()) {
                //Communication Model
                UniversityCommunicationModel communicationModel;
                //Attributes
                int background;
                String title;
                String date;
                String communication;
                String faculty;

                do {
                    background = rs.getInt("image");
                    title = rs.getString("title");
                    date = rs.getString("date");
                    communication = rs.getString("communication");
                    faculty = rs.getString("faculty");
                    //Add CommunicationModel to List
                    communicationModel = new UniversityCommunicationModel(background, title, date, communication, faculty);
                    communicationsList.add(communicationModel);
                } while (rs.next());
                //Reverse communications order
                Collections.reverse(communicationsList);
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }
        return communicationsList;
    }


    public static List<ProfessorCommunicationModel> getCourseCommunications(String courseShortName, String courseFullName) throws SQLException {
        List<ProfessorCommunicationModel> communicationsList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


            ResultSet rs = QueryCommunications.selectCourseCommunications(statement, courseShortName);

            if (rs.first()){
                //Communication Model
                ProfessorCommunicationModel communicationModel;
                //Attributes
                int profilePhoto;
                String shortName;
                String professorName;
                String date;
                String type;
                String communication;

                do {
                    shortName = rs.getString("shortname");
                    date = rs.getString("date");
                    type = rs.getString("title");
                    communication = rs.getString("communication");

                    professorName = rs.getString("firstname") + " " + rs.getString("lastname");
                    profilePhoto = rs.getInt("image");

                    //Add CommunicationModel to List
                    communicationModel = new ProfessorCommunicationModel(profilePhoto, shortName, courseFullName, professorName, date, type, communication);
                    communicationsList.add(communicationModel);

                } while(rs.next());
                //Reverse communications order
                Collections.reverse(communicationsList);
            }

            rs.close();


        } finally {
            if (statement != null){
                statement.close();
            }
        }
        return communicationsList;
    }
}
