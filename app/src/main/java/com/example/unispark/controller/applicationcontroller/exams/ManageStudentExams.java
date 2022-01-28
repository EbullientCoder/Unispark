package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.bean.exams.BeanVerbalizeExam;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.database.dao.ExamsDAO;
import com.example.unispark.exceptions.GenericException;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageStudentExams extends ManageExams{





    //Remove the Connection inside the DB
    public void removeExam(BeanLoggedStudent student, int position) throws GenericException {
        List<BookExamModel> bookedExams = student.getBookedExams();
        BookExamModel leaveExam = bookedExams.get(position);
        try {
            ExamsDAO.removeBookedExam(leaveExam.getId(), student.getId());
            //Remove the Booked Exam from Student's Attributes
            bookedExams.remove(position);
            student.setBookedExams(bookedExams);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new GenericException("Try again");
        }
    }



    public List<BeanExamType> verbalizedExams(BeanLoggedStudent student){
        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            student.setVerbalizedExams(ExamsDAO.getVerbalizedExams(student.getId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanVerbalizedExams(student.getVerbalizedExams(), 0);
    }


    //Page: Failed ExamModel
    public List<BeanExamType> failedExams(BeanLoggedStudent student){

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            student.setFailedExams(ExamsDAO.getFailedExams(student.getId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return this.listBeanVerbalizedExams(student.getFailedExams(), 0);
    }




    //Page: Booked StudentExamsGUIController
    public List<BeanExamType> showBookedExams(BeanLoggedStudent student){

        //Types: 0 = Verbalized - Failed Exam | 1 = Professor Assigned Exam | 2 = Book Exam | 3 = Booked Exam
        try{
            student.setBookedExams(ExamsDAO.getBookedExams(student.getId()));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return this.listBeanBookExams(student.getBookedExams(), 3);
    }





    private List<BeanExamType> listBeanVerbalizedExams(List<VerbalizedExamModel> verbalizedExams, int type){
        List<BeanExamType> bExams = new ArrayList<>();

        for (int i = 0; verbalizedExams != null && i < verbalizedExams.size(); i++){
            VerbalizedExamModel vExam = verbalizedExams.get(i);
            BeanVerbalizeExam beanVerbalizeExam;
            beanVerbalizeExam = new BeanVerbalizeExam();
            beanVerbalizeExam.setName(vExam.getName());
            beanVerbalizeExam.setResult(vExam.getResult());
            beanVerbalizeExam.setCfu(vExam.getCfu());
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


}
