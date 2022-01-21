package com.example.unispark;


import static org.junit.Assert.assertEquals;

import com.example.unispark.bean.courses.BeanCourse;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.bean.login.BeanUser;
import com.example.unispark.controller.applicationcontroller.Login;
import com.example.unispark.controller.applicationcontroller.course.ManageCourses;
import com.example.unispark.exceptions.CourseAlreadyJoined;
import com.example.unispark.exceptions.CourseDoesNotExist;
import com.example.unispark.exceptions.CourseNeverJoined;
import com.example.unispark.exceptions.ExamBookedException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.exceptions.WrongUsernameOrPasswordException;

import org.junit.Test;

import java.sql.SQLException;


/**
 *
 *
 * @author Emanuele Valzano
 *
 *
 *
 */
public class TestManageCourses {



    @Test
    public void testCourseDoesNotExist() throws WrongUsernameOrPasswordException, SQLException {
        int code = 1;

        // Create a student
        Login loginController;
        loginController = new Login();
        BeanLoggedStudent beanStudent;
        beanStudent = loginController.studentLogin(new BeanUser("andrea.lapiana", "password"));

        // Create a course that does not exist
        BeanCourse beanCourse;
        beanCourse = new BeanCourse("1", "TEST", "TEST COURSE",
                "2021/2022", "12.0", "Winter", "https://testcourse.com",
                "Ingegneria Informatica", 1);


        ManageCourses controller;
        controller = new ManageCourses();
        try{
            controller.joinCourse(beanStudent, beanCourse);
            code = 2;
        } catch (GenericException | CourseAlreadyJoined e) {
            e.printStackTrace();
            code = 3;
        } catch (CourseDoesNotExist courseDoesNotExist) {
            //SUCCESS
            courseDoesNotExist.printStackTrace();
            code = 0;
        }

        assertEquals(0, code);
    }





    @Test
    public void testCourseAlreadyJoined() throws WrongUsernameOrPasswordException, SQLException {
        int code = 1;

        // Create a student
        Login loginController;
        loginController = new Login();
        BeanLoggedStudent beanStudent;
        beanStudent = loginController.studentLogin(new BeanUser("mario.rossi", "password"));

        //Create a course already joined by the student
        BeanCourse beanCourse;
        beanCourse = new BeanCourse("4", "MAT", "MATEMATICA GENERALE",
                "2021/2022", "12.0", "Winter", "https://economia.uniroma2.it/cdl/triennio/clem/corso/206/",
                "Economia", 1);

        ManageCourses controller;
        controller = new ManageCourses();
        try{
            controller.joinCourse(beanStudent, beanCourse);
            code = 2;
        } catch (GenericException | CourseDoesNotExist e) {
            e.printStackTrace();
            code = 3;
        } catch (CourseAlreadyJoined courseAlreadyJoined) {
            //SUCCESS
            courseAlreadyJoined.printStackTrace();
            code = 0;
        }

        assertEquals(0, code);
    }






    @Test
    public void testExamBookedCannotLeaveCourse() throws WrongUsernameOrPasswordException, SQLException {
        int code = 1;

        // Create a student
        Login loginController;
        loginController = new Login();
        BeanLoggedStudent beanStudent;
        beanStudent = loginController.studentLogin(new BeanUser("mario.rossi", "password"));

        //Create a course that is associated to the exam the student has booked
        BeanCourse beanCourse;
        beanCourse = new BeanCourse("4", "MAT", "MATEMATICA GENERALE",
                "2021/2022", "12.0", "Winter", "https://economia.uniroma2.it/cdl/triennio/clem/corso/206/",
                "Economia", 1);


        int position = 0;
        for (int i = 0; i < beanStudent.getCourses().size(); i++){
            if (beanStudent.getCourses().get(i).getFullName().equals(beanCourse.getFullName())){
                position = i;
                break;
            }
        }

        ManageCourses controller;
        controller = new ManageCourses();
        try {
            controller.leaveCourse(beanStudent, beanCourse, position);
            code = 2;
        } catch (GenericException | CourseNeverJoined e) {
            e.printStackTrace();
            code = 3;
        } catch (ExamBookedException e) {
            //SUCCESS
            e.printStackTrace();
            code = 0;
        }

        assertEquals(0, code);

    }

}
