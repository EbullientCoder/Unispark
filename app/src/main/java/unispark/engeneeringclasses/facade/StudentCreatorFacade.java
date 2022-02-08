package unispark.engeneeringclasses.facade;

import unispark.engeneeringclasses.dao.CourseDAO;
import unispark.engeneeringclasses.dao.ExamsDAO;
import unispark.model.CourseModel;
import unispark.model.StudentModel;
import unispark.model.exams.ExamModel;


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
