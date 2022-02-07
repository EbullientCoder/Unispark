package com.example.common.applicationcontroller.exams;

import com.example.common.bean.BeanStudentSignedToExam;
import com.example.common.bean.exams.BeanBookExam;
import com.example.common.database.dao.ExamsDAO;
import com.example.common.exceptions.ExamException;
import com.example.common.exceptions.ExamNotYetOccured;
import com.example.common.exceptions.GenericException;
import com.example.common.model.exams.VerbalizedExamModel;

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
            throw new ExamNotYetOccured(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }


    //Show the Students that have booked an Exam
    public List<BeanStudentSignedToExam> getStudentsVerbalizeExam(BeanBookExam exam){
        List<BeanStudentSignedToExam> studentsItem = null;
        try {
            studentsItem = ExamsDAO.getStudentsBookedExam(exam.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return studentsItem;

    }

}
