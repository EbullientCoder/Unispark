package com.example.unispark.model;


import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.FailedExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.List;

public class StudentModel extends UserModel{
    //Attributes

    private int imageID;
    private String firstName;
    private String lastName;
    private String faculty;
    private String academicYear;
    private String id;
    private List<CourseModel> courses;

    //private List<VerbalizedExamModel> vExams;
    //private List<FailedExamModel> fExams;
    //private List<BookExamModel> bExams;

    //Methods
    //Constructor
    public StudentModel(int imageID,
                        String firstName,
                        String lastName,
                        String email,
                        String password,
                        String faculty,
                        String academicYear,
                        String id,
                        List<CourseModel> courses
    ) {
        super(email, password);
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.academicYear = academicYear;
        this.id = id;
        this.courses = courses;
        //this.vExams = vExams;
        //this.fExams = fExams;
        //this.bExams = bExams;
    }

    //Getter
    public int getImageID() {
        return imageID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public String getId() {
        return id;
    }

    public List<CourseModel> getCourses() {
        return courses;
    }


    //Setter
    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
}






