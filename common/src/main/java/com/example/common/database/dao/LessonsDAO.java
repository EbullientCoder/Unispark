package com.example.common.database.dao;


import com.example.common.database.MySqlConnect;
import com.example.common.database.query.QueryLessons;
import com.example.common.exceptions.LessonAlreadyExists;
import com.example.common.model.CourseModel;
import com.example.common.model.LessonModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LessonsDAO {

    private LessonsDAO(){}

    public static void addLesson(LessonModel lesson) throws LessonAlreadyExists, SQLException {

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            ResultSet rs = QueryLessons.selectLessons(statement, lesson.getDay(), lesson.getLessonName(), lesson.getHour());
            if (rs.first()) throw new LessonAlreadyExists(lesson.getLessonName(), lesson.getDay(), lesson.getHour());

            QueryLessons.insertLesson(statement, lesson.getLessonName(), lesson.getDay(), lesson.getHour());

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }




    public static List<LessonModel> getLessons(String day, List<CourseModel> courses) throws SQLException {
        List<LessonModel> lessons = new ArrayList<>();

        if (!courses.isEmpty()){

            Statement statement = null;
            Connection connection = null;

            try {
                connection = MySqlConnect.getInstance().getDBConnection();
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                        ResultSet.CONCUR_READ_ONLY);


                ResultSet rs;
                for (int i = 0; i < courses.size(); i++){
                    rs  = QueryLessons.selectLessons(statement, day, courses.get(i).getFullName());
                    if (rs.first()) {
                        //Add lesson to the Lessons' list
                        do{
                            lessons.add(new LessonModel(rs.getString("lesson"), rs.getString("day"), rs.getString("hour")));
                        } while(rs.next());
                    }
                    rs.close();
                }

            } finally {
                if (statement != null){
                    statement.close();
                }
            }
        }

        return lessons;
    }




    public static void removeLesson(LessonModel lesson) throws SQLException {

        Statement statement = null;
        Connection connection = null;

        try {
            connection = MySqlConnect.getInstance().getDBConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);

            QueryLessons.deleteLesson(statement, lesson.getLessonName(), lesson.getDay(), lesson.getHour());

        } finally {
            if (statement != null){
                statement.close();
            }
        }
    }


}
