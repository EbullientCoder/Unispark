package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.exams.BeanExam;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.factory.FactoryExams;
import com.example.unispark.model.exams.ExamModel;

import java.sql.SQLException;
import java.util.List;

public class ManageExams {





    //Remove the Connection inside the DB
    public void removeExam(BeanLoggedStudent student, int position) throws GenericException {
        List<ExamModel> bookedExams = student.getBookedExams();
        ExamModel leaveExam = bookedExams.get(position);
        try {
            ExamsDAO.removeBookedExam(leaveExam.getId(), student.getId());
            //Remove the Booked Exam from Student's Attributes
            bookedExams.remove(position);
            student.setBookedExams(bookedExams);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }



    public List<BeanExam> verbalizedExams(BeanLoggedStudent student){
        //Types: 0 = Verbalized - Failed Exam | 1 = Book Exam | 2 = Booked Exam | 3 = Professor Assigned Exam
        try{
            student.setVerbalizedExams(ExamsDAO.getVerbalizedExams(student.getId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return FactoryExams.getInstance().createBeanVerbalizedExams(student.getVerbalizedExams());
    }



    //Page: Failed ExamModel
    public List<BeanExam> failedExams(BeanLoggedStudent student){

        //Types: 0 = Verbalized - Failed Exam | 1 = Book Exam | 2 = Booked Exam | 3 = Professor Assigned Exam
        try{
            student.setFailedExams(ExamsDAO.getFailedExams(student.getId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return FactoryExams.getInstance().createBeanVerbalizedExams(student.getFailedExams());
    }




    //Page: Booked StudentExamsGUIController
    public List<BeanExam> getBookedExams(BeanLoggedStudent student){

        //Types: 0 = Verbalized - Failed Exam | 1 = Book Exam | 2 = Booked Exam | 3 = Professor Assigned Exams
        return FactoryExams.getInstance().createBeanBookExams(student.getBookedExams());
    }


    //Professor
    public List<BeanExam> assignedExams(BeanLoggedProfessor professor){

        //Types: 0 = Verbalized - Failed Exam | 1 = Book Exam | 2 = Booked Exam | 3 = Professor Assigned Exam
        try {
            professor.setExams(ExamsDAO.getProfessorExams(professor.getId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return FactoryExams.getInstance().createBeanBookExams(professor.getExams());
    }



}
