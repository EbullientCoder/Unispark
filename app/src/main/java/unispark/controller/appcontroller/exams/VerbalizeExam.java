package unispark.controller.appcontroller.exams;

import unispark.engeneeringclasses.bean.BeanStudentSignedToExam;
import unispark.engeneeringclasses.bean.exams.BeanBookExam;
import unispark.engeneeringclasses.dao.ExamsDAO;
import unispark.engeneeringclasses.exceptions.ExamException;
import unispark.engeneeringclasses.exceptions.ExamNotYetOccured;
import unispark.engeneeringclasses.exceptions.GenericException;
import unispark.model.exams.VerbalizedExamModel;

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
