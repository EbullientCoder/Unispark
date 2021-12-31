package com.example.unispark.facade;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.CourseModel;

import java.util.ArrayList;
import java.util.List;

public class ExamsFacade {

    private static ExamsFacade instance=null;
    private ExamsFacade()
    {

    }
    public static synchronized ExamsFacade getInstance()
    {
        if(instance==null)
        {
            instance=new ExamsFacade();
        }
        return instance;
    }

    private static void removeBookedExams (List<BookExamModel> bookedExams, List<BookExamModel> exams)
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


    public List<BookExamModel> getStudentExams(String id)
    {
        List<CourseModel> courses = CourseDAO.selectStudentCourses(id);
        if (courses == null){
            return null;
        }
        List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(id);

        List<BookExamModel> examsList = new ArrayList<>();
        List<BookExamModel> tempList;
        for (int i = 0; i < courses.size(); i++)
        {
            tempList = ExamsDAO.getCourseExams(courses.get(i), false);
            if(tempList != null){
                if (bookedExams != null) {
                    removeBookedExams(bookedExams, tempList);
                }
                examsList.addAll(tempList);
            }
        }
        return examsList;
    }

    public List<BookExamModel> getProfessorExams(String id)
    {
        List<CourseModel> courses = CourseDAO.selectProfessorCourses(Integer.valueOf(id));

        List<BookExamModel> examsList = new ArrayList<>();
        List<BookExamModel> tempList;
        for (int i = 0; i < courses.size(); i++)
        {
            tempList = ExamsDAO.getCourseExams(courses.get(i), true);
            if(tempList != null){
                examsList.addAll(tempList);
            }
        }
        return examsList;
    }
}
