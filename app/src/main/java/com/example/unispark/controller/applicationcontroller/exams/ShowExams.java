package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.exams.BeanVerbalizeExam;
import com.example.unispark.bean.professor.BeanLoggedProfessor;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.facade.ExamsFacade;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowExams {
    //Student
    //Page: Verbalized ExamModel
    public List<BeanExamType> verbalizedExams(BeanLoggedStudent student){
        List<VerbalizedExamModel> verbalizedExams = null;
        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            verbalizedExams = ExamsDAO.getVerbalizedExams(student.getId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanVerbalizedExams(verbalizedExams, 0);
    }


    //Page: Failed ExamModel
    public List<BeanExamType> failedExams(BeanLoggedStudent student){
        List<VerbalizedExamModel> failedExams = null;

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            failedExams = ExamsDAO.getFailedExams(student.getId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanVerbalizedExams(failedExams, 0);
    }


    //Page: Upcoming StudentExamsGUIController
    public List<BeanExamType> bookExams(BeanLoggedStudent student){
        List<BookExamModel> bookExams = null;

        try{
            //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
            bookExams = ExamsFacade.getInstance().getExams(student.getId(), false);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanBookExams(bookExams, 2);
    }


    //Page: Booked StudentExamsGUIController
    public List<BeanExamType> bookedExams(BeanLoggedStudent student){
        List<BookExamModel> bookedExams = null;

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try {
            bookedExams = ExamsDAO.getBookedExams(student.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.listBeanBookExams(bookedExams, 3);
    }


    //Professor
    public List<BeanExamType> assignedExams(BeanLoggedProfessor professor){
        List<BookExamModel> exams = null;
        try {
            exams = ExamsFacade.getInstance().getExams(String.valueOf(professor.getId()), true);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanBookExams(exams, 1);
    }



    private List<BeanExamType> listBeanVerbalizedExams(List<VerbalizedExamModel> verbalizedExams, int type){
        List<BeanExamType> bExams = new ArrayList<>();

        for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
            VerbalizedExamModel vExam = verbalizedExams.get(i);
            BeanVerbalizeExam beanVerbalizeExam;
            beanVerbalizeExam = new BeanVerbalizeExam();
            beanVerbalizeExam.setName(vExam.getName());
            beanVerbalizeExam.setResult(vExam.getResult());
            beanVerbalizeExam.setCfu(vExam.getCFU());
            beanVerbalizeExam.setDate(vExam.getDate());
            beanVerbalizeExam.setId(vExam.getId());
            beanVerbalizeExam.setYear(vExam.getYear());
            BeanExamType beanExamType;
            beanExamType = new BeanExamType();
            beanExamType.setType(type);
            beanExamType.setExamType(beanVerbalizeExam);
            bExams.add(beanExamType);
        }

        return bExams;
    }

    private List<BeanExamType> listBeanBookExams(List<BookExamModel> bookExams, int type){
        List<BeanExamType> bExams = new ArrayList<>();

        for (int i = 0; bookExams != null && i < bookExams.size(); i++){
            BookExamModel bExam = bookExams.get(i);
            BeanBookExam beanBookExam;
            beanBookExam = new BeanBookExam();
            beanBookExam.setDate(bExam.getDate());
            beanBookExam.setYear(bExam.getYear());
            beanBookExam.setName(bExam.getName());
            beanBookExam.setCfu(bExam.getCFU());
            beanBookExam.setId(bExam.getId());
            beanBookExam.setBuilding(bExam.getBuilding());
            beanBookExam.setClassroom(beanBookExam.getClassroom());
            BeanExamType beanExamType;
            beanExamType = new BeanExamType();
            beanExamType.setType(type);
            beanExamType.setExamType(beanBookExam);

            bExams.add(beanExamType);
        }

        return bExams;
    }
}
