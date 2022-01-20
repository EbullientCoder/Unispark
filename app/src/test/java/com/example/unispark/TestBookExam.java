package com.example.unispark;

import org.junit.Test;

import static org.junit.Assert.*;

import android.os.StrictMode;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.controller.applicationcontroller.exams.BookExam;
import com.example.unispark.controller.applicationcontroller.exams.VerbalizeExam;
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
 *
 */

public class TestBookExam {


    @Test
    public void testBookExam(){
        
        int code = 0;

        BeanLoggedStudent beanStudent;
        BeanBookExam exam;
        beanStudent = new BeanLoggedStudent("testname", "testlastname", 1,
                "0000000", "faculty", "2021/2022", null, null, 3);

        exam = new BeanBookExam(1, "testexam", "2021/2022",
                "2022-03-03", "12.0", "A4", "ING.TEST");
        BookExam controller;
        controller = new BookExam();

        try{
            controller.bookExam(beanStudent, exam);
            //SUCCEESS
            code = 1;
        } catch (GenericException | ExamAlreadyVerbalized e) {
            e.printStackTrace();
            code = 0;
        }

        assertEquals(1, code);


    }
}
