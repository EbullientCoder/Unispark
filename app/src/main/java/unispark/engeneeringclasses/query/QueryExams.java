package unispark.engeneeringclasses.query;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryExams {

    private static final String EXAMS = "exams";
    private static final String EXAM_NAME = "examname";
    private static final String DATE = "date";


    private QueryExams(){}

    //Look for exams marked by courseName
    public static ResultSet selectExams(Statement statement, String courseName) throws SQLException
    {
        String queryString = "SELECT * FROM exams INNER JOIN courses ON exams.examname = courses.coursename WHERE courses.coursename = '" + courseName + "' AND " + DATE + " >= NOW();";

        return statement.executeQuery(queryString);
    }

    //Look for exams marked by courseName
    public static ResultSet selectProfessorExams(Statement statement, int professorId) throws SQLException
    {
        String queryString = "SELECT * FROM exams INNER JOIN courses ON exams.examname = courses.coursename WHERE courses.trackprofessor = " + professorId + ";";
        return statement.executeQuery(queryString);
    }

    //Look for exams marked by studentID
    public static ResultSet selectBookedExams(Statement statement, String studentID) throws SQLException
    {
        String queryString = "SELECT * FROM studentexams INNER JOIN exams ON studentexams.examID = exams.examID " +
                "INNER JOIN courses ON exams.examname = courses.coursename WHERE studentexams.studentID = '" + studentID + "';";
        return statement.executeQuery(queryString);

    }



    //Look for exam grades marked by studentID
    public static ResultSet selectExamGrades(Statement statement, String studentID) throws SQLException
    {
        String queryString = "SELECT * FROM examgrades INNER JOIN exams ON examgrades.examID = exams.examID " +
                "INNER JOIN courses ON exams.examname = courses.coursename WHERE examgrades.studentID = '" + studentID + "';";
        return statement.executeQuery(queryString);
    }

    public static ResultSet selectExamDate(Statement statement, int examID) throws SQLException{
        String queryString = "SELECT " + DATE + " FROM " + EXAMS + " WHERE examID = " + examID + " AND " + DATE + " >= NOW() ;";
        return statement.executeQuery(queryString);


    }



    public static ResultSet selectStudents(Statement statement, int examID) throws SQLException
    {
        String queryString = "SELECT * FROM studentexams INNER JOIN students ON studentexams.studentID = students.studentID WHERE studentexams.examID = " + examID + ";";
        return statement.executeQuery(queryString);

    }



    public static ResultSet selectExam(Statement statement, String name, String date) throws SQLException
    {
        String queryString = "SELECT * FROM " + EXAMS + " WHERE " + EXAM_NAME + " = '" + name + "' AND " + DATE + " = '" + date + "';";
        return statement.executeQuery(queryString);
    }


    public static ResultSet selectCourseExam(Statement statement, String studentId, String courseName) throws SQLException
    {
        String queryString = "SELECT * FROM studentexams INNER JOIN exams ON studentexams.examID = exams.examID WHERE exams.examname = '" + courseName +
                "' AND studentexams.studentID = '" + studentId + "';";

        return statement.executeQuery(queryString);
    }


    public static void insertExam(Statement stmt, String examName, String date, String building, String classRoom) throws SQLException {
        stmt.executeUpdate("INSERT INTO exams(examname, date, building, class) VALUES('" +examName+ "', '" +date+ "', '" +building+ "', '" +classRoom+ "')");
    }



    public static void insertGrade(Statement stmt, int examId, String examName, String studentId, String grade) throws SQLException {
        stmt.executeUpdate("INSERT INTO examgrades(examID, examname, studentID, grade) VALUES(" +examId+ ", '" +examName+ "', '" +studentId+ "', '" +grade+ "')");
    }


    public static void bookStudentExam(Statement stmt, String studentId, int examId) throws SQLException {
        stmt.executeUpdate("INSERT INTO studentexams(studentID, examID) VALUES('" +studentId+ "', " +examId+ ")");
    }


    public static void deleteBookedExam(Statement stmt, String studentId, int examId) throws SQLException {
        stmt.executeUpdate("DELETE FROM studentexams WHERE studentID = '" +studentId+ "' AND examID = " + examId);
    }


}
