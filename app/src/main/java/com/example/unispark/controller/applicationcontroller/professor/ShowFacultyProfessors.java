package com.example.unispark.controller.applicationcontroller.professor;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanProfessorDetails;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowFacultyProfessors {
    //Faculty professors
    public List<BeanProfessorDetails> setFacultyProfessors(BeanLoggedStudent student) {
        List<BeanProfessorDetails> beanProfessorDetailsList = new ArrayList<>();
        List<BeanCourse> bCourses = new ArrayList<>();
        List<ProfessorModel> professorsItem;
        try {
            professorsItem = ProfessorDAO.getFacultyProfessors(student.getFaculty());

            for (int i = 0; i < professorsItem.size(); i++){
                beanProfessorDetailsList.add(new BeanProfessorDetails(
                        professorsItem.get(i).getFirstName(),
                        professorsItem.get(i).getLastName(),
                        professorsItem.get(i).getProfilePicture(),
                        professorsItem.get(i).getId(),
                        professorsItem.get(i).getFaculty(),
                        professorsItem.get(i).getWebsite(),
                        getBeanCourses(professorsItem.get(i).getCourses())
                ));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanProfessorDetailsList;

    }

    private List<BeanCourse> getBeanCourses(List<CourseModel> courses){
        List<BeanCourse> beanCourseList = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < courses.size(); i++){
            course = courses.get(i);
            beanCourseList.add(new BeanCourse(course.getId(),
                    course.getShortName(),
                    course.getFullName(),
                    course.getCourseYear(),
                    course.getCfu(),
                    course.getSession(),
                    course.getLink(),
                    course.getFaculty(),
                    course.getUniYear()));
        }
        return beanCourseList;
    }
}
