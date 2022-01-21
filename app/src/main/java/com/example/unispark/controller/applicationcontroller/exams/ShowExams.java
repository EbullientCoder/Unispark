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
        List<BeanExamType> bExams = new ArrayList<>();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{

            List<VerbalizedExamModel> verbalizedExams = ExamsDAO.getVerbalizedExams(student.getId());

            for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
                VerbalizedExamModel vExam = verbalizedExams.get(i);
                BeanVerbalizeExam beanVerbalizeExam;
                beanVerbalizeExam = new BeanVerbalizeExam();
                beanVerbalizeExam.setName(vExam.getName());
                beanVerbalizeExam.setResult(vExam.getResult());
                beanVerbalizeExam.setCFU(vExam.getCFU());
                beanVerbalizeExam.setDate(vExam.getDate());
                beanVerbalizeExam.setId(vExam.getId());
                beanVerbalizeExam.setYear(vExam.getYear());
                BeanExamType beanExamType;
                beanExamType = new BeanExamType();
                beanExamType.setType(0);
                beanExamType.setBeanExamType(beanVerbalizeExam);
                bExams.add(beanExamType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Page: Failed ExamModel
    public List<BeanExamType> failedExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            List<VerbalizedExamModel> failedExams = ExamsDAO.getFailedExams(student.getId());
            for (int i = 0; failedExams != null && i < failedExams.size(); i++){
                VerbalizedExamModel vExam = failedExams.get(i);
                BeanVerbalizeExam beanVerbalizeExam;
                beanVerbalizeExam = new BeanVerbalizeExam();
                beanVerbalizeExam.setName(vExam.getName());
                beanVerbalizeExam.setResult(vExam.getResult());
                beanVerbalizeExam.setCFU(vExam.getCFU());
                beanVerbalizeExam.setDate(vExam.getDate());
                beanVerbalizeExam.setId(vExam.getId());
                beanVerbalizeExam.setYear(vExam.getYear());
                BeanExamType beanExamType;
                beanExamType = new BeanExamType();
                beanExamType.setType(0);
                beanExamType.setBeanExamType(beanVerbalizeExam);
                bExams.add(beanExamType);
                bExams.add(beanExamType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Page: Upcoming StudentExamsGUIController
    public List<BeanExamType> bookExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();


        try{
            //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
            List<BookExamModel> bookExams = ExamsFacade.getInstance().getExams(student.getId(), false);
            for (int i = 0; bookExams != null && i < bookExams.size(); i++){
                BookExamModel bExam = bookExams.get(i);
                BeanBookExam beanBookExam;
                beanBookExam = new BeanBookExam();
                beanBookExam.setDate(bExam.getDate());
                beanBookExam.setYear(bExam.getYear());
                beanBookExam.setName(bExam.getName());
                beanBookExam.setCFU(bExam.getCFU());
                beanBookExam.setId(bExam.getId());
                beanBookExam.setBuilding(bExam.getBuilding());
                beanBookExam.setClassroom(beanBookExam.getClassroom());
                BeanExamType beanExamType;
                beanExamType = new BeanExamType();
                beanExamType.setType(2);
                beanExamType.setBeanExamType(beanBookExam);

                bExams.add(beanExamType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Page: Booked StudentExamsGUIController
    public List<BeanExamType> bookedExams(BeanLoggedStudent student){
        List<BeanExamType> bExams = new ArrayList<>();

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            List<BookExamModel> bookedExams = ExamsDAO.getBookedExams(student.getId());
            for (int i = 0; bookedExams != null && i < bookedExams.size(); i++){
                BookExamModel bExam = bookedExams.get(i);
                BeanBookExam beanBookExam;
                beanBookExam = new BeanBookExam();
                beanBookExam.setDate(bExam.getDate());
                beanBookExam.setYear(bExam.getYear());
                beanBookExam.setName(bExam.getName());
                beanBookExam.setCFU(bExam.getCFU());
                beanBookExam.setId(bExam.getId());
                beanBookExam.setBuilding(bExam.getBuilding());
                beanBookExam.setClassroom(beanBookExam.getClassroom());
                BeanExamType beanExamType;
                beanExamType = new BeanExamType();
                beanExamType.setType(3);
                beanExamType.setBeanExamType(beanBookExam);

                bExams.add(beanExamType);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return bExams;
    }


    //Professor
    public List<BeanExamType> assignedExams(BeanLoggedProfessor professor){
        List<BeanExamType> bExams = new ArrayList<>();


        try {
            List<BookExamModel> exams;
            exams = ExamsFacade.getInstance().getExams(String.valueOf(professor.getId()), true);
            for (int i = 0; exams != null && i < exams.size(); i++){
                BookExamModel bExam = exams.get(i);
                BeanBookExam beanBookExam;
                beanBookExam = new BeanBookExam();
                beanBookExam.setDate(bExam.getDate());
                beanBookExam.setYear(bExam.getYear());
                beanBookExam.setName(bExam.getName());
                beanBookExam.setCFU(bExam.getCFU());
                beanBookExam.setId(bExam.getId());
                beanBookExam.setBuilding(bExam.getBuilding());
                beanBookExam.setClassroom(beanBookExam.getClassroom());
                BeanExamType beanExamType;
                beanExamType = new BeanExamType();
                beanExamType.setType(1);
                beanExamType.setBeanExamType(beanBookExam);

                bExams.add(beanExamType);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return bExams;
    }
}
