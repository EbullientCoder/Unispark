package com.example.common.database.dao;

import com.example.common.database.MySqlConnect;
import com.example.common.database.query.QueryHomework;
import com.example.common.model.HomeworkModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeworkDAO {

    private HomeworkDAO(){}

    //Get homeworks of courses marked by studentID
    public static List<HomeworkModel> getStudentHomework(String studentID) throws SQLException {
        List<HomeworkModel> homeworkList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryHomework.selectHomeworks(statement, studentID);

            if (rs.first()){

                do{

                    homeworkList.add(createHomework(rs));

                } while (rs.next());

            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return homeworkList;
    }



    //Get homeworks marked by professorID
    public static List<HomeworkModel> getAssignedHomework(int professorID) throws SQLException {
        List<HomeworkModel> homeworkList = new ArrayList<>();


        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryHomework.selectProfessorHomework(statement, professorID);

            if (rs.first()){

                do{
                    homeworkList.add(createHomework(rs));
                } while (rs.next());
                //Reverse Homeworks List
                Collections.reverse(homeworkList);
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return homeworkList;
    }


    public static void addHomework(HomeworkModel homework) throws SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            List<String> name = new ArrayList<>();
            name.add(homework.getShortName());
            name.add(homework.getFullName());

            QueryHomework.insertHomework(statement, name, homework.getTitle(), homework.getExpiration(), homework.getInstructions(), homework.getPoints(), homework.getTrackProfessor());

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }


    public static HomeworkModel createHomework(ResultSet rs) throws SQLException {
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
