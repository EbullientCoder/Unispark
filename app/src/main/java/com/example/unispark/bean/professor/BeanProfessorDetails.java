package com.example.unispark.bean.professor;



import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedUser;

import java.io.Serializable;
import java.util.List;

public class BeanProfessorDetails extends BeanLoggedProfessor implements Serializable {


    private List<BeanCourse> courses;


    public List<BeanCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<BeanCourse> courses) {
        this.courses = courses;
    }
}
