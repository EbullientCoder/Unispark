package com.example.common.facade;

import com.example.common.database.dao.CourseDAO;
import com.example.common.database.dao.ExamsDAO;
import com.example.common.database.dao.HomeworkDAO;
import com.example.common.model.CourseModel;
import com.example.common.model.HomeworkModel;
import com.example.common.model.ProfessorModel;
import com.example.common.model.exams.BookExamModel;
import com.example.common.model.exams.ExamModel;

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
        List<ExamModel> exams = ExamsDAO.getProfessorExams(professorId);
        List<HomeworkModel> homeworks = HomeworkDAO.getAssignedHomework(professorId);

        return new ProfessorModel(firstName, lastName, email, profilePicture, professorId, faculty, website, courses, exams, homeworks);

    }
}
