package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;

public class BookExam {
    //Book Exam
    //Make DB Connection
    public boolean bookExam(StudentModel student, BookExamModel exam){
        boolean isBooked = ExamsDAO.bookExam(exam, student.getId());

        return isBooked;
    }
}
