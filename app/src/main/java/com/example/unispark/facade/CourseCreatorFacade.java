package com.example.unispark.facade;

import android.database.Cursor;

import com.example.unispark.model.CourseModel;

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

    public CourseModel createCourse(Cursor cursor){
        String courseId = String.valueOf(cursor.getInt(7));
        String shortName = cursor.getString(1);
        String fullName = cursor.getString(2);
        String courseYear = cursor.getString(3);
        String cfu = cursor.getString(4);
        String session = cursor.getString(5);
        String link = cursor.getString(6);
        String facultyCourse = cursor.getString(8);
        int uniYear = cursor.getInt(9);

        return new CourseModel(courseId, shortName, fullName, courseYear, cfu, session, link, facultyCourse, uniYear);
    }

    public List<CourseModel> getAvaliableCourses(Cursor cursor, List<CourseModel> courses) {

        List<CourseModel> coursesList = new ArrayList<>();
        String courseName;

        boolean equals = false;
        do {
            courseName = cursor.getString(2);

            if (!courses.isEmpty()) {
                for (int i = 0; i < courses.size(); i++) {
                    if (courseName.equals(courses.get(i).getFullName())) {
                        equals = true;
                        break;
                    }
                }
            }
            if (!equals) {
                coursesList.add(createCourse(cursor));
            }
            equals = false;
        } while (cursor.moveToNext());

        return coursesList;
    }
}
