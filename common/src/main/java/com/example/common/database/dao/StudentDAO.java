package com.example.common.database.dao;


import com.example.common.database.MySqlConnect;
import com.example.common.database.query.QueryLogin;
import com.example.common.facade.StudentCreatorFacade;
import com.example.common.model.StudentModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.LoginException;

public class StudentDAO {

    private StudentDAO(){}

    //Get a Student using the email and password
    public static StudentModel selectStudent(String email, String password) throws LoginException, SQLException {
        Statement statement = null;
        Connection connection = null;
        StudentModel student;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryLogin.loginStudent(statement, email, password);

            if (!rs.first()) {

                throw new LoginException();

            }

            rs.first();
            String firstName = rs.getString("firstname");
            String lastName = rs.getString("lastname");
            String studentEmail = rs.getString("email");
            int profilePicture = rs.getInt("image");
            String faculty = rs.getString("faculty");
            String academicYear = rs.getString("academicyear");
            String studentId = rs.getString("studentID");
            int uniYear = rs.getInt("uniyear");
            //Compose the student entity
            List<String> nameEmail = new ArrayList<>();
            nameEmail.add(firstName);
            nameEmail.add(lastName);
            nameEmail.add(studentEmail);
            student = StudentCreatorFacade.getInstance().getStudent(nameEmail, profilePicture, studentId, faculty, academicYear, uniYear);

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return student;
    }
}
