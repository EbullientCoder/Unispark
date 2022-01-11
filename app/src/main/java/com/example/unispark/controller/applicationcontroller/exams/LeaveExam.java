package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;

public class LeaveExam {

    //Remove the Connection inside the DB
    public void removeExam(StudentModel student, BookExamModel exam){
        ExamsDAO.removeBookedExam(exam.getId(), student.getId());
    }
}
