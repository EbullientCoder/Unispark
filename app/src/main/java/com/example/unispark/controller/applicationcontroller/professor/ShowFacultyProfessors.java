package com.example.unispark.controller.applicationcontroller.professor;

import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.professor.BeanProfessorDetails;
import com.example.unispark.bean.student.BeanLoggedStudent;
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
               BeanProfessorDetails beanProfessorDetails;
               beanProfessorDetails = new BeanProfessorDetails();
               beanProfessorDetails.setFaculty(professorsItem.get(i).getFaculty());
               beanProfessorDetails.setCourses(getBeanCourses(professorsItem.get(i).getCourses()));
               beanProfessorDetails.setId(professorsItem.get(i).getId());
               beanProfessorDetails.setProfilePicture(professorsItem.get(i).getProfilePicture());
               beanProfessorDetails.setWebsite(professorsItem.get(i).getWebsite());
               beanProfessorDetails.setFirstName(professorsItem.get(i).getFirstName());
               beanProfessorDetails.setLastName(professorsItem.get(i).getLastName());

               beanProfessorDetailsList.add(beanProfessorDetails);
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
            BeanCourse beanCourse;
            beanCourse  = new BeanCourse();
            beanCourse.setShortName(course.getShortName());
            beanCourse.setFullName(course.getFullName());
            beanCourse.setCourseYear(course.getCourseYear());
            beanCourse.setCfu(course.getCfu());
            beanCourse.setFaculty(course.getFaculty());
            beanCourse.setId(course.getId());
            beanCourse.setLink(course.getLink());
            beanCourse.setSession(course.getSession());
            beanCourse.setUniYear(course.getUniYear());
            beanCourseList.add(beanCourse);
        }
        return beanCourseList;
    }
}
