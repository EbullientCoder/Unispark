package com.example.common.bean.professor;

import com.example.common.bean.login.BeanLoggedUser;
import com.example.common.model.CourseModel;
import com.example.common.model.HomeworkModel;
import com.example.common.model.exams.BookExamModel;
import com.example.common.model.exams.ExamModel;

import java.util.List;

public class BeanLoggedProfessor extends BeanProfessor {

    //Attributes
    private List<CourseModel> courses;
    private List<ExamModel> exams;
    private List<HomeworkModel> homeworks;

    //Getter
    public List<CourseModel> getCourses() {
        return courses;
    }
    public List<ExamModel> getExams() {
        return exams;
    }
    public List<HomeworkModel> getHomeworks() {
        return homeworks;
    }

    //Setter
    public void setCourses(List<CourseModel> courses) {
        this.courses = courses;
    }
    public void setExams(List<ExamModel> exams) {
        this.exams = exams;
    }
    public void setHomeworks(List<HomeworkModel> homeworks) {
        this.homeworks = homeworks;
    }
}
