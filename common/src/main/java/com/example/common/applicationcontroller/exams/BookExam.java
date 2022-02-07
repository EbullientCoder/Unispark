package com.example.common.applicationcontroller.exams;

import com.example.common.bean.exams.BeanBookExam;
import com.example.common.bean.exams.BeanExam;
import com.example.common.bean.student.BeanLoggedStudent;
import com.example.common.database.dao.ExamsDAO;
import com.example.common.exceptions.ExamAlreadyVerbalized;
import com.example.common.exceptions.ExamException;
import com.example.common.exceptions.GenericException;
import com.example.common.facade.ExamsFacade;
import com.example.common.factory.FactoryExams;
import com.example.common.model.CourseModel;
import com.example.common.model.exams.BookExamModel;
import com.example.common.model.exams.ExamModel;

import java.sql.SQLException;
import java.util.List;

public class BookExam {

    public void bookExam(BeanLoggedStudent student, BeanBookExam exam) throws ExamAlreadyVerbalized, GenericException
    {

        List<ExamModel> exams = student.getBookedExams();
        BookExamModel bookExam = new BookExamModel(exam.getId(), exam.getName(), exam.getYear(), exam.getDate(), exam.getCfu(), exam.getClassroom(), exam.getBuilding());
        try {
            ExamsDAO.bookExam(bookExam, student.getId());
            exams.add(bookExam);
            student.setBookedExams(exams);
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamAlreadyVerbalized(e.getMessage());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }



    public List<BeanExam> generateBookingExams(BeanLoggedStudent student){
        List<ExamModel> bookExams = null;

        try{
            List<ExamModel> bookedExams = student.getBookedExams();
            List<CourseModel> studentCourses = student.getCourses();
            bookExams = ExamsFacade.getInstance().getStudentExams(studentCourses, bookedExams);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return FactoryExams.getInstance().createBeanBookExams(bookExams);

    }

}
