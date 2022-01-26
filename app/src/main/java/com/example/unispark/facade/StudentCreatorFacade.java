package com.example.unispark.facade;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;


import java.sql.SQLException;
import java.util.List;

public class StudentCreatorFacade {

    private static StudentCreatorFacade instance=null;
    private StudentCreatorFacade()
    {

    }
    public static synchronized StudentCreatorFacade getInstance()
    {
        if(instance==null)
        {
            instance=new StudentCreatorFacade();
        }
        return instance;
    }

    public StudentModel getStudent(List<String> nameEmail, int profilePicture, String studentId, String faculty, String academicYear, int uniYear) throws SQLException
    {

        List<CourseModel> coursesList = CourseDAO.selectStudentCourses(studentId);
        List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(studentId);
        List<VerbalizedExamModel> verbalizedExams = ExamsDAO.getVerbalizedExams(studentId);
        List<VerbalizedExamModel> failedExams = ExamsDAO.getFailedExams(studentId);

        return new StudentModel(nameEmail.get(0), nameEmail.get(1), nameEmail.get(2), profilePicture, studentId, faculty, academicYear, coursesList, bookedExams, verbalizedExams, failedExams, uniYear);
    }
}
