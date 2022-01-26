package com.example.unispark.controller.applicationcontroller.professor;

import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.professor.BeanProfessorDetails;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.ProfessorDAO;
import com.example.unispark.facade.CourseCreatorFacade;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.ProfessorModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowFacultyProfessors {
    //Faculty professors
    public List<BeanProfessorDetails> setFacultyProfessors(BeanLoggedStudent student) {
        List<BeanProfessorDetails> beanProfessorDetailsList = new ArrayList<>();
        List<ProfessorModel> professorsItem;
        try {
            professorsItem = ProfessorDAO.getFacultyProfessors(student.getFaculty());

            for (int i = 0; i < professorsItem.size(); i++){
               BeanProfessorDetails beanProfessorDetails;
               beanProfessorDetails = new BeanProfessorDetails();
               beanProfessorDetails.setFaculty(professorsItem.get(i).getFaculty());
               beanProfessorDetails.setCourses(this.beanCourses(professorsItem.get(i).getCourses()));
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


    private BeanCourse beanCourse(CourseModel courseModel){
        BeanCourse course;
        course  = new BeanCourse();
        course.setShortName(courseModel.getShortName());
        course.setFullName(courseModel.getFullName());
        course.setCourseYear(courseModel.getCourseYear());
        course.setCfu(courseModel.getCfu());
        course.setFaculty(courseModel.getFaculty());
        course.setId(courseModel.getId());
        course.setLink(courseModel.getLink());
        course.setSession(courseModel.getSession());
        course.setUniYear(courseModel.getUniYear());

        return course;
    }


    private List<BeanCourse> beanCourses(List<CourseModel> listCourses){
        List<BeanCourse> courseList = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < listCourses.size(); i++){
            course = listCourses.get(i);
            courseList.add(this.beanCourse(course));
        }
        return courseList;
    }



}
