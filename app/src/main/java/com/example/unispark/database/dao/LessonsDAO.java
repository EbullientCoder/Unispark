package com.example.unispark.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.unispark.database.others.SQLiteConnection;
import com.example.unispark.database.query.QueryLessons;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LessonModel;

import java.util.ArrayList;
import java.util.List;

public class LessonsDAO {

    private LessonsDAO(){}

    public static boolean addLesson(LessonModel lesson){
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        ContentValues cv = new ContentValues();

        cv.put("lesson", lesson.getLessonName());
        cv.put("day", lesson.getDay());
        cv.put("hour", lesson.getHour());
        long insert = db.insert("lessons", null, cv);

        if (insert == -1) return false;
        else return true;
    }

    public static List<LessonModel> getLessons(String day, List<CourseModel> courses)
    {
        SQLiteDatabase db =  SQLiteConnection.getReadableDB();
        if (courses == null) return null;

        List<LessonModel> lessons = new ArrayList<>();
        Cursor cursorLesson;
        for (int i = 0; i < courses.size(); i++){
            cursorLesson  = QueryLessons.selectLessons(db, day, courses.get(i).getFullName());
            if (cursorLesson.moveToFirst()) {
                //Add lesson to the Lessons' list
                do{
                    lessons.add(new LessonModel(cursorLesson.getString(0), cursorLesson.getString(1), cursorLesson.getString(2)));
                } while(cursorLesson.moveToNext());
            }
            cursorLesson.close();
        }

        db.close();
        return lessons;
    }

    public static boolean removeLesson(LessonModel lesson)
    {
        SQLiteDatabase db = SQLiteConnection.getWritableDB();
        int delete = db.delete("lessons","lesson='" + lesson.getLessonName() + "' and day=" + lesson.getDay() + "' and hour=" + lesson.getHour(),null);
        if (delete > 0) return true;
        return false;
    }
}
