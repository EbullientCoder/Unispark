package unispark.engeneeringclasses.dao;

import unispark.engeneeringclasses.MySqlConnect;
import unispark.engeneeringclasses.query.QueryLogin;
import unispark.engeneeringclasses.model.UniversityModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.security.auth.login.LoginException;

public class UniversityDAO {

    private UniversityDAO(){}

    public static UniversityModel selectUniversity(String email, String password) throws LoginException, SQLException {
        UniversityModel university;

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryLogin.loginUniversity(statement, email, password);

            if (!rs.first()) {

                throw new LoginException();

            }

            String name = rs.getString("name");
            String streetAddress = rs.getString("streetaddress");
            int profilePicture = rs.getInt("image");
            String universityEmail = rs.getString("email");
            List<String> faculties = FacultyDAO.getUniversityFaculties();

            university = new UniversityModel(name, universityEmail, profilePicture, streetAddress, faculties);

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return university;
    }
}
