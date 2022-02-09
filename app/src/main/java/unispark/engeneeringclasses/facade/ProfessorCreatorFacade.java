package unispark.engeneeringclasses.facade;

import unispark.engeneeringclasses.dao.CourseDAO;
import unispark.engeneeringclasses.dao.ExamsDAO;
import unispark.engeneeringclasses.dao.HomeworkDAO;
import unispark.model.CourseModel;
import unispark.model.HomeworkModel;
import unispark.model.ProfessorModel;
import unispark.model.exams.BookExamModel;
import unispark.model.exams.ExamModel;

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
        List<BookExamModel> exams = ExamsDAO.getProfessorExams(professorId);
        List<HomeworkModel> homeworks = HomeworkDAO.getAssignedHomework(professorId);

        return new ProfessorModel(firstName, lastName, email, profilePicture, professorId, faculty, website, courses, exams, homeworks);

    }
}
