package com.example.unispark.model;

import com.example.unispark.model.exams.BookExamModel;

import java.util.List;

public class ProfessorModel extends UserModel{
    //Attributes
    private String firstName;
    private String lastName;
    private int id;
    private String faculty;
    private String website;
    private List<CourseModel> courses;
    private List<BookExamModel> exams;

    //Methods
    //Constructor


    public ProfessorModel(String firstName, String lastName, String email, int profilePicture, int id, String faculty, String website, List<CourseModel> courses, List<BookExamModel> exams) {
        super(email, profilePicture);
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.faculty = faculty;
        this.website = website;
        this.courses = courses;
        this.exams = exams;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getWebsite() {
        return website;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }

    public List<BookExamModel> getExams() {
        return exams;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    public void setExams(List<BookExamModel> exams) {
        this.exams = exams;
    }
}



