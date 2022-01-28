package com.example.unispark.controller.applicationcontroller.exams;

import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExamType;
import com.example.unispark.model.exams.BookExamModel;

import java.util.ArrayList;
import java.util.List;

public class ManageExams {


    public List<BeanExamType> listBeanBookExams(List<BookExamModel> bookExams, int type){
        List<BeanExamType> bExams = new ArrayList<>();

        for (int i = 0; bookExams != null && i < bookExams.size(); i++){
            BookExamModel bExam = bookExams.get(i);
            BeanBookExam beanBookExam;
            beanBookExam = new BeanBookExam();
            beanBookExam.setDate(bExam.getDate());
            beanBookExam.setYear(bExam.getYear());
            beanBookExam.setName(bExam.getName());
            beanBookExam.setCfu(bExam.getCfu());
            beanBookExam.setId(bExam.getId());
            beanBookExam.setBuilding(bExam.getBuilding());
            beanBookExam.setClassroom(bExam.getClassroom());
            BeanExamType beanExamType;
            beanExamType = new BeanExamType();
            beanExamType.setType(type);
            beanExamType.setExamType(beanBookExam);

            bExams.add(beanExamType);
        }

        return bExams;
    }
}
