package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.BookExamModel;

public class AddExam {
    //Add Exam
    public void addExam(BookExamModel exam){
        //Adding it into the DB
        ExamsDAO.addExam(exam);
    }
}
