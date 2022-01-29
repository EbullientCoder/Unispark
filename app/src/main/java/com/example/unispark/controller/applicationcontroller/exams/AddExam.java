package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.ExamAlreadyExists;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.exams.BookExamModel;

import java.sql.SQLException;
import java.util.List;

public class AddExam {
    //Add Exam
    public void addExam(BeanBookExam bExam, BeanLoggedProfessor professor) throws ExamAlreadyExists, GenericException
    {
        //Adding it into the DB
        BookExamModel exam = new BookExamModel(bExam.getId(), bExam.getName(), bExam.getYear(), bExam.getDate(), bExam.getCfu(), bExam.getClassroom(), bExam.getBuilding());
        try {
            ExamsDAO.addExam(exam);
            List<BookExamModel> professorExams = professor.getExams();
            professorExams.add(exam);
            professor.setExams(professorExams);
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamAlreadyExists("Exam already exists");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }


}
