package com.example.common.applicationcontroller.exams;

import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.professor.BeanLoggedProfessor;
import com.example.common.database.dao.ExamsDAO;
import com.example.common.exceptions.ExamAlreadyExists;
import com.example.common.exceptions.ExamException;
import com.example.common.exceptions.GenericException;
import com.example.common.model.exams.BookExamModel;
import com.example.common.model.exams.ExamModel;

import java.sql.SQLException;
import java.util.List;

public class AddExamAppController {
    //Add Exam
    public void addExam(BeanBookExam bExam, BeanLoggedProfessor professor) throws ExamAlreadyExists, GenericException
    {
        //Adding it into the DB
        BookExamModel exam = new BookExamModel(bExam.getId(), bExam.getName(), bExam.getYear(), bExam.getDate(), bExam.getCfu(), bExam.getClassroom(), bExam.getBuilding());
        try {
            ExamsDAO.addExam(exam);
            List<ExamModel> professorExams = professor.getExams();
            professorExams.add(exam);
            professor.setExams(professorExams);
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamAlreadyExists(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }


}
