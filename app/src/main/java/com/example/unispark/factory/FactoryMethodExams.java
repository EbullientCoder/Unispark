package com.example.unispark.factory;

import com.example.unispark.bean.exams.BeanBookExam;
import com.example.unispark.bean.exams.BeanExam;
import com.example.unispark.bean.exams.BeanVerbalizeExam;
import com.example.unispark.bean.student.BeanLoggedStudent;
import com.example.unispark.model.exams.BookExamModel;
import com.example.unispark.model.exams.ExamModel;
import com.example.unispark.model.exams.VerbalizedExamModel;

import java.util.ArrayList;
import java.util.List;

public class FactoryMethodExams {

    private static FactoryMethodExams instance = null;

    private FactoryMethodExams() {

    }

    public static FactoryMethodExams getInstance() {
        if (instance == null) {
            instance = new FactoryMethodExams();
        }
        return instance;
    }


    public List<BeanExam> createBeanExams(List<ExamModel> exams, int type) {
        List<BeanExam> bExams = new ArrayList<>();

        //BeanVerbalizeExams
        if (type == 0) {
            for (int i = 0; i < exams.size(); i++) {
                VerbalizedExamModel vExam = (VerbalizedExamModel) exams.get(i);
                BeanVerbalizeExam beanVerbalizeExam;
                beanVerbalizeExam = new BeanVerbalizeExam();
                beanVerbalizeExam.setName(vExam.getName());
                beanVerbalizeExam.setResult(vExam.getResult());
                beanVerbalizeExam.setCfu(vExam.getCfu());
                beanVerbalizeExam.setDate(vExam.getDate());
                beanVerbalizeExam.setId(vExam.getId());
                beanVerbalizeExam.setYear(vExam.getYear());

                bExams.add(beanVerbalizeExam);
            }

        } else {
            //BeanBookExams
            for (int i = 0; i < exams.size(); i++) {
                BookExamModel bExam = (BookExamModel) exams.get(i);
                BeanBookExam beanBookExam;
                beanBookExam = new BeanBookExam();
                beanBookExam.setDate(bExam.getDate());
                beanBookExam.setYear(bExam.getYear());
                beanBookExam.setName(bExam.getName());
                beanBookExam.setCfu(bExam.getCfu());
                beanBookExam.setId(bExam.getId());
                beanBookExam.setBuilding(bExam.getBuilding());
                beanBookExam.setClassroom(bExam.getClassroom());

                bExams.add(beanBookExam);
            }
        }

        return bExams;

    }

}
