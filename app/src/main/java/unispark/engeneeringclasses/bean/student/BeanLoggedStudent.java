package unispark.engeneeringclasses.bean.student;

import unispark.engeneeringclasses.bean.login.BeanLoggedUser;
import unispark.engeneeringclasses.model.CourseModel;
import unispark.engeneeringclasses.model.exams.BookExamModel;
import unispark.engeneeringclasses.model.exams.ExamModel;
import unispark.engeneeringclasses.model.exams.VerbalizedExamModel;

import java.util.List;

public class BeanLoggedStudent extends BeanLoggedUser {

    private String firstName;
    private String lastName;
    private String id;
    private String faculty;
    private String academicYear;
    private int year;
    private List<CourseModel> courses;
    private List<ExamModel> bookedExams;
    private List<ExamModel> verbalizedExams;
    private List<ExamModel> failedExams;




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


    public List<ExamModel> getBookedExams() {
        return bookedExams;
    }

    public List<ExamModel> getVerbalizedExams() {
        return verbalizedExams;
    }

    public List<ExamModel> getFailedExams() {
        return failedExams;
    }

    public int getYear() {
        return year;
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


    public void setBookedExams(List<ExamModel> bookedExams) {
        this.bookedExams = bookedExams;
    }

    public void setVerbalizedExams(List<ExamModel> verbalizedExams) {
        this.verbalizedExams = verbalizedExams;
    }

    public void setFailedExams(List<ExamModel> failedExams) {
        this.failedExams = failedExams;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

