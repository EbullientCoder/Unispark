package com.example.unispark.facade;


import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.ExamModel;


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


    public List<ExamModel> getStudentExams(List<CourseModel> studentCourses, List<ExamModel> bookedExams) throws SQLException {
        List<ExamModel> examsList = new ArrayList<>();

        if (!studentCourses.isEmpty()){
            List<ExamModel> tempList;
            for (int i = 0; i < studentCourses.size(); i++)
            {
                tempList = ExamsDAO.getCourseStudentExams(studentCourses.get(i));
                if(!tempList.isEmpty()){
                    if (!bookedExams.isEmpty()) {
                        removeBookedExams(bookedExams, tempList);
                    }
                    examsList.addAll(tempList);
                }
            }
        }
        return examsList;
    }


    private static void removeBookedExams (List<ExamModel> bookedExams, List<ExamModel> exams)
    {
        int examId;
        int bookedExamId;
        List<ExamModel> removeExams = new ArrayList<>();
        for (int i = 0; i < exams.size(); i++){
            examId = exams.get(i).getId();
            for (int j = 0; j < bookedExams.size(); j++){
                bookedExamId = bookedExams.get(j).getId();
                if (examId == bookedExamId) removeExams.add(exams.get(i));
            }
        }
        exams.removeAll(removeExams);
    }


}
