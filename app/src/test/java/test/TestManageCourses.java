package test;


import static org.junit.Assert.assertEquals;

import unispark.engeneeringclasses.applicationcontroller.LoginAppController;
import unispark.engeneeringclasses.bean.courses.BeanCourse;
import unispark.engeneeringclasses.bean.student.BeanLoggedStudent;
import unispark.engeneeringclasses.bean.login.BeanUser;
import unispark.engeneeringclasses.applicationcontroller.course.ManageCourses;
import unispark.engeneeringclasses.exceptions.CourseAlreadyJoined;
import unispark.engeneeringclasses.exceptions.CourseDoesNotExist;
import unispark.engeneeringclasses.exceptions.CourseNeverJoined;
import unispark.engeneeringclasses.exceptions.ExamBookedException;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.engeneeringclasses.exceptions.WrongUsernameOrPasswordException;

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

    private static String password = "password";

    @Test
    public void testJoinCourseDoesNotExist() throws WrongUsernameOrPasswordException, SQLException {
        int code = 1;

        // Create a student
        LoginAppController loginController;
        loginController = new LoginAppController();

        // Create a course that does not exist
        BeanCourse beanCourse = createBean("https://testcourse.com",
                "1",
                "12.0",
                "TEST COURSE",
                "TEST",
                "Ingegneria Informatica");

        BeanLoggedStudent beanStudent;
        BeanUser beanUser;
        beanUser = new BeanUser();
        beanUser.setEmail("andrea.lapiana");
        beanUser.setPassword(password);
        beanStudent = loginController.studentLogin(beanUser);



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
    public void testJoinCourseAlreadyJoined() throws WrongUsernameOrPasswordException, SQLException {
        int code = 1;

        // Create a student
        LoginAppController loginController;
        loginController = new LoginAppController();
        BeanLoggedStudent beanStudent;
        BeanUser beanUser;
        beanUser = new BeanUser();
        beanUser.setEmail("mario.rossi");
        beanUser.setPassword(password);
        beanStudent = loginController.studentLogin(beanUser);

        //Create a course already joined by the student
        BeanCourse beanCourse = createBean("https://economia.uniroma2.it/cdl/triennio/clem/corso/206/",
                "4",
                "12.0",
                "MATEMATICA GENERALE",
                "MAT",
                "Economia");


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
    public void testLeaveCourseExamBooked() throws WrongUsernameOrPasswordException, SQLException {
        int code = 1;

        //Create a course that is associated to the exam the student has booked
        BeanCourse beanCourse = createBean("https://economia.uniroma2.it/cdl/triennio/clem/corso/206/",
                "4",
                "12.0",
                "MATEMATICA GENERALE",
                "MAT",
                "Economia");

        // Create a student
        LoginAppController loginController;
        loginController = new LoginAppController();
        BeanLoggedStudent beanStudent;
        BeanUser beanUser;
        beanUser = new BeanUser();
        beanUser.setEmail("mario.rossi");
        beanUser.setPassword(password);
        beanStudent = loginController.studentLogin(beanUser);


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



    //Create the Course Bean
    private static BeanCourse createBean(String link, String id, String cfu, String fullName, String shortName, String faculty){
        BeanCourse beanCourse = new BeanCourse();
        beanCourse.setUniYear(1);
        beanCourse.setCourseYear("2021/2022");
        beanCourse.setSession("Winter");
        beanCourse.setLink(link);
        beanCourse.setId(id);
        beanCourse.setCfu(cfu);
        beanCourse.setFullName(fullName);
        beanCourse.setShortName(shortName);
        beanCourse.setFaculty(faculty);

        return beanCourse;
    }
}
