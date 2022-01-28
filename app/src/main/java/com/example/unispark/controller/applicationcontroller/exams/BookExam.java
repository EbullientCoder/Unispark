package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.ExamAlreadyVerbalized;
import com.example.unispark.exceptions.ExamException;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;

import java.sql.SQLException;
import java.util.List;

public class BookExam extends ManageExams{

    public void bookExam(BeanLoggedStudent student, BeanBookExam exam) throws ExamAlreadyVerbalized, GenericException
    {
        List<BookExamModel> exams = student.getBookedExams();
        BookExamModel bookExam = new BookExamModel(exam.getId(), exam.getName(), exam.getYear(), exam.getDate(), exam.getCfu(), exam.getClassroom(), exam.getBuilding());
        try {
            ExamsDAO.bookExam(bookExam, student.getId());
            exams.add(bookExam);
            student.setBookedExams(exams);
        } catch (ExamException e) {
            e.printStackTrace();
            throw new ExamAlreadyVerbalized("Exam verbalized, cannot book");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }



    //Page: Upcoming StudentExamsGUIController
    public List<BeanExamType> showBookExams(BeanLoggedStudent student){
        List<BookExamModel> bookExams = null;

        try{
            //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
            List<CourseModel> studentCourses = student.getCourses();
            bookExams = ExamsFacade.getInstance().getStudentExams(student.getId(), studentCourses);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanBookExams(bookExams, 2);
    }
}
