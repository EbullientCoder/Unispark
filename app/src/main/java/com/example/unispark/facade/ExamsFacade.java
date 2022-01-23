package com.example.unispark.facade;

import com.example.unispark.database.dao.CourseDAO;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.model.CourseModel;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.ResultSet;
import java.sql.SQLException;
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


    private List<BookExamModel> getStudentExams(String id) throws SQLException {
        List<BookExamModel> examsList = new ArrayList<>();

        List<CourseModel> courses = CourseDAO.selectStudentCourses(id);

        if (!courses.isEmpty()){

            List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(id);
            List<BookExamModel> tempList;
            for (int i = 0; i < courses.size(); i++)
            {
                tempList = ExamsDAO.getCourseExams(courses.get(i), false);
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

    private List<BookExamModel> getProfessorExams(String id) throws SQLException {
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

    //Select exams marked by studentID/professorId depending on boolean isProfessor
    public List<BookExamModel> getExams(String id, boolean isProfessor) throws SQLException {
        List<BookExamModel> exams;
        if (isProfessor) {
            exams = this.getProfessorExams(id);
        }
        else{
            exams = this.getStudentExams(id);
        }
        return exams;
    }



    //Create a new Booking Exam model
    public BookExamModel bookingExam(ResultSet rs) throws SQLException {

        int id = rs.getInt("examID");
        String name = rs.getString("examname");
        String year = rs.getString( "year");
        String dateTime = rs.getString("date");
        String cfu = rs.getString("cfu");
        String building = rs.getString("building");
        String classroom = rs.getString("class");

        //Create new bookExam model
        return new BookExamModel(id, name, year, dateTime, cfu, classroom, building);
    }


    //Create a new examGrade
    public VerbalizedExamModel examGrade(ResultSet rs, String result) throws SQLException {

        int id = rs.getInt("examID");
        String name = rs.getString("examname");

        String year = rs.getString("year");
        String date = rs.getString("date");
        String cfu = rs.getString("cfu");

        //Create new ExamGrade
        return new VerbalizedExamModel(id, name, year, date, cfu, result);
    }
}
