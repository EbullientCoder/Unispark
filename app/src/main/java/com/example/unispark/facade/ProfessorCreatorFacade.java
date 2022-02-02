package com.example.unispark.facade;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.HomeworkDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.HomeworkModel;
import com.example.unispark.model.ProfessorModel;
import com.example.unispark.model.exams.BookExamModel;

import java.sql.SQLException;
import java.util.List;

public class ProfessorCreatorFacade {

    private static ProfessorCreatorFacade instance=null;
    private ProfessorCreatorFacade()
    {

    }
    public static ProfessorCreatorFacade getInstance()
    {
        if(instance==null)
        {
            instance=new ProfessorCreatorFacade();
        }
        return instance;
    }

    public ProfessorModel getProfessor(String firstName, String lastName, String email, int profilePicture, int professorId, String faculty, String website) throws SQLException
    {
        List<CourseModel> courses = CourseDAO.selectProfessorCourses(professorId);
        List<BookExamModel> exams = ExamsFacade.getInstance().getProfessorExams(courses);
        List<HomeworkModel> homeworks = HomeworkDAO.getAssignedHomework(professorId);

        return new ProfessorModel(firstName, lastName, email, profilePicture, professorId, faculty, website, courses, exams, homeworks);

    }
}
