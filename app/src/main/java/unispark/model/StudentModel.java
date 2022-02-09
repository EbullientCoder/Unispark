package unispark.model;

import unispark.model.exams.BookExamModel;
import unispark.model.exams.ExamModel;
import unispark.model.exams.VerbalizedExamModel;

import java.util.List;

public class StudentModel extends UserModel{

    //Attributes
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

    //Methods
    //Constructor


    public StudentModel(String email, int profilePicture, String firstName, String lastName, String id, String faculty, String academicYear, List<CourseModel> courses, List<BookExamModel> bookedExams, List<VerbalizedExamModel> verbalizedExams, List<VerbalizedExamModel> failedExams, int uniYear) {
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

    public List<BookExamModel> getBookedExams() {
        return bookedExams;
    }

    public void setBookedExams(List<BookExamModel> bookedExams) {
        this.bookedExams = bookedExams;
    }

    public List<VerbalizedExamModel> getVerbalizedExams() {
        return verbalizedExams;
    }

    public void setVerbalizedExams(List<VerbalizedExamModel> verbalizedExams) {
        this.verbalizedExams = verbalizedExams;
    }

    public List<VerbalizedExamModel> getFailedExams() {
        return failedExams;
    }

    public void setFailedExams(List<VerbalizedExamModel> failedExams) {
        this.failedExams = failedExams;
    }

    public int getUniYear() {
        return uniYear;
    }

    public void setUniYear(int uniYear) {
        this.uniYear = uniYear;
    }
}
