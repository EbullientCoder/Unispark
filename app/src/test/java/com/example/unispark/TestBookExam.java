package com.example.unispark;

import org.junit.Test;

import static org.junit.Assert.*;

import android.os.StrictMode;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.exams.AddExam;
import com.example.unispark.controller.applicationcontroller.exams.BookExam;
import com.example.unispark.controller.applicationcontroller.exams.VerbalizeExam;
import com.example.unispark.exceptions.ExamAlreadyExists;
import com.example.unispark.exceptions.ExamAlreadyVerbalized;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * @author Emanuele Valzano
 *
 * This test aims to check wether the use case book exam
 * is implemented correcly by the controller class
 *
 *
 */

public class TestBookExam {




    @Test
    public void testBookExam(){


        //Create the course associated to the exam to book
        List<CourseModel> courses;
        courses = new ArrayList<>();
        CourseModel course;
        course = new CourseModel("1", "TEST", "TEST COURSE",
                "2021/2022", "12.0", "Winter", "https://testcourse.com",
                "Test faculty", 3);
        courses.add(course);


        //Add exam
        BeanBookExam bookExam;
        bookExam = new BeanBookExam(1, "TEST COURSE", "2021/2022",
                "2022-02-03", "12.0", "A4", "ING.TEST");


        BeanLoggedProfessor professor;
        professor = new BeanLoggedProfessor("TestNameProfessor",
                "TestLastNameProfessor", 1, 1, "Test faculty", "Test website", courses);

        AddExam addExamController;
        addExamController = new AddExam();

        try {
            addExamController.addExam(bookExam, professor);
        } catch (ExamAlreadyExists | GenericException e) {
            e.printStackTrace();
        }


        //Create an initial empty booked exam list for the student
        List<BookExamModel> examList;
        examList = new ArrayList<>();

        //Create student bean and fill it with the booked exams list necessary for the book exam test
        BeanLoggedStudent beanStudent;
        beanStudent = new BeanLoggedStudent("TestName", "TestLastname", 2,
                "0000000", "Test faculty", "2021/2022", courses, examList, 3);



        //Test BookExam
        int code = 0;

        BookExam controller;
        controller = new BookExam();

        try{
            controller.bookExam(beanStudent, bookExam);
            //SUCCEESS
            code = 1;
        } catch (GenericException | ExamAlreadyVerbalized e) {
            e.printStackTrace();
            code = 0;
        }
        assertEquals(1, code);
    }



    @Test
    public void testExamAlreadyVerbalized(){

        //Create the course associated to the exam to book
        List<CourseModel> courses;
        courses = new ArrayList<>();
        CourseModel course;
        course = new CourseModel("2", "TEST2", "TEST2 COURSE",
                "2021/2022", "12.0", "Winter", "https://test2course.com",
                "Test2 faculty", 3);
        courses.add(course);


        //Add exam
        BeanBookExam bookExam;
        bookExam = new BeanBookExam(2, "TEST2 COURSE", "2021/2022",
                "2022-02-03", "12.0", "A4", "ING.TEST2");


        BeanLoggedProfessor professor;
        professor = new BeanLoggedProfessor("Test2NameProfessor",
                "Test2LastNameProfessor", 3, 2, "Test2 faculty", "Test2 website", courses);

        AddExam addExamController;
        addExamController = new AddExam();

        try {
            addExamController.addExam(bookExam, professor);
        } catch (ExamAlreadyExists | GenericException e) {
            e.printStackTrace();
        }


        //Create an initial empty booked exam list for the student
        List<BookExamModel> examList;
        examList = new ArrayList<>();

        //Create student bean and fill it with the booked exams list necessary for the book exam test
        BeanLoggedStudent beanStudent;
        beanStudent = new BeanLoggedStudent("Test2Name", "Test2Lastname", 4,
                "1111111", "Test2 faculty", "2021/2022", courses, examList, 3);

        //Verbalize exam before trying to book it
        VerbalizeExam




    }







}
