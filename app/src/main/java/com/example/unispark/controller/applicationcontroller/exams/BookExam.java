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
import java.util.ArrayList;
import java.util.List;

public class BookExam {

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

    public List<BeanExamType> generateBookingExams(BeanLoggedStudent student){
        List<BookExamModel> bookExams = null;
        List<BeanExamType> beanExamsTypeList = new ArrayList<>();

        try{
            List<BookExamModel> bookedExams = student.getBookedExams();
            List<CourseModel> studentCourses = student.getCourses();
            bookExams = ExamsFacade.getInstance().getStudentExams(studentCourses, bookedExams);

            for (int i = 0; i < bookExams.size(); i++){
                BookExamModel bookExam = bookExams.get(i);
                BeanBookExam beanBookExam;
                beanBookExam = new BeanBookExam();
                beanBookExam.setDate(bookExam.getDate());
                beanBookExam.setYear(bookExam.getYear());
                beanBookExam.setName(bookExam.getName());
                beanBookExam.setCfu(bookExam.getCfu());
                beanBookExam.setId(bookExam.getId());
                beanBookExam.setBuilding(bookExam.getBuilding());
                beanBookExam.setClassroom(bookExam.getClassroom());
                BeanExamType beanExamType;
                beanExamType = new BeanExamType();
                beanExamType.setType(2);
                beanExamType.setExamType(beanBookExam);

                beanExamsTypeList.add(beanExamType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return beanExamsTypeList;


    }






}
