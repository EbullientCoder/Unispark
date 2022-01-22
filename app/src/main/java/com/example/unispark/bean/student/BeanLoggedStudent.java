package com.example.unispark.bean.student;

import com.example.unispark.bean.login.BeanLoggedUser;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.List;

public class BeanLoggedStudent extends BeanLoggedUser {

    private String firstName;
    private String lastName;
    private String id;
    private String faculty;
    private String academicYear;
    private List<CourseModel> courses;
    private List<BookExamModel> bookedExams;
    private List<VerbalizedExamModel> verbalizedExams;
    private List<VerbalizedExamModel> failedExams;
    private int uniYear;





    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getId() {
        return id;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }


    public List<BookExamModel> getBookedExams() {
        return bookedExams;
    }

    public List<VerbalizedExamModel> getVerbalizedExams() {
        return verbalizedExams;
    }

    public List<VerbalizedExamModel> getFailedExams() {
        return failedExams;
    }

    public int getUniYear() {
        return uniYear;
    }





    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setId(String id) {
        this.id = id;
    }


    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }


    public void setBookedExams(List<BookExamModel> bookedExams) {
        this.bookedExams = bookedExams;
    }

    public void setVerbalizedExams(List<VerbalizedExamModel> verbalizedExams) {
        this.verbalizedExams = verbalizedExams;
    }

    public void setFailedExams(List<VerbalizedExamModel> failedExams) {
        this.failedExams = failedExams;
    }

    public void setUniYear(int uniYear) {
        this.uniYear = uniYear;
    }
}

