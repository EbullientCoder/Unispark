package unispark.controller.appcontroller.exams;

import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.bean.professor.BeanLoggedProfessor;
import unispark.engeneeringclasses.dao.ExamsDAO;
import unispark.engeneeringclasses.exceptions.ExamAlreadyExists;
import unispark.engeneeringclasses.exceptions.ExamException;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.model.exams.BookExamModel;
import unispark.model.exams.ExamModel;

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
            throw new ExamAlreadyExists(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }


}
