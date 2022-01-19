package com.example.unispark.controller.applicationcontroller.course;

import com.example.unispark.bean.BeanCourse;
import com.example.unispark.bean.BeanCoursesNames;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.CourseModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenageCourses {

    //Remove Course Joined from DB
    public void leaveCourse(BeanLoggedStudent student, BeanCourse bCourse, int position) throws ExamBookedException, GenericException {
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
    public void joinCourse(BeanLoggedStudent student, BeanCourse bCourse) throws GenericException {
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

        List<BeanCourse> bCourses = new ArrayList<>();

        List<CourseModel> avaliableCourses;
        try {
            avaliableCourses = CourseDAO.selectAvailableCourses(student.getFaculty(), student.getUniYear(), student.getCourses());
            CourseModel course;
            for (int i = 0; i < avaliableCourses.size(); i++){
                course = avaliableCourses.get(i);
                bCourses.add(new BeanCourse(course.getId(),
                        course.getShortName(),
                        course.getFullName(),
                        course.getCourseYear(),
                        course.getCfu(),
                        course.getSession(),
                        course.getLink(),
                        course.getFaculty(),
                        course.getUniYear()));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return bCourses;
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
                        bCourses.add(new BeanCourse(courses.get(j).getId(),
                                courses.get(j).getShortName(),
                                courses.get(j).getFullName(),
                                courses.get(j).getCourseYear(),
                                courses.get(j).getCfu(),
                                courses.get(j).getSession(),
                                courses.get(j).getLink(),
                                courses.get(j).getFaculty(),
                                courses.get(j).getUniYear()));
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
        List<BeanCourse> bCourses = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < courses.size(); i++){
            course = courses.get(i);
            bCourses.add(new BeanCourse(course.getId(),
                    course.getShortName(),
                    course.getFullName(),
                    course.getCourseYear(),
                    course.getCfu(),
                    course.getSession(),
                    course.getLink(),
                    course.getFaculty(),
                    course.getUniYear()));
        }

        return bCourses;
    }

    //Get Professor Courses
    public List<BeanCourse> getCourses(BeanLoggedProfessor professor){
        List<CourseModel> courses = professor.getCourses();
        List<BeanCourse> bCourses = new ArrayList<>();
        CourseModel course;
        for (int i = 0; i < courses.size(); i++){
            course = courses.get(i);
            bCourses.add(new BeanCourse(course.getId(),
                    course.getShortName(),
                    course.getFullName(),
                    course.getCourseYear(),
                    course.getCfu(),
                    course.getSession(),
                    course.getLink(),
                    course.getFaculty(),
                    course.getUniYear()));
        }

        return bCourses;
    }

    public List<String> getCoursesNames(BeanLoggedProfessor bProfessor){
        List<String> coursesNames = new ArrayList<>();
        List<CourseModel> courses = bProfessor.getCourses();

        for(int i = 0; i < courses.size(); i++) coursesNames.add(courses.get(i).getShortName());

        return coursesNames;
    }
}
