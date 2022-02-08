package unispark.engeneeringclasses.query;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryFaculties {

    private static final String FACULTIES = "faculties";
    private static final String FACULTY = "faculty";

    private QueryFaculties(){}


    //Look for all university faculties
    public static ResultSet selectFaculties(Statement statement) throws SQLException
    {
        String queryString = "SELECT " + FACULTY + " FROM " + FACULTIES + ";";
        return statement.executeQuery(queryString);
    }
}
