package com.example.unispark.model;

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
    private int uniYear;



    public CourseModel(String id, String courseYear, String cfu, String session, String link, String faculty, int uniYear) {
        this.id = id;
        this.courseYear = courseYear;
        this.cfu = cfu;
        this.session = session;
        this.link = link;
        this.faculty = faculty;
        this.uniYear = uniYear;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCourseYear() {
        return courseYear;
    }

    public void setCourseYear(String courseYear) {
        this.courseYear = courseYear;
    }

    public String getCfu() {
        return cfu;
    }

    public void setCfu(String cfu) {
        this.cfu = cfu;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public int getUniYear() {
        return uniYear;
    }

    public void setUniYear(int uniYear) {
        this.uniYear = uniYear;
    }
}
