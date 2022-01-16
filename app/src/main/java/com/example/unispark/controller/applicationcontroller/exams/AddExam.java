package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanBookExam;
import com.example.unispark.bean.login.BeanLoggedProfessor;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.ExamAlreadyExists;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.exams.BookExamModel;

import java.util.List;

public class AddExam {
    //Add Exam
    public void addExam(BeanBookExam bExam, BeanLoggedProfessor professor) throws ExamAlreadyExists, GenericException
    {
        //Adding it into the DB
        BookExamModel exam = new BookExamModel(bExam.getId(), bExam.getName(), bExam.getYear(), bExam.getDate(), bExam.getCFU(), bExam.getClassroom(), bExam.getBuilding());
        try {

            ExamsDAO.addExam(exam);
            List<BookExamModel> exams = professor.getExams();
            exams.add(exam);
            professor.setExams(exams);

        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamAlreadyExists("Exam already exists");
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot add exam, try again");
        }
    }
}
