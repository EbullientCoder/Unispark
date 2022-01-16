package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.login.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.DatabaseOperationError;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.exams.BookExamModel;

import java.util.List;

public class LeaveExam {

    //Remove the Connection inside the DB
    public void removeExam(BeanLoggedStudent student, int position) throws GenericException
    {
        List<BookExamModel> bookedExams = student.getBookedExams();
        BookExamModel leaveExam = bookedExams.get(position);
        try {
            ExamsDAO.removeBookedExam(leaveExam.getId(), student.getId());
            //Remove the Booked Exam from Student's Attributes
            bookedExams.remove(position);
            student.setBookedExams(bookedExams);
        } catch (DatabaseOperationError databaseOperationError) {
            databaseOperationError.printStackTrace();
            throw new GenericException("Cannot leave Exam, try again");
        }
    }
}
