package com.example.unispark.provaDB;





import com.example.unispark.provaDB.exams.BookExamModel;
import com.example.unispark.provaDB.exams.VerbalizedExamModel;

import java.util.List;

public class StudentModel extends UserModel{
    //Attributes

    private int uniYear;
    private int imageID;
    private String firstName;
    private String lastName;
    private String faculty;
    private String academicYear;
    private String id;
    private List<CourseModel> courses;
    private List<BookExamModel> bExams;
    private List<BookExamModel> upcomingExams;
    private List<VerbalizedExamModel> vExams;
    private List<VerbalizedExamModel> fExams;


    //Methods
    //Constructor
    public StudentModel(int uniYear,
                        int imageID,
                        String firstName,
                        String lastName,
                        String email,
                        String password,
                        String faculty,
                        String academicYear,
                        String id,
                        List<CourseModel> courses,
                        List<BookExamModel> bExams,
                        List<BookExamModel> upcomingExams,
                        List<VerbalizedExamModel> vExams,
                        List<VerbalizedExamModel> fExams
    ) {
        super(email, password);
        this.uniYear = uniYear;
        this.imageID = imageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.faculty = faculty;
        this.academicYear = academicYear;
        this.id = id;
        this.courses = courses;
        this.bExams = bExams;
        this.upcomingExams = upcomingExams;
        this.vExams = vExams;
        this.fExams = fExams;

    }

    //Getter


    public int getUniYear() {
        return uniYear;
    }

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

    public List<BookExamModel> getbExams() {
        return bExams;
    }

    public List<BookExamModel> getUpcomingExams() {
        return upcomingExams;
    }

    public List<VerbalizedExamModel> getvExams() {
        return vExams;
    }

    public List<VerbalizedExamModel> getfExams() {
        return fExams;
    }

    //Setter


    public void setUniversityYear(int uniYear) {
        this.uniYear = uniYear;
    }

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

    public void setbExams(List<BookExamModel> bExams) {
        this.bExams = bExams;
    }

    public void setUpcomingExams(List<BookExamModel> upcomingExams) {
        this.upcomingExams = upcomingExams;
    }

    public void setvExams(List<VerbalizedExamModel> vExams) {
        this.vExams = vExams;
    }

    public void setfExams(List<VerbalizedExamModel> fExams) {
        this.fExams = fExams;
    }
}






