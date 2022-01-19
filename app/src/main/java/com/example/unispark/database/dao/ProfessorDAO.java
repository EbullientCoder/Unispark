package com.example.unispark.database.dao;

import com.example.unispark.database.others.MySqlConnect;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.database.query.QueryProfessor;
import com.example.unispark.facade.ProfessorCreatorFacade;
import com.example.unispark.model.ProfessorModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class ProfessorDAO {

    private ProfessorDAO(){}

    //Get a ProfessorModel using the email and password
    public static ProfessorModel selectProfessor(String email, String password) throws LoginException, SQLException {
        Statement statement = null;
        Connection connection = null;

        ProfessorModel professor;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryLogin.loginProfessor(statement, email, password);

            if (!rs.first()) {

                throw new LoginException();

            }

            int professorId = rs.getInt("professorID");
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String emailProfessor = rs.getString("email");
            String website = rs.getString("website");
            int profilePicture = rs.getInt("image");
            String faculty = rs.getString("faculty");

            professor = ProfessorCreatorFacade.getInstance().getProfessor(firstName, lastName, emailProfessor, profilePicture, professorId, faculty, website);

            rs.close();

        } finally {
            if (statement != null){
                statement.close();

            }
        }

        return professor;
    }

    //Get professor marked by faculty of studentId
    public static List<ProfessorModel> getFacultyProfessors(String faculty) throws SQLException {
        List<ProfessorModel> professors = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryProfessor.selectProfessorFaculty(statement, faculty);

            if (rs.first()) {

                ProfessorModel professor;
                int professorId;
                String firstName;
                String lastName;
                String emailProfessor;
                String website;
                int profilePicture;
                String professorFaculty;

                do {
                    professorId = rs.getInt("professorID");
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    emailProfessor = rs.getString("email");
                    website = rs.getString("website");
                    profilePicture = rs.getInt("image");
                    professorFaculty = rs.getString("faculty");

                    professor = ProfessorCreatorFacade.getInstance().getProfessor(firstName, lastName, emailProfessor, profilePicture, professorId, professorFaculty, website);
                    professors.add(professor);
                } while(rs.next());
            }

            rs.close();


        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return professors;
    }

}
