package com.example.unispark.facade;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.database.dao.StudentLinksDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.LinkModel;
import com.example.unispark.model.StudentModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

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

    public StudentModel getStudent(String firstName, String lastName, String email, int profilePicture, String studentId, String faculty, String academicYear) //throws exception
    {

        List<CourseModel> coursesList = CourseDAO.selectStudentCourses(studentId);
        List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(studentId);
        List<VerbalizedExamModel> verbalizedExams = ExamsDAO.getVerbalizedExams(studentId);
        List<VerbalizedExamModel> failedExams = ExamsDAO.getFailedExams(studentId);

        return new StudentModel(firstName, lastName, email, profilePicture, studentId, faculty, academicYear, coursesList, bookedExams, verbalizedExams, failedExams);
    }
}
