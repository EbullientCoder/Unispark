package com.example.unispark.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryCourse;
import com.example.unispark.database.query.QueryLogin;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;


import java.util.ArrayList;
import java.util.List;

public class ProfessorDAO {

    private ProfessorDAO(){}

    //Get a ProfessorModel using the email and password
    public static ProfessorModel selectProfessor(String email, String password) //throws exception
    {
        //Get a readable SQLiteDatabase
        SQLiteDatabase db = SQLiteConnection.getReadableDB();

        ProfessorModel professor;
        //Professor's attributes
        int professorId;
        String firtName;
        String lastName;
        String emailProfessor;
        String passwordProfessor;
        String website;
        int image;
        String faculty;

        //Get all professor's attributes from the Database using email and password
        Cursor cursor = QueryLogin.loginProfessor(db, email, password);

        if (!cursor.moveToFirst()){
            //throw exception

            //Momentaneo
            ProfessorModel professorerror = new ProfessorModel(null,
                    null,
                    0,
                    null,
                    null,
                    "",
                    0,
                    null, null);
            return professorerror;
        }
        professorId = cursor.getInt(0);
        firtName = cursor.getString(1);
        lastName = cursor.getString(2);
        emailProfessor = cursor.getString(3);
        passwordProfessor = cursor.getString(4);
        website = cursor.getString(5);
        image = cursor.getInt(6);
        faculty = cursor.getString(7);

        //List of courses kept by professor
        List<CourseModel> coursesList = new ArrayList<>();

        //Course attributes
        CourseModel course;
        String courseId;
        String shortName;
        String fullName;
        String courseYear;
        String cfu;
        String session;
        String link;

        //Select all courses marked by professor id
        cursor = QueryCourse.selectProfessorCourses(db, professorId);

        if (!cursor.moveToFirst()){
            //throw exception
            coursesList = null;
        }
        else{
            do{
                courseId = String.valueOf(cursor.getInt(7));
                shortName = cursor.getString(1);
                fullName = cursor.getString(2);
                courseYear = cursor.getString(3);
                cfu = cursor.getString(4);
                session = cursor.getString(5);
                link = cursor.getString(6);

                //Create a new course and add it to the professor's course list
                course = new CourseModel(courseId, shortName, fullName, courseYear, cfu, session, link);
                coursesList.add(course);

            } while (cursor.moveToNext());
        }



        //Create the professor instance
        professor = new ProfessorModel(emailProfessor,
                passwordProfessor,
                professorId,
                firtName,
                lastName,
                website,
                image,
                coursesList,
                faculty);

        //close both the cursor and the db when done.
        cursor.close();
        db.close();

        return professor;
    }
}
