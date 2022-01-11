package com.example.unispark.controller.applicationcontroller.exams;

import android.content.Context;

import com.example.unispark.adapter.exams.ExamAdapter;
import com.example.unispark.adapter.exams.ExamItem;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.ArrayList;
import java.util.List;

public class StudentShowExams {

    //Page: Verbalized ExamModel
    public List<ExamItem> verbalizedExams(StudentModel student){
        List<ExamItem> examsItem = new ArrayList<>();


        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<VerbalizedExamModel> verbalizedExams = student.getVerbalizedExams();
        for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
            examsItem.add(new ExamItem(0, verbalizedExams.get(i)));
        }

        return examsItem;
    }


    //Page: Failed ExamModel
    public List<ExamItem> failedExams(StudentModel student){
        List<ExamItem> examsItem = new ArrayList<>();


        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<VerbalizedExamModel> failedExams = student.getFailedExams();
        for (int i = 0; failedExams != null && i < failedExams.size(); i++){
            examsItem.add(new ExamItem(0, failedExams.get(i)));
        }

        return examsItem;
    }


    //Page: Upcoming StudentExamsGUIController
    public List<ExamItem> bookExams(StudentModel student){
        List<ExamItem> examsItem = new ArrayList<>();


        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<BookExamModel> bookExams = ExamsDAO.getExams(student.getId(), false);
        for (int i = 0; bookExams != null && i < bookExams.size(); i++){
            examsItem.add(new ExamItem(2, bookExams.get(i)));
        }

        return examsItem;
    }


    //Page: Booked StudentExamsGUIController
    public List<ExamItem> bookedExams(StudentModel student){
        List<ExamItem> examsItem = new ArrayList<>();


        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        List<BookExamModel> leaveExams = student.getBookedExams();
        for (int i = 0; leaveExams != null && i < leaveExams.size(); i++){
            examsItem.add(new ExamItem(3, leaveExams.get(i)));
        }

        return examsItem;
    }
}
