package com.example.common.model;

import com.example.common.model.exams.BookExamModel;
import com.example.common.model.exams.ExamModel;
import com.example.common.model.exams.VerbalizedExamModel;

import java.util.List;

public class StudentModel extends UserModel{

    //Attributes
    private String firstName;
    private String lastName;
    private String id;
    private String faculty;
    private String academicYear;
    private List<CourseModel> courses;
    private List<ExamModel> bookedExams;
    private List<ExamModel> verbalizedExams;
    private List<ExamModel> failedExams;
    private int uniYear;

    //Methods
    //Constructor


    public StudentModel(String email, int profilePicture, String firstName, String lastName, String id, String faculty, String academicYear, List<CourseModel> courses, List<ExamModel> bookedExams, List<ExamModel> verbalizedExams, List<ExamModel> failedExams, int uniYear) {
        super(email, profilePicture);
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.faculty = faculty;
        this.academicYear = academicYear;
        this.courses = courses;
        this.bookedExams = bookedExams;
        this.verbalizedExams = verbalizedExams;
        this.failedExams = failedExams;
        this.uniYear = uniYear;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }

    public List<ExamModel> getBookedExams() {
        return bookedExams;
    }

    public void setBookedExams(List<ExamModel> bookedExams) {
        this.bookedExams = bookedExams;
    }

    public List<ExamModel> getVerbalizedExams() {
        return verbalizedExams;
    }

    public void setVerbalizedExams(List<ExamModel> verbalizedExams) {
        this.verbalizedExams = verbalizedExams;
    }

    public List<ExamModel> getFailedExams() {
        return failedExams;
    }

    public void setFailedExams(List<ExamModel> failedExams) {
        this.failedExams = failedExams;
    }

    public int getUniYear() {
        return uniYear;
    }

    public void setUniYear(int uniYear) {
        this.uniYear = uniYear;
    }
}
