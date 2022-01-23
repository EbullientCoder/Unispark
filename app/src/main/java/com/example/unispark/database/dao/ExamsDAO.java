package com.example.unispark.database.dao;

import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.MySqlConnect;
import com.example.unispark.database.query.QueryExams;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ExamsDAO {

    public static final String GRADE = "grade";

    private ExamsDAO(){}

    public static void addExam(BookExamModel exam) throws ExamException, SQLException {

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryExams.selectExam(statement, exam.getName(), exam.getDate());

            if (rs.first()) throw new ExamException(0);

            QueryExams.insertExam(statement, exam.getName(), exam.getDate(),  exam.getBuilding(), exam.getClassroom());

        } finally {
            if (statement != null){
                statement.close();
            }
        }

    }


    public static void addExamGrade(VerbalizedExamModel examGrade, String studentID) throws ExamException, SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            QueryExams.insertGrade(statement, examGrade.getId(), examGrade.getName(), studentID, examGrade.getResult());
            removeBookedExam(examGrade.getId(), studentID);

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }



    //Select exams marked my courseName
    public static List<BookExamModel> getCourseExams(CourseModel course, boolean isProfessor) throws SQLException {
        List<BookExamModel> examsList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryExams.selectExams(statement, course.getFullName(), isProfessor);

            if (rs.first()){
                do {
                    examsList.add(ExamsFacade.getInstance().bookingExam(rs));
                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return examsList;
    }




    public static void bookExam(BookExamModel exam, String studentID) throws ExamException, SQLException {

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryExams.selectExamGrades(statement, studentID);

            if(rs.first()){
                String takenExam;
                String result;
                do {
                    takenExam = rs.getString("examname");
                    result = rs.getString(GRADE);
                    if (exam.getName().equals(takenExam) && Double.valueOf(result) >= 18) throw new ExamException(2);
                } while (rs.next());
            }

            QueryExams.bookStudentExam(statement, studentID, exam.getId());

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }



    //Get booked exams marked by studentID
    public static List<BookExamModel> getBookedExams(String studentID) throws SQLException {
        List<BookExamModel> bookedExamsList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);


            ResultSet rs = QueryExams.selectBookedExams(statement, studentID);
            if (rs.first()) {
                do {

                    bookedExamsList.add(ExamsFacade.getInstance().bookingExam(rs));

                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return bookedExamsList;
    }




    //Get verbalized exams
    public static List<VerbalizedExamModel> getVerbalizedExams(String studentID) throws SQLException {
        List<VerbalizedExamModel> gradesList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            doConnect(statement,connection);

            rs = QueryExams.selectExamGrades(statement, studentID);
            if (rs.first()) {
                do {
                    String result = rs.getString(GRADE);
                    double numberResult = Double.parseDouble(result);
                    if (numberResult >= 18){
                        gradesList.add(ExamsFacade.getInstance().examGrade(rs, result));
                    }
                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return gradesList;
    }


    //Get Not passed exams List
    public static List<VerbalizedExamModel> getFailedExams(String studentID) throws SQLException {
        List<VerbalizedExamModel> gradesList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;
        ResultSet rs = null;

        try {
            doConnect(statement,connection);

            rs = QueryExams.selectExamGrades(statement, studentID);
            if (rs.first()) {
                do {
                    String result = rs.getString("GRADE");
                    double numberResult = Double.parseDouble(result);
                    if (numberResult < 18){
                        gradesList.add(ExamsFacade.getInstance().examGrade(rs, result));
                    }
                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return gradesList;
    }


    private static void doConnect(Statement statement, Connection connection) throws SQLException {

        connection = MySqlConnect.getInstance().getDBConnection();
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);
    }




    //Get students that booked an exam
    public static List<BeanStudentSignedToExam> getStudentsBookedExam(int examID) throws SQLException {
        List<BeanStudentSignedToExam> studentsList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryExams.selectStudents(statement, examID);
            if (rs.first()) {

                //BeanStudentSignedToExam
                BeanStudentSignedToExam student;
                String id;
                String fullName;

                do{
                    id = rs.getString("studentID");
                    fullName = rs.getString("firstname") + " " + rs.getString("lastname");

                    //Create Student and add it to the list
                    student = new BeanStudentSignedToExam();
                    student.setFullName(fullName);
                    student.setId(id);
                    studentsList.add(student);
                } while(rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return studentsList;
    }



    //Remove studentID booked exam from DB
    public static void removeBookedExam(int examID, String studentID) throws SQLException {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            QueryExams.deleteBookedExam(statement, studentID, examID);

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }

}
