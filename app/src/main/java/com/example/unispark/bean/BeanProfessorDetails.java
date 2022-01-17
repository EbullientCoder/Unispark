package com.example.unispark.bean;



import java.io.Serializable;
import java.util.List;

public class BeanProfessorDetails implements Serializable {

    //Attributes
    private String firstName;
    private String lastName;
    private int profilePicture;
    private int id;
    private String faculty;
    private String website;
    private List<BeanCourse> courses;


    //Methods
    //Constructor


    public BeanProfessorDetails(String firstName, String lastName, int profilePicture, int id, String faculty, String website, List<BeanCourse> courses) {
        this.profilePicture = profilePicture;
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.faculty = faculty;
        this.website = website;
        this.courses = courses;

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

    public int getProfilePicture() {
        return profilePicture;
    }

    public List<BeanCourse> getCourses() {
        return courses;
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


    public void setProfilePicture(int profilePicture) {
        this.profilePicture = profilePicture;
    }

    public void setCourses(List<BeanCourse> courses) {
        this.courses = courses;
    }
}
