package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.courses.BeanCoursesNames;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.exceptions.CourseAlreadyJoined;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.CourseNeverJoined;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.facade.CourseCreatorFacade;
import com.example.unispark.model.CourseModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageCourses {

    //Remove Course Joined from DB
    public void leaveCourse(BeanLoggedStudent student, BeanCourse bCourse, int position) throws ExamBookedException, GenericException, CourseNeverJoined {
        List<CourseModel> courses = student.getCourses();
        try {
            CourseDAO.leaveCourse(student.getId(), bCourse.getFullName());
            courses.remove(position);
            student.setCourses(courses);

        } catch (SQLException e) {
            e.printStackTrace();
            throw new GenericException("Try again");
        }


    }


    //Join Course
    public void joinCourse(BeanLoggedStudent student, BeanCourse bCourse) throws GenericException, CourseDoesNotExist, CourseAlreadyJoined {
        try {
            CourseDAO.joinCourse(student.getId(), bCourse.getFullName());
            //Add Course to the Student's Joined Courses
            List<CourseModel> joinedCourses = student.getCourses();
            joinedCourses.add(0, new CourseModel(bCourse.getId(),
                    bCourse.getShortName(),
                    bCourse.getFullName(),
                    bCourse.getCourseYear(),
                    bCourse.getCfu(),
                    bCourse.getSession(),
                    bCourse.getLink(),
                    bCourse.getFaculty(),
                    bCourse.getUniYear()));

            student.setCourses(joinedCourses);

        }  catch (SQLException e) {
            e.printStackTrace();
            throw new GenericException("Cannot join course, try again");
        }
    }


    public List<BeanCourse> getAvaliableCourses(BeanLoggedStudent student)  {

        List<CourseModel> avaliableCourses = null;
        try {
            avaliableCourses = CourseDAO.selectAvailableCourses(student.getFaculty(), student.getUniYear(), student.getCourses());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return CourseCreatorFacade.getInstance().listBeanCourses(avaliableCourses);
    }



    //University: Get Courses of its faculty
    public BeanCoursesNames getCoursesNamesByFaculty(List<String> faculties) {
        BeanCoursesNames bCourses = new BeanCoursesNames();
        List<CourseModel> courses;
        List<CourseModel> allCourses = new ArrayList<>();
        List<String> coursesNames = new ArrayList<>();

        try{
            for (int i = 0; i < faculties.size(); i++){
                courses = CourseDAO.selectCourses(faculties.get(i));
                if (!courses.isEmpty()){
                    allCourses.addAll(courses);
                }
            }

            for (int j = 0; j < allCourses.size(); j++){
                coursesNames.add(allCourses.get(j).getFullName());
            }

            bCourses.setCourses(coursesNames);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bCourses;
    }



    //Get Courses
    public List<BeanCourse> getFacultyCourses(List<String> faculties){
        List<BeanCourse> bCourses = new ArrayList<>();
        List<CourseModel> courses;

        try{

            for (int i = 0; i < faculties.size(); i++){
                courses = CourseDAO.selectCourses(faculties.get(i));
                if (!courses.isEmpty()){
                    for (int j = 0; j < courses.size(); j++){
                        bCourses.add(CourseCreatorFacade.getInstance().createBeanCourse(courses.get(j)));
                    }
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return bCourses;
    }



    //Get Student Courses
    public List<BeanCourse> getCourses(BeanLoggedStudent student){
        List<CourseModel> courses = student.getCourses();

        return CourseCreatorFacade.getInstance().listBeanCourses(courses);
    }


    //Get Professor Courses
    public List<BeanCourse> getCourses(BeanLoggedProfessor professor){
        List<CourseModel> courses = null;
        try {
            courses = CourseDAO.selectProfessorCourses(professor.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return CourseCreatorFacade.getInstance().listBeanCourses(courses);
    }

    public List<String> getCoursesNames(BeanLoggedProfessor bProfessor){
        List<String> coursesNames = new ArrayList<>();
        List<CourseModel> courses = null;
        try {
            courses = CourseDAO.selectProfessorCourses(bProfessor.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        for(int i = 0; i < courses.size(); i++) coursesNames.add(courses.get(i).getShortName());

        return coursesNames;
    }


}
