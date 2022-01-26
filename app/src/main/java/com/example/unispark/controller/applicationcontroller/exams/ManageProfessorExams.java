package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.ExamAlreadyExists;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.exceptions.ExamNotYetOccured;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.List;

public class ManageProfessorExams extends ManageExams{

    //Professor
    public List<BeanExamType> assignedExams(BeanLoggedProfessor professor){
        try {
            professor.setExams(ExamsFacade.getInstance().getProfessorExams(professor.getCourses()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanBookExams(professor.getExams(), 1);
    }


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
