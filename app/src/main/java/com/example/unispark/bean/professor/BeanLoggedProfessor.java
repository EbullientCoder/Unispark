package com.example.unispark.bean.professor;

import com.example.unispark.model.CourseModel;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.exams.BookExamModel;

import java.util.List;

public class BeanLoggedProfessor extends BeanProfessor{

    //Attributes

    private List<CourseModel> courses;
    private List<BookExamModel> exams;
    private List<HomeworkModel> homeworks;


    public List<CourseModel> getCourses() {
        return courses;
    }

    public List<BookExamModel> getExams() {
        return exams;
    }

    public List<HomeworkModel> getHomeworks() {
        return homeworks;
    }



    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    public void setExams(List<BookExamModel> exams) {
        this.exams = exams;
    }

    public void setHomeworks(List<HomeworkModel> homeworks) {
        this.homeworks = homeworks;
    }
}
