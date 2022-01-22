package com.example.unispark.database.dao;

import com.example.unispark.database.MySqlConnect;
import com.example.unispark.database.query.QueryStudentLinks;
import com.example.unispark.exceptions.LinkAlreadyExists;
import com.example.unispark.model.LinkModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentLinksDAO {

    public static final String LINK = "link";

    private StudentLinksDAO(){}


    public static void addStudentLink(LinkModel studentLink, String studentId) throws LinkAlreadyExists, SQLException {

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryStudentLinks.selectStudentLinks(statement, studentId);
            if (rs.first()) {
                String linkAddress;
                do {
                    linkAddress = rs.getString(LINK);
                    if (studentLink.getLinkAddress().equals(linkAddress)) throw new LinkAlreadyExists("Link already exists");
                } while(rs.next());
            }

            QueryStudentLinks.insertLink(statement, studentId, studentLink.getLinkName(), studentLink.getLinkAddress());


        } finally {
            if (statement != null){
                statement.close();
            }
        }




    }

    public static List<LinkModel> getStudentLinks(String studentID) throws SQLException {
        List<LinkModel> studentLinks = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryStudentLinks.selectStudentLinks(statement, studentID);

            if (rs.first()) {
                String linkName;
                String linkAddress;
                do {
                    linkName = rs.getString("name");
                    linkAddress = rs.getString(LINK);
                    studentLinks.add(new LinkModel(linkName, linkAddress));
                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return studentLinks;
    }



    public static void removeLink(String link) throws SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            QueryStudentLinks.deleteLink(statement, link);


        } finally {
            if (statement != null){
                statement.close();
            }
        }


    }
}
