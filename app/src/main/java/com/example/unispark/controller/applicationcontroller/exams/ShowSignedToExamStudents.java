package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.exam.BeanBookExam;
import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.dao.ExamsDAO;

import java.sql.SQLException;
import java.util.List;

public class ShowSignedToExamStudents {
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
