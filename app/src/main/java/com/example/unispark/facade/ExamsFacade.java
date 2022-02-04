package com.example.unispark.facade;


import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExamsFacade {

    private static ExamsFacade instance=null;
    private ExamsFacade()
    {

    }
    public static ExamsFacade getInstance()
    {
        if(instance==null)
        {
            instance=new ExamsFacade();
        }
        return instance;
    }

    private void removeBookedExams (List<BookExamModel> bookedExams, List<BookExamModel> exams)
    {
        int examId;
        int bookedExamId;
        List<BookExamModel> removeExams = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++){
            examId = exams.get(i).getId();
            for (int j = 0; j < bookedExams.size(); j++){
                bookedExamId = bookedExams.get(j).getId();
                if (examId == bookedExamId) removeExams.add(exams.get(i));
            }
        }
        exams.removeAll(removeExams);
    }


    public List<BookExamModel> getStudentExams(List<CourseModel> studentCourses, List<BookExamModel> bookedExams) throws SQLException {
        List<BookExamModel> examsList = new ArrayList<>();

        if (!studentCourses.isEmpty()){
            List<BookExamModel> tempList;
            for (int i = 0; i < studentCourses.size(); i++)
            {
                tempList = ExamsDAO.getCourseExams(studentCourses.get(i), false);
                if(!tempList.isEmpty()){
                    if (!bookedExams.isEmpty()) {
                        this.removeBookedExams(bookedExams, tempList);
                    }
                    examsList.addAll(tempList);
                }
            }
        }
        return examsList;
    }

    public List<BookExamModel> getProfessorExams(List<CourseModel> professorCourses) throws SQLException {

        List<BookExamModel> examsList = new ArrayList<>();
        List<BookExamModel> tempList;
        for (int i = 0; i < professorCourses.size(); i++)
        {
            tempList = ExamsDAO.getCourseExams(professorCourses.get(i), true);
            if(!tempList.isEmpty()){
                examsList.addAll(tempList);
            }
        }
        return examsList;
    }

}
