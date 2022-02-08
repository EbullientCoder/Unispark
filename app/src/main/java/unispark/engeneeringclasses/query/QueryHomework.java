package unispark.engeneeringclasses.query;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class QueryHomework {
    //Homework table
    private static final String HOMEWORK_TABLE = "homework";
    private static final String TRACK_PROFESSOR = "trackprofessor";

    private QueryHomework(){}

    //Look for homeworks marked by courseName
    public static ResultSet selectHomeworks(Statement statement, String studentId) throws SQLException
    {
        String queryString = "SELECT * FROM studentscourses INNER JOIN homework ON studentscourses.coursename = homework.coursename " +
                "WHERE studentscourses.studentID = '" + studentId + "';";
        return statement.executeQuery(queryString);
    }

    //Look for homeworks tracked by professorID
    public static ResultSet selectProfessorHomework(Statement statement, int professorID) throws SQLException
    {
        String queryString = "SELECT * FROM " + HOMEWORK_TABLE + " WHERE " + TRACK_PROFESSOR + " = " + professorID + ";";
        return statement.executeQuery(queryString);
    }


    public static void insertHomework(Statement stmt, List<String> name, String title, String expiration, String instructions,
                                      String points, int trackProfessor) throws SQLException {
        stmt.executeUpdate("INSERT INTO homework(shortname, coursename, title, expiration, instructions, points, trackprofessor) " +
                "VALUES('" +name.get(0)+ "', '" +name.get(1)+ "', '" +title+ "', '" +expiration+ "', '" +instructions+ "', '" +points+ "', " +trackProfessor+ ")");
    }

}
