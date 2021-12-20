package com.example.unispark.model;

import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.FailedExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.List;

public class StudentModel extends UserModel{
    //Attributes
    private int id;
    private int imageID;
    private String firstName;
    private String lastName;
    private String faculty;
    private String AA;
    private List<CourseModel> courses;
    private List<VerbalizedExamModel> vExams;
    private List<FailedExamModel> fExams;
    private List<BookExamModel> bExams;

    //Methods
    //Constructor
    public StudentModel(int imageID,
                        String firstName,
                        String lastName,
                        String email,
                        String password,
                        List<CourseModel> courses
                        ) {
        super(email, password);
        //this.id = id;
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        //this.AA = AA;
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
    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
}



