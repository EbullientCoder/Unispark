package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.BeanStudentSignedToExam;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;


public class VerbalizeExam {
    //Verbalize Exam
    public boolean verbalizeExam(BookExamModel exam, BeanStudentSignedToExam student, String result){
        //Create new Verbalized Exam
        VerbalizedExamModel vExam = new VerbalizedExamModel(exam.getId(), exam.getName(), exam.getDate(), exam.getDate(), exam.getCFU(), result);

        //Add Verbalized Exam to the DB
        boolean isValid = ExamsDAO.addExamGrade(vExam, student.getId());

        return isValid;
    }
}
