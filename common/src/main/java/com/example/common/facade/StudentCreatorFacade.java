package com.example.common.facade;

import com.example.common.database.dao.CourseDAO;
import com.example.common.database.dao.ExamsDAO;
import com.example.common.model.CourseModel;
import com.example.common.model.StudentModel;
import com.example.common.model.exams.BookExamModel;
import com.example.common.model.exams.ExamModel;
import com.example.common.model.exams.VerbalizedExamModel;


import java.sql.SQLException;
import java.util.List;

public class StudentCreatorFacade {

    private static StudentCreatorFacade instance=null;
    private StudentCreatorFacade()
    {

    }
    public static StudentCreatorFacade getInstance()
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
        List<ExamModel> bookedExams = ExamsDAO.getBookedExams(studentId);
        List<ExamModel> verbalizedExams = ExamsDAO.getVerbalizedExams(studentId);
        List<ExamModel> failedExams = ExamsDAO.getFailedExams(studentId);


        return new StudentModel(nameEmail.get(0), profilePicture, nameEmail.get(1), nameEmail.get(2), studentId, faculty, academicYear, coursesList, bookedExams, verbalizedExams, failedExams, uniYear);
    }
}
