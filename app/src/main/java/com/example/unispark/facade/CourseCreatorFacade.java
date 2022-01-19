package com.example.unispark.facade;

import android.database.Cursor;

import com.example.unispark.model.CourseModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CourseCreatorFacade {

    private static CourseCreatorFacade instance=null;
    private CourseCreatorFacade()
    {

    }
    public static synchronized CourseCreatorFacade getInstance()
    {
        if(instance==null)
        {
            instance=new CourseCreatorFacade();
        }
        return instance;
    }

    public CourseModel createCourse(ResultSet rs) throws SQLException {
        String courseId = String.valueOf(rs.getInt("trackprofessor"));
        String shortName = rs.getString("shortname");
        String fullName = rs.getString("coursename");
        String courseYear = rs.getString("year");
        String cfu = rs.getString("cfu");
        String session = rs.getString("session");
        String link = rs.getString("link");
        String facultyCourse = rs.getString("faculty");
        int uniYear = rs.getInt("uniyear");

        return new CourseModel(courseId, shortName, fullName, courseYear, cfu, session, link, facultyCourse, uniYear);
    }

    public List<CourseModel> getAvaliableCourses(ResultSet rs, List<CourseModel> courses) throws SQLException {

        List<CourseModel> coursesList = new ArrayList<>();
        String courseName;

        boolean equals = false;
        do {
            courseName = rs.getString("coursename");

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
