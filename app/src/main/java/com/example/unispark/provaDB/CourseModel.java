package com.example.unispark.provaDB;

import java.io.Serializable;

public class CourseModel implements Serializable {
    //Attributes
    private String id;
    private String shortName;
    private String fullName;
    private String courseYear;
    private String cfu;
    private String session;
    private String link;
    private String faculty;


    //Methods
    //Constructor
    public CourseModel(){}

    public CourseModel(String id, String shortName, String fullName, String courseYear, String cfu, String session, String link, String faculty) {
        this.id = id;
        this.shortName = shortName;
        this.fullName = fullName;
        this.courseYear = courseYear;
        this.cfu = cfu;
        this.session = session;
        this.link = link;
        this.faculty = faculty;
    }


    //Getter
    public String getId() {
        return id;
    }
    public String getShortName() {
        return shortName;
    }
    public String getFullName() {
        return fullName;
    }
    public String getCourseYear() {
        return courseYear;
    }
    public String getCfu() {
        return cfu;
    }
    public String getSession() {
        return session;
    }
    public String getLink() {
        return link;
    }
    public String getFaculty() {
        return faculty;
    }

    //Setter
    public void setId(String id) {
        this.id = id;
    }
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }
    public void setCfu(String cfu) {
        this.cfu = cfu;
    }
    public void setSession(String session) {
        this.session = session;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
