package com.example.unispark.database.dao;

import com.example.unispark.database.MySqlConnect;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryExams;
import com.example.unispark.exceptions.CourseAlreadyJoined;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.CourseNeverJoined;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.model.CourseModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    private static final String COURSENAME = "coursename";

    private CourseDAO(){}


    public static void joinCourse(String studentID, String courseName) throws SQLException, CourseDoesNotExist, CourseAlreadyJoined {
        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCourse.selectStudentCourses(statement, studentID);
            if(rs.first()){
                do{
                    if(rs.getString(COURSENAME).equals(courseName)) {

                        throw new CourseAlreadyJoined("Cannot join course, already joined");
                    }

                } while (rs.next());
            }

            rs = QueryCourse.selectCourseName(statement, courseName);
            if(!rs.first()){
                throw new CourseDoesNotExist("Cannot join course, does not exist");
            }

            QueryCourse.addStudentCourse(statement, studentID, courseName);

        } finally {
            if (statement != null){
                statement.close();
            }
        }



    }

    public static void leaveCourse(String studentID, String courseName) throws SQLException, ExamBookedException, CourseNeverJoined {


        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryExams.selectCourseExam(statement, studentID, courseName);

            if(rs.first()){

                throw new ExamBookedException("Exam booked, cannot leave course");
            }

            rs = QueryCourse.selectStudentCourses(statement, studentID);

            boolean isFound = false;
            if (rs.first()){
                String course;
                do{
                    course = rs.getString(COURSENAME);
                    if(course.equals(courseName)) {
                        isFound = true;
                        QueryCourse.removeStudentCourse(statement, studentID, courseName);
                        break;
                    }
                } while(rs.next());
            }
            if (!isFound) throw new CourseNeverJoined("Cannot leave, course never joined");

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }


    public static List<CourseModel> selectStudentCourses(String studentId) throws SQLException {
        List<CourseModel> coursesList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCourse.selectStudentCourses(statement, studentId);

            if (rs.first()){
                do{

                    coursesList.add(createCourse(rs));

                } while (rs.next());
            }

            rs.close();

        } finally {
            if (statement != null){
                statement.close();
            }
        }



        return coursesList;
    }

    public static List<CourseModel> selectProfessorCourses(int professorId) throws SQLException {
        List<CourseModel> coursesList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCourse.selectProfessorCourses(statement, professorId);

            if (rs.first()){
                do {
                    coursesList.add(createCourse(rs));
                } while (rs.next());
            }

            rs.close();


        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return coursesList;
    }

    //Get available course names to join for a student marked by faculty, year and that are not already in the student's courses list
    public static List<CourseModel> selectAvailableCourses(String faculty, int uniYear, List<CourseModel> courses) throws SQLException {
        List<CourseModel> coursesList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCourse.selectFacultyCourses(statement, faculty, uniYear);

            if (rs.first()){
                coursesList = getAvaliableCourses(rs, courses);
            }
            rs.close();


        } finally {
            if (statement != null){
                statement.close();
            }
        }

        return coursesList;
    }



    public static List<CourseModel> selectCourses(String faculty) throws SQLException {
        List<CourseModel> coursesList = new ArrayList<>();

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryCourse.selectFacultyCourses(statement, faculty);

            if (rs.first()){
                do{
                    coursesList.add(createCourse(rs));
                } while(rs.next());
            }
            rs.close();


        } finally {
            if (statement != null){
                statement.close();
            }
        }


        return coursesList;
    }




    private static CourseModel createCourse(ResultSet rs) throws SQLException {
        int courseId = rs.getInt("trackprofessor");
        String shortName = rs.getString("shortname");
        String fullName = rs.getString(COURSENAME);
        String courseYear = rs.getString("year");
        String cfu = rs.getString("cfu");
        String session = rs.getString("session");
        String link = rs.getString("link");
        String facultyCourse = rs.getString("faculty");
        int uniYear = rs.getInt("uniyear");

        CourseModel courseModel = new CourseModel(courseId, courseYear, cfu, session, link, facultyCourse, uniYear);
        courseModel.setShortName(shortName);
        courseModel.setFullName(fullName);
        return courseModel;
    }


    private static List<CourseModel> getAvaliableCourses(ResultSet rs, List<CourseModel> courses) throws SQLException {

        List<CourseModel> coursesList = new ArrayList<>();
        String courseName;

        boolean equals = false;
        do {
            courseName = rs.getString(COURSENAME);

            if (!courses.isEmpty()) {
                for (int i = 0; i < courses.size(); i++) {
                    if (courseName.equals(courses.get(i).getFullName())) {
                        equals = true;
                        break;
                    }
                }
            }
            if (!equals) {
                coursesList.add(createCourse(rs));
            }
            equals = false;
        } while (rs.next());

        return coursesList;
    }


}
