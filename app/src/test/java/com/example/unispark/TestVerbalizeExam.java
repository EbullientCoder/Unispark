package com.example.unispark;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.controller.applicationcontroller.exams.VerbalizeExam;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;

/**
 *
 *
 * @author Emanuele Valzano
 *
 *
 */

public class TestVerbalizeExam {


    @Test
    public void testVerbalizeExamNotAssigned(){
        VerbalizeExam verbalizeExamController = new VerbalizeExam();
        int code;

        BeanStudentSignedToExam beanStudent;
        BeanBookExam testExam;
        beanStudent = new BeanStudentSignedToExam("0000000", "Test name");
        testExam = new BeanBookExam(1, "Test Exam", "2021/2022", "2023-01-01", "12.0", "Test Classroom", "Test Building");

        try {

            verbalizeExamController.verbalizeExam(testExam, beanStudent, "27");
            code = 1;
        } catch (ExamNotYetOccured examNotYetOccured) {
            examNotYetOccured.printStackTrace();
            code = 0;
        } catch (GenericException e) {
            e.printStackTrace();
            code = 1;
        }

        assertEquals(1, code);

    }
}
