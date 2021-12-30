package com.example.unispark.provaDB;



import com.example.unispark.provaDB.exams.BookExamModel;

import java.util.List;

public class ProfessorModel extends UserModel{
    //Attributes
    private int id;
    private String firstName;
    private String lastName;
    private String website;
    private int image;
    private List<CourseModel> courses;
    private String faculty;
    private List<BookExamModel> exams;


    //Methods
    //Constructor
    public ProfessorModel(String email, String password){
        super(email, password);
    }

    public ProfessorModel(String email, String password, int id, String firstName, String lastName, String website, int image, List<CourseModel> courses, String faculty, List<BookExamModel> exams) {
        super(email, password);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.website = website;
        this.image = image;
        this.courses = courses;
        this.faculty = faculty;
        this.exams = exams;
    }

    //Getter
    public int getId() {
        return id;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getWebsite() {
        return website;
    }
    public int getImage() {
        return image;
    }
    public List<CourseModel> getCourses() {
        return courses;
    }
    public String getFaculty(){ return faculty; }
    public List<BookExamModel> getExams() {
        return exams;
    }

    //Setter
    public void setId(int id) {
        this.id = id;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setWebsite(String website) {
        this.website = website;
    }
    public void setImage(int image) {
        this.image = image;
    }
    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
    public void setFaculty(String faculty) { this.faculty = faculty; }
    public void setExams(List<BookExamModel> exams) {
        this.exams = exams;
    }
}



