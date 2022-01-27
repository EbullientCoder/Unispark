package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.List;

public class VerbalizeExam {

    //Verbalize Exam
    public void verbalizeExam(BeanBookExam exam, BeanStudentSignedToExam student, String result) throws ExamNotYetOccured, GenericException {
        //Create new Verbalized Exam
        VerbalizedExamModel vExam = new VerbalizedExamModel(exam.getId(), exam.getName(), exam.getDate(), exam.getDate(), exam.getCfu(), result);

        //Add Verbalized Exam to the DB
        try {
            ExamsDAO.addExamGrade(vExam, student.getId());
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamNotYetOccured("Exam has not yet occured");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }

    }



    //Show the Students that have booked an Exam
    public List<BeanStudentSignedToExam> showBookedStudents(BeanBookExam exam){
        List<BeanStudentSignedToExam> studentsItem = null;
        try {
            studentsItem = ExamsDAO.getStudentsBookedExam(exam.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studentsItem;

    }
}
